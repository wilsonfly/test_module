#!/system/bin/sh

himm 0xF8A23028 0x07200000

count=0

while ((1))
do
    ((count=$count+1))
    echo "\n\n"
    echo "$0 is running, count:$count"
    date
#    himd.l 0xF8A23030 --> 0000:  98989898 98989898 00000000 00000000
    tmp1=`himd.l 0xF8A23030|busybox awk '{FS=" "}/0000:/{print $2}'`
    tmp2=${tmp1:(-2)}
    ((tmp3=0x${tmp2}))
    ((tmp=$tmp3*180/255-40))

    echo "0000: $tmp1"
    echo "cpu temperature:$tmp"

    sleep 3
done
