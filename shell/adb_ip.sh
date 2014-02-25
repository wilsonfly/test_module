#!/bin/sh
if [ $# -ne 1 ]
then
	echo "invalid params"
	echo "exsample: ./adb_ip.sh 172.16.6.117"
	exit
fi
echo "$#"

set -v
adb disconnect $1
adb connect $1
adb remount
