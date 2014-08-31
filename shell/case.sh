#!/bin/sh

#
#something is still wrong !!!
#

no1=0
no2=0
no3=0

x="a bb A BB 0 11"
echo "x: $x"

for word in $x
do 
	case $word in 
		[a-z]*) 
		#[a-z]{1,}) 
			no1=`expr $no1 + 1`
			echo "no1: $no1"
			;;
		[A-Z]*) 
		#[A-Z]{1,}) 
			no2=`expr $no2 + 1`
			echo "no2: $no2"
			;;
		#[0-9]{1,}) 
		[0-9]*) 
			no3=`expr $no3 + 1`
			echo "no3 :$no3"
			;;
	esac
done

echo "[a-z]: $no1"
echo "{A-Z}: $no2"
echo "[0-9]: $no3"

