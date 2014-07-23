#!/bin/sh

str="this is a string, a test string"
echo "origin:$str"

suffix=${str##*is}
echo "back part:$suffix"
suffix=${str#*is}
echo "back part:$suffix"
suffix=${str#this*test}
echo "back part after this*test:$suffix"

prefix=${str%%string}
echo "front part:$prefix"
prefix=${str%%string}
echo "front part:$prefix"

suffix=`echo $str | awk -F, '{print $2;}'`
echo "behind ,:$suffix"
suffix=`echo $str | awk -F"string," '{print $2}'`
echo "behind 'string,':$suffix"


mid=${str:1:3}
echo "mid 1-3:$mid"
mid=`echo $str | cut -b 1-3`
echo "mid 1-3:$mid"
mid=`echo $str | cut -b 1,3`
echo "mid 1,3:$mid"
mid=`echo $str | cut -b -2,7`
echo "mid -2,7:$mid"



