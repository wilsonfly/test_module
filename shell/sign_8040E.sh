#!/bin/sh
##set -x
if [ $# -eq 0 ]
then
	echo "invalid params !!!"
	echo "input the orgin zip name, such as:"
	echo "./sign.sh disabl_serial.zip"
	exit
fi

build_path="/home/sunhuasheng/projects_code/sub_system_8040E_tmiptv/build"
input_zip=$1
prefix_name=`echo "$input_zip" | cut -d'.' -f1`
#prefix_name=`echo "$input_zip" | cut -d".zip" -f1`
output_zip="$prefix_name"_sign.zip

echo "input : $input_zip"
echo "output: $output_zip"

#set -v
cmd="java -jar $build_path/linux-x86/framework/signapk.jar -w $build_path/ota/security/testkey.x509.pem $build_path/ota/security/testkey.pk8  $input_zip $output_zip"

echo $cmd
$cmd
