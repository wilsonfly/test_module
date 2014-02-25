#!/system/bin/sh
#set -x
### Vars Define
up="19"
down="20"
count="0"
cmd="input keyevent"
flag="0"
sleep_interval=10
count_max=13

### Main Logic
while true
do
	if [ $flag -ne 0 ];then
		${cmd} ${up}
		sleep $sleep_interval
	else
		${cmd} ${down}
		sleep $sleep_interval
	fi
	
	(( count++ ))
	#echo "num:$count"

	if [ $count -ge $count_max ];then
		count=0
		(( flag=!flag ))
	fi
done

