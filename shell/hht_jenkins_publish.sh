project_path="/home/sunhuasheng/Downloads/0716.shell"

##current path:/home/hitecloud
cd $project_path/tmp/
pwd && ls -l

target_file=`ls *.zip`
target_md5_file=md5.txt
param_file=parameter.txt
zipinfo_file=zipinfo.txt
nothing_file=nothing_published.txt
echo "target_file:$target_file"

if [ ! -f "$param_file" ];then
        echo "$param_file get lost, do nothing here!"
        pwd && ls -l
        rm -rf $project_path/tmp
	exit 1
fi

JENKINS_BUILD_TIME=`sed -n "/^[^#]*JENKINS_BUILD_TIME/p" $param_file |cut -d= -f2`
PublishType=`sed -n "/^[^#]*PublishType/p" $param_file |cut -d= -f2`
Branch=`sed -n "/^[^#]*Branch/p" $param_file |cut -d= -f2`
theNetMode=`sed -n "/^[^#]*NetMode/p" $param_file |cut -d= -f2`
UartMode=`sed -n "/^[^#]*UartMode/p" $param_file |cut -d= -f2`
productModel=`sed -n "/^[^#]*productModel/p" $param_file |cut -d= -f2`
panelPath=$project_path/PanelFiles/$productModel
echo "JENKINS_BUILD_TIME=$JENKINS_BUILD_TIME PublishType=$PublishType Branch=$Branch theNetMode=$theNetMode $UartMode"

if [ "$Branch" != "" ] && [ "$Branch" != "master" ];then
    Target_path=$project_path/${JENKINS_BUILD_TIME}-$Branch
else
    Target_path=$project_path/$JENKINS_BUILD_TIME
fi
Target_path=$Target_path-$theNetMode
if [ "$UartMode" == "Uart" ];then
Target_path=$Target_path-$UartMode
fi
mkdir -p $Target_path

if [ -f "$nothing_file" ];then
    mv $nothing_file $Target_path
    rm -rf $project_path/tmp
    exit 0
fi

mkdir -p $panelPath/tmp

if [ "$PublishType" == "Integrated" ];then
    unzip $target_file
    cp *VCTL*.zip $panelPath/tmp
    mv $target_file $target_md5_file $zipinfo_file  $Target_path
else
    unzip $target_file -d $Target_path
    cp $Target_path/*VCTL*.zip $panelPath/tmp
fi

cd $panelPath/tmp
fullNameZip=`ls *.zip`
unzip $fullNameZip
cp PanelFiles_*.zip $panelPath
cd $panelPath
md5sum *.zip > md5.txt

rm -rf $panelPath/tmp
#rm -rf $project_path/tmp
