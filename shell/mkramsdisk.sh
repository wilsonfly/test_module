#!/bin/sh
if [ $# -ne 2 ]
then
	echo "invalid params !!!"
	echo "input the root/ and the path of ramdisk.img, such as:"
	echo "./mkramsdisk.sh ./resource/recovery/root  ./"
	exit
fi

build_path="/home/sunhuasheng/projects_code/sub_system_8040E/build"
root_path=$1
output_path=$2
echo "root_path  : $root_path"
echo "output_path: $output_path"

set -v
$build_path/linux-x86/bin/mkbootfs $root_path | $build_path/linux-x86/bin/minigzip  > $output_path/ramdisk.img
