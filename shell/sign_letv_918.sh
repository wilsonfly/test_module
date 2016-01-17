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

build_path="/home/sunhuasheng/project_code/mstar_6a918_branchus"
input_file=$1
suffix_name=${input_file##*.}
prefix_name=${input_file%".$suffix_name"}

#java -jar prebuilts/sdk/tools/lib/signapk.jar build/target/product/security/platform.x509.pem build/target/product/security/platform.pk8 ~/Downloads/GunWorld_unsigned_letv.apk GunWorld_signed_letv.ap

if [ ${suffix_name} == "zip" ]
then
    output_file="$prefix_name"_sign.zip
    cmd="java -Xmx2048m -jar $build_path/prebuilts/sdk/tools/lib/signapk.jar -w $build_path/build/target/product/security/platform.x509.pem $build_path/build/target/product/security/platform.pk8 $input_file $output_file"
else if [ ${suffix_name} == "apk" ]
then
    output_file="$prefix_name"_sign.apk
    cmd="java -jar $build_path/prebuilts/sdk/tools/lib/signapk.jar -w $build_path/build/target/product/security/platform.x509.pem $build_path/build/target/product/security/platform.pk8 $input_file $output_file"
else
    echo "Invalid param,need *.zip/*.apk "
    exit
fi
fi

echo "input : $input_file"
echo "output: $output_file"

echo $cmd
$cmd
