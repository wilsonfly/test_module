#!/bin/sh
#set -x
### Vars Define
up="19"
down="20"
count="0"
#cmd="input keyevent"
cmd="echo"
flag="0"

### Main Logic
while true
do
	if [ $flag -ne 0 ];then
		${cmd} ${up}
		sleep 1
	else
		${cmd} ${down}
		sleep 1
	fi
	
	(( count++ ))
	echo "num:$count"

	if [ $count -ge 3 ];then
		count=0
		(( flag=!flag ))
	fi
done

