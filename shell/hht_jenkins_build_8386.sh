#!/bin/sh

function try_to_clean()
{
    if [ "$CleanProject" == "Yes" ];then
        echo "==Will clean project=="
        cd $SCRIPT_PATH
        ./$SCRIPT_NAME clean
    else
        echo "==Not clean project=="
    fi
}

function prepare_build()
{
    ###add persist.sys.route_mode.debug in PROP_FILE if someone choosed router/wifi from jenkins
    if [ "$NetMode" == "wifi" ];then
        echo "persist.sys.route_mode.debug=wifi" >> $PROP_FILE
    else if [ "$NetMode" == "router" ];then
        echo "persist.sys.route_mode.debug=router" >> $PROP_FILE
    else if [ "$NetMode" == "followProductId" ];then
        net=${ProductId:16:2}
        echo "NetMode info:$net"
        if [ "$net" == "02" ];then
            NetMode="wifi"
        else if [ "$net" == "01" ];then
            NetMode="router"
        fi
        fi

        ## default router for irregular ProdcutId(such as PRODUCTID00000000000)
        if [ "$NetMode" == "followProductId" ];then
            NetMode="router"
            echo "persist.sys.route_mode.debug=router" >> $PROP_FILE
        fi
    fi
    fi
    fi
    echo "prepare_build, NetMode:$NetMode"

    ### rewrite CENTER_CTL/CULTRAVIEW_PRINT in SET_CONFIG_FILE
    ctl_name="setenv CENTER_CTL"
    print_name="setenv CULTRAVIEW_PRINT"
    if [ "$UartMode" == "CenterCtl" ];then
        echo "CenterCtl"
        sed -i "s/$ctl_name .*/$ctl_name on/" $SET_CONFIG_FILE
        sed -i "s/$print_name .*/$print_name off/" $SET_CONFIG_FILE
    else if [ "$UartMode" == "Uart" ];then
        echo "Uart"
        sed -i "s/$ctl_name .*/$ctl_name off/" $SET_CONFIG_FILE
        sed -i "s/$print_name .*/$print_name on/" $SET_CONFIG_FILE
    fi
    fi
    sed -n /CENTER_CTL/p $SET_CONFIG_FILE
    sed -n /CULTRAVIEW_PRINT/p $SET_CONFIG_FILE
}

function build_all()
{
    echo "==Will build_all=="
    cd $SCRIPT_PATH
    ./$SCRIPT_NAME all "pid=$ProductId" $ExcludePartition
    if [ $? -ne 0 ]; then
        echo "build all failed !"
        exit -1;
    fi

    ### release_panelFiles after build all
    ### copyfiles.sh would be called every time SCRIPT_NAME is called
    if [ "$PublishPanelFiles" == "Yes" ];then
        cd $CURRENT_PATH
        ./hht/build/release_panelFiles.sh
        if [ $? -ne 0 ]; then
            echo "build panelFiles failed !"
            exit -1;
        fi
    fi

    cd $SCRIPT_PATH
    if [ "$PublishOta" == "Yes" ];then
        ./$SCRIPT_NAME ota "pid=$ProductId"
        if [ $? -ne 0 ]; then
            echo "build ota failed !"
            exit -1;
        fi
    fi
    if [ "$PublishIncre" == "Yes" ];then
        ./$SCRIPT_NAME otainc "pid=$ProductId"
        if [ $? -ne 0 ]; then
            echo "build otainc failed !"
            exit -1;
        fi
    fi

}

function prepare_publish()
{
    if [ ! -f "$BUILD_PROP" ] || [ ! -f "$VENDOR_PROP" ];then
        echo "$BUILD_PROP or $VENDOR_PROP not exist"
        exit 1
    fi
    echo "BUILD_PROP=$BUILD_PROP"
    echo "VENDOR_PROP=$VENDOR_PROP"


    ###
    ###Filter some character from files
    ###
    gitHashFull=`git log --pretty=oneline -1`
    gitHash=${gitHashFull:0:7}
    Version=`sed -n '/^[^#]*ro.build.version.incremental/p' $BUILD_PROP |cut -d= -f2`
    VersionStable=`sed -n '/^[^#]*ro.build.version.stable/p' $BUILD_PROP |cut -d= -f2`
    IncrementalNumber=`sed -n '/^OTA_INCREMENTAL_NUMBER/p' $BOARDCONFIG |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    TimeUtc=`sed -n '/^[^#]*ro.build.date.utc/p' $BUILD_PROP |cut -d= -f2`
    Time=`date -d @"$TimeUtc" "+%Y%m%d.%H%M%S"`
    Board=`sed -n "/^[^#]*ro.product.board=/p" $VENDOR_PROP|cut -d= -f2|tr '_-' '.'`
    PanelId=`sed -n '/^[^#]*PANEL_ID/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    Panel=`echo $PanelId|awk -F"PANEL_" '{print $2}'|tr '_-' '.'`
    echo "Panel:$Panel"
    Target_name="${ProductId}_${Board}_${Panel}_${Version}_${Time}_${gitHash}"
    echo "Target_name="$Target_name

    material_fw=`sed -n '/^[^#]*MATERIAL_FW/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    material_vctl=`sed -n '/^[^#]*MATERIAL_VCTL/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    material_ota=`sed -n '/^[^#]*MATERIAL_OTA/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    category=`sed -n '/^[^#]*PRODUCT_CATEGORY/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panel_type=`sed -n '/^[^#]*PANEL_TYPE/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    brand=`sed -n '/^[^#]*PRODUCT_BRAND/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`

    productModel=`sed -n '/^[^#]*PRODUCT_MODEL/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelModelPart1=`sed -n '/^[^#]*PANEL_MODEL_PART1/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelModelPart2=`sed -n '/^[^#]*PANEL_MODEL_PART2/p' $CURRENT_PATH/hht/product/$PanelId/version.txt |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelModel="$panelModelPart1"."$panelModelPart2"
    panel_file=PanelFiles_"$productModel"_"$panelModel".zip
    echo "panel_file:$panel_file"


    ###
    ###Clean old published files
    ###
    cd $RELEASE_PATH
    rm -rf $PUBLISH_PATH
    mkdir $PUBLISH_PATH


    ###
    ###Prepare some parameters for the shell on publish-server
    ###
    echo "JENKINS_BUILD_TIME="$JENKINS_BUILD_TIME > $PARAMETER_FILE
    echo "PublishType="$PublishType >> $PARAMETER_FILE
    echo "Branch="$Branch >> $PARAMETER_FILE
    echo "NetMode="$NetMode >> $PARAMETER_FILE
    echo "UartMode="$UartMode >> $PARAMETER_FILE
    echo "productModel="$productModel >> $PARAMETER_FILE
    mv $PARAMETER_FILE $PUBLISH_PATH


    ###
    ###Make the files to publish
    ###
    Target_images=""
    if [ "$PublishBin" == "Yes" ];then
        targetBinZip=${material_fw}_BASECODE_${category}_${productModel}_${Board}_${panel_type}_${Panel}_${brand}_${Version}_${Time}_${gitHash}_${VersionStable}.zip
        echo "targetBinZip:$targetBinZip"
        md5sum $TARGET_BIN > bin_md5.txt
        rm -f *BASECODE*.zip
        zip -qry $targetBinZip $TARGET_BIN bin_md5.txt
        Target_images="$Target_images $targetBinZip"
    fi
    if [ "$PublishOta" == "Yes" ];then
        otaZip=${material_ota}_OTA_${category}_${productModel}_${Board}_${panel_type}_${Panel}_${brand}_${Version}_${Time}_${gitHash}_${VersionStable}_FULL.zip
        echo "otaZip:$otaZip"
        md5sum ${DEVICE}-ota-${Version}.zip ${DEVICE}-target_files-${Version}.zip > ota_md5.txt
        rm -f *OTA*FULL.zip
        zip -qry $otaZip ${DEVICE}-ota-${Version}.zip ${DEVICE}-target_files-${Version}.zip ota_md5.txt
        Target_images="$Target_images $otaZip"
    fi
    if [ "$PublishIncre" == "Yes" ];then
        increZip=${material_ota}_OTA_${category}_${productModel}_${Board}_${panel_type}_${Panel}_${brand}_${Version}_${Time}_${gitHash}_${VersionStable}_INC.zip
        echo "increZip:$increZip"
        md5sum incremental-${DEVICE}-${IncrementalNumber}-patch-${Version}.zip > incre_md5.txt
        rm -f *OTA*INC.zip
        zip -qry $increZip incremental-${DEVICE}-${IncrementalNumber}-patch-${Version}.zip incre_md5.txt
        Target_images="$Target_images $increZip"
    fi
    if [ "$PublishPanelFiles" == "Yes" ];then
        vctlZip=${material_vctl}_VCTL_${category}_${productModel}_${Board}_${panel_type}_${Panel}_${brand}_${Version}_${Time}_${gitHash}_${VersionStable}.zip
        echo "vctlZip:$vctlZip"
        md5sum $panel_file > panelfile_md5.txt
        rm -f *VCTL*.zip
        zip -qry $vctlZip $panel_file panelfile_md5.txt
        Target_images="$Target_images $vctlZip"
    fi
    if [ "$Target_images" == "" ];then
        echo "Requestor of this build choose to publish nothing." > $NOTHING_PUBLISHED_FILE
        mv $NOTHING_PUBLISHED_FILE $PUBLISH_PATH
        return
    fi
    echo "Target_images:[$Target_images]"
    md5sum $Target_images > $IMAGES_MD5_FILE

    rm -f $Target_name.zip
    zip -qry $Target_name.zip $Target_images $IMAGES_MD5_FILE
    md5sum $Target_name.zip > $TARGET_MD5_FILE

    if [ "$PublishType" == "Integrated" ];then
        zipinfo $Target_name.zip > $ZIPINFOFILE
        mv $ZIPINFOFILE $PUBLISH_PATH
    fi

    mv $Target_name.zip $PUBLISH_PATH
    mv $TARGET_MD5_FILE $PUBLISH_PATH
}

function print_info_into_log()
{
    if [ "$Branch" != "" ] && [ "$Branch" != "master" ];then
        info="Submit:http://172.21.120.33/image/$ProjectName/$JENKINS_BUILD_TIME-$Branch"
    else
        info="Submit:http://172.21.120.33/image/$ProjectName/$JENKINS_BUILD_TIME"
    fi

    info=$info-$NetMode

    if [ "$UartMode" == "Uart" ];then
        info=$info-$UartMode
    fi

    echo $info
}


####
####Analyze the parameters
####
if [ "$#" -lt 11 ]; then
    echo "invalid parameters"
    echo "Usage:"
    echo "PublishType:[Seperated|Integrated]"
    echo "CleanProject:[Yes|No]"
    echo "Branch:[master|stable-20200620|...]"
    echo "PublishBin:[Yes|No]"
    echo "PublishOta:[Yes|No]"
    echo "PublishIncre:[Yes|No]"
    echo "ProductId:[PRODUCTID00000000000|...]"
    echo "ExcludePartition:[[factory,[[tvcertificate,[[tvconfig"
    echo "NetMode:[followProductId|router|wifi]"
    echo "UartMode:[CenterCtl|Uart]"
    echo "PublishPanelFiles:[Yes|No]"
    echo "./hht/build/jenkins_build_8386.sh mstar8386-2020 PublishType CleanProject Branch PublishBin PublishOta PublishIncre ProductId ExcludePartition NetMode UartMode PublishPanelFiles"
    echo "./hht/build/jenkins_build_8386.sh mstar8386-2020 Seperated No master Yes No No pid=PRODUCTID00000000000 exclude=[[factory,[[tvcertificate wifi CenterCtl Yes"
    echo "./hht/build/jenkins_build_8386.sh mstar8386-2020 Seperated No master Yes Yes Yes pid=J1C0H0W1K50100000100 exclude=[[factory,[[tvcertificate followProductId CenterCtl Yes"
    exit 1
fi
ProjectName=$1
PublishType=$2
CleanProject=$3
Branch=$4
PublishBin=$5
PublishOta=$6
PublishIncre=$7
ProductId=${8:4}
ExcludePartition=$9
NetMode=${10}
UartMode=${11}
PublishPanelFiles=${12}
echo "ProjectName=$ProjectName PublishType=$PublishType CleanProject=$CleanProject Branch=$Branch PublishBin=$PublishBin PublishOta:$PublishOta"
echo "PublishIncre:$PublishIncre ProductId:$ProductId ExcludePartition:$ExcludePartition NetMode:$NetMode UartMode:$UartMode PublishPanelFiles:$PublishPanelFiles"


####
####Define some var
####
DEVICE=CV8386H_A
JENKINS_BUILD_TIME=`date +%Y%m%d-%H%M%S`
CURRENT_PATH=`pwd`
SCRIPT_PATH=$CURRENT_PATH/build
SCRIPT_NAME=build-userdebug-CV8386_MH.sh
RELEASE_PATH=$CURRENT_PATH/../images/oreo/$DEVICE
PUBLISH_PATH=$RELEASE_PATH/publish
BUILD_PROP=$CURRENT_PATH/out/target/product/$DEVICE/system/build.prop
VENDOR_PROP=$CURRENT_PATH/out/target/product/$DEVICE/vendor/build.prop
BOARDCONFIG=$CURRENT_PATH/device/mstar/$DEVICE/BoardConfig.mk
PANEL_COPY_FILE=$CURRENT_PATH/hht/product/$ProductId/copyfiles.sh
TARGET_BIN=MstarUpgrade.bin
IMAGES_MD5_FILE=zips_md5.txt
TARGET_MD5_FILE=md5.txt
PARAMETER_FILE=parameter.txt
ZIPINFOFILE=zipinfo.txt
NOTHING_PUBLISHED_FILE=nothing_published.txt
PROP_FILE=hht/property/hht.prop
SET_CONFIG_FILE=vendor/mstar/product/m7221/images/prebuilts/scripts/set_config
CUSTOMER_MAKE_FILE=$CURRENT_PATH/hht/hht_product_customized.mk


####
####Start to build
####
try_to_clean
prepare_build
build_all
prepare_publish
print_info_into_log
