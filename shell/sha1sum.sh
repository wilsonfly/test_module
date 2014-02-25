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

for line in `find $dir -type f`
do
	sha1sum $line
done

exit
