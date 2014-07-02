#!/bin/sh
##set -x
if [ $# -eq 0 ]
then
	echo "invalid params !!!"
	echo "input the name of orgin zip/apk, such as:"
	echo "./sign.sh disabl_serial.zip"
	echo "./sign.sh SWSetting.apk"
	exit
fi

build_path="/home/sunhuasheng/project_code/sub_system_8e40a_base/platform/build/tool/"
input_file=$1
suffix_name=${input_file##*.}
prefix_name=${input_file%".$suffix_name"}

if [ ${suffix_name} == "zip" ]
then
    output_file="$prefix_name"_sign.zip
    cmd="java -Xmx2048m -jar $build_path/linux-x86/framework/signapk.jar -w $build_path/security/testkey.x509.pem $build_path/security/testkey.pk8 $input_file $output_file"
else if [ ${suffix_name} == "apk" ]
then
    output_file="$prefix_name"_sign.apk
    cmd="java -jar $build_path/linux-x86/framework/signapk.jar $build_path/security/platform.x509.pem $build_path/security/platform.pk8  $input_file $output_file"
else
    echo "Invalid param,need *.zip/*.apk "
    exit
fi
fi

echo "input : $input_file"
echo "output: $output_file"

echo $cmd
$cmd
