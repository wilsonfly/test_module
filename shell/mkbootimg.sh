#!/bin/sh
if [ $# -ne 2 ]
then
	echo "invalid params !!!"
	echo "input the uImage and ramdisk.img, such as:"
	echo "./mkbootimg.sh ./uImage ./ramdisk.img"
	exit
fi

build_path="/home/sunhuasheng/projects_code/sub_system_8040E/build"
uImage_file=$1
ramdisk_file=$2
echo "uImage     : $uImage_file"
echo "ramdisk.img: $ramdisk_file"

set -v

$build_path/linux-x86/bin/mkbootimg --kernel $uImage_file --ramdisk $ramdisk_file --cmdline "mem=335M vmalloc=400M console=huasheng,115200 lpj=5996758 mtddev=blackbox androidboot.console=huasheng mmz=ddr,0,0x9D600000,42M mtdparts=hi_emmc:"  --base 0x4000  --output boot.img
