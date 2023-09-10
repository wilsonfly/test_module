#!/bin/sh
if [ $# != 3 ];then
    echo "Usage:"
    echo "$0 old-target.zip new-target.zip old.cre.new.zip"
    exit
fi

old_target=$1
new_target=$2
incre_zip=$3

root_path=/data/sunhuasheng/prjCode/9632
cmd="$root_path/build/tools/releasetools/ota_from_target_files  -i $old_target $new_target $incre_zip"
echo $cmd
$cmd

