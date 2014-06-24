#!/bin/sh
no1=0
no2=0
no3=0
no4=0
x=`cat 22.txt`
#echo "$no1"
echo "x: $x"
for word in $x
do 
	case $word in 
		[0-9]+) 
		no1=`expr $no1 + 1`
		echo "no1 :$no1"
		;;
		[a-z]+) 
		no2=`expr $no2 + 1`
		echo "no2: $no2"
		;;
		[A-Z]+) 
		no3=`expr $no3 + 1`
		echo "no3: $no3"
		;;
	esac
done
		echo "$no1"
		echo "$no2"
		echo "$no3"
	
