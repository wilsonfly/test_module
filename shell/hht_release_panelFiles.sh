#!/bin/bash

ROOT_PATH=`pwd`
echo "release_panelFiles.sh/ROOT_PATH:$ROOT_PATH"

IMAGES_PATH=$ROOT_PATH/../images/oreo/CV8386H_A
PANELFILES_PATH=$IMAGES_PATH/PanelFiles
SUPERNOVA_PATH=$ROOT_PATH/vendor/mstar/supernova
SUPERNOVA_OUT_PATH=$SUPERNOVA_PATH/target/dvb.m7221/tmp_image
ANDROID_PRODUCT_OUT_PATH=$ROOT_PATH/out/target/product/CV8386H_A
BOARD_INI_PATH=$SUPERNOVA_OUT_PATH/tvconfig/config/model
PANEL_PATH=$SUPERNOVA_OUT_PATH/tvconfig/config/panel
PQ_PATH=$SUPERNOVA_OUT_PATH/tvconfig/config/pq
DLC_INI_PATH=$SUPERNOVA_OUT_PATH/tvconfig/config/DLC
DATABASE_PATH=$SUPERNOVA_OUT_PATH/tvdatabase/Database
LOGO_PATH=$SUPERNOVA_OUT_PATH/tvconfig
CUSTOMER_MAKE_FILE=$ROOT_PATH/hht/hht_product_customized.mk
SET_CONFIG_FILE=$ROOT_PATH/vendor/mstar/product/m7221/images/prebuilts/scripts/set_config
SINGLEMODE_FILE=singleMode.txt


function prepare_build()
{
    rm -rf $PANELFILES_PATH
    mkdir $PANELFILES_PATH
}

function copy_files()
{
    cp -vf $BOARD_INI_PATH/Customer_1.ini $PANELFILES_PATH/
    cp -vf $PANEL_PATH/UD_VB1_8LANE_M28DGJ_L30.ini $PANELFILES_PATH/
    cp -vf $PQ_PATH/Main.bin $PANELFILES_PATH/
    cp -vf $PQ_PATH/Main_Text.bin $PANELFILES_PATH/
    cp -vf $PQ_PATH/Main_Color.bin $PANELFILES_PATH/
    cp -vf $PQ_PATH/Main_Color_Text.bin $PANELFILES_PATH/
    cp -vf $DLC_INI_PATH/DLC.ini $PANELFILES_PATH/
    cp -vf $DATABASE_PATH/user_setting.db $PANELFILES_PATH/
    cp -vf $DATABASE_PATH/factory.db $PANELFILES_PATH/
    cp -vf $LOGO_PATH/boot0.jpg $PANELFILES_PATH/
    cp -vf $ANDROID_PRODUCT_OUT_PATH/system/media/bootanimation.zip $PANELFILES_PATH/

    singleModeValue=`sed -n '/setenv singleMode/p' $SET_CONFIG_FILE|awk -F" " '{print $3}'`
    echo "singleModeValue:$singleModeValue"
    echo $singleModeValue > $PANELFILES_PATH/$SINGLEMODE_FILE
}

function make_zipfile()
{
    productModel=`sed -n '/^[^#]*PRODUCT_MODEL/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelId=`sed -n '/^[^#]*PANEL_ID/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelModelPart1=`sed -n '/^[^#]*PANEL_MODEL_PART1/p' $CUSTOMER_MAKE_FILE |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelModelPart2=`sed -n '/^[^#]*PANEL_MODEL_PART2/p' $ROOT_PATH/hht/product/$panelId/version.txt |awk -F":=" '{print $1$2}'|awk '{print $2}'`
    panelModel="$panelModelPart1"."$panelModelPart2"
    zip_file=PanelFiles_"$productModel"_"$panelModel".zip
    echo "zip_file:$zip_file"

    cd $IMAGES_PATH/PanelFiles/
    rm -f $IMAGES_PATH/$zip_file
    zip -qry $IMAGES_PATH/$zip_file ./*
    if [ -f $IMAGES_PATH/$zip_file ];then
        echo "created $zip_file"
    else
        echo "failed to create $zip_file"
    fi
}

prepare_build
copy_files
make_zipfile
