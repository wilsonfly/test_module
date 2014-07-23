#!/bin/sh

#set -x
#set -n
#set -v

if [ $# -ne 1 ]; then
	echo "invalid params !!!"
	echo "input the dir where the files in,such as:"
	echo "sample: ./md5sum.sh ./8940L"
	exit
fi

dir=$1
echo "dir:$dir"
count=0

for line in `find $dir -type f`
do
	md5sum $line
    ((count++))
done

echo "file nums:$count"
exit


######## old version #########
for line in `find $dir`
do
	md5sum $line
done

exit


######### old version #########
for line in `ls $dir`
do
	md5sum $dir/$line
done

exit


######### also works #########
ls $dir | while read line
do
	md5sum $dir/$line
done

exit



############old version##########
#ls *.sh | sh md5sum.sh
while read line
do
	md5sum $line
done

