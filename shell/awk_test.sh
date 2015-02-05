#!/bin/sh

##matching
awk 'BEGIN{FS=":"}/huasheng/{print $0}' /etc/passwd
full_line=`awk 'BEGIN{FS=":"}/huasheng/{print $0}' /etc/passwd `
echo "full:$full_line"

##matching
awk 'BEGIN{FS=":"}/huasheng/{print $1}' /etc/passwd
first_name=`awk 'BEGIN{FS=":"}/huasheng/{print $1}' /etc/passwd `
echo "first:$first_name"

##matching more than one
awk 'BEGIN{FS=":";OFS="^"}/huasheng/{print $0,$1}' /etc/passwd
full_line=`awk 'BEGIN{FS=":";OFS="^"}/huasheng/{print $0,$1}' /etc/passwd | awk 'BEGIN{FS="^"}{print $1}'`
first_name=`awk 'BEGIN{FS=":";OFS="^"}/huasheng/{print $0,$1}' /etc/passwd | awk 'BEGIN{FS="^"}{print $2}'`
echo "full:$full_line"
echo "first:$first_name"

##environment
awk 'BEGIN{print ENVIRON["PATH"];}'
#awk 'BEGIN{for(i in ENVIRON){print i"=="ENVIRON[i]}}'

##if-else
awk 'BEGIN{a="11";if(a >= "10"){print "\"11\">=\"10\" ok";}else {print "\"11\">=\"10\" false"}}'
awk 'BEGIN{a="11";if(a >= 9){print "\"11\">=9 ok";}else {print "\"11\">=9 false"}}'
awk 'BEGIN{a=11;if(a >= 9){print "11>=9 ok";}else {print "11>=9 false"}}'

##calculate(non_number turn to 0 in calculating)
awk 'BEGIN{a="b";print a++,++a;}'
awk 'BEGIN{a=3;print a++,++a;}'

##operator in (???not understand yet????)
awk 'BEGIN{a="b";arr[0]="b";arr[1]="c";print (a in arr);}'
awk 'BEGIN{a="b";arr[0]="b";arr["b"]="c";print (a in arr);}'

##string2number(string2num when connect with '+',ignore the rest when match non_number in the string)
awk 'BEGIN{a="100";b="10test10";print (a+b+0);}' 

##number2string(just connected with "")
awk 'BEGIN{a=100;b=100;c=(a""b);print c}'

##string connecting
awk 'BEGIN{a="a";b="b";c=(a""b);print c}'
awk 'BEGIN{a="a";b="b";c=(a+b);print c}'

##read from a file
awk -F:= '$1=="BUILD_PRODUCT_IMAGE "' "/home/sunhuasheng/tools/build_id.mk"
awk -F:= '$1=="BUILD_PRODUCT_IMAGE " {print $1 $2}' "/home/sunhuasheng/tools/build_id.mk"
awk -F:= '$1=="BUILD_PRODUCT_IMAGE " {print $1 $2}' "/home/sunhuasheng/tools/build_id.mk" | awk '{print $2}'
awk -F:= '$1=="BUILD_PRODUCT_IMAGE " {print $2}' "/home/sunhuasheng/tools/build_id.mk"

##FS:Field Separator���ֶηָ���, the separeter in origin string
echo "adc|zzz|yyy"|awk 'BEGIN{FS="|"}{print $0}'
echo "adc|zzz|yyy"|awk 'BEGIN{FS="|"}{print $2}'
echo "adc||zzz|yyy"|awk 'BEGIN{FS="|"}{print $0}'
echo "adc||zzz|yyy"|awk 'BEGIN{FS="|"}{print $2}'
echo "adc||zzz|yyy"|awk 'BEGIN{FS="[|]+"}{print $2}' #use regular expression
echo "adc||zzz|yyy"|awk 'BEGIN{FS=""}{print $0}'
echo "adc|zzz|yyy"|awk 'BEGIN{FS=""}{NF++;print $0}'

#RS:Record Separator����¼�ָ���, separete the string and the separeter is RS, default \n
awk 'BEGIN{RS="\n"}{print $0}' ~/tools/awk_test1.txt
awk '{print $0}' ~/tools/awk_test1.txt
awk 'BEGIN{RS="|"}{print $0}' ~/tools/awk_test2.txt
echo "111 222a333 444b555 666"|awk 'BEGIN{RS="[a-z]+"}{print $1,RS,RT}'
echo "111 222a333 444b555 666"|awk 'BEGIN{RS="[a-z]+"}{print $0}'
awk 'BEGIN{RS=""}{print "<",$0,">"}'  ~/tools/awk_test1.txt #��RSΪ��ʱ��awk���Զ��Զ�������Ϊ�ָ��
awk 'BEGIN{RS="444";}{print $2,$3}' ~/tools/awk_test1.txt #RS���趨�ɷ�\nʱ��\n���FS�ָ���е�һ��

##ORS:Output Record Separate�������ǰ��¼�ָ���, RS�������, default \n
awk 'BEGIN{ORS="|"}{print $0}' ~/tools/awk_test1.txt
echo ""

##OFS:Out of Field Separator������ֶηָ���
awk 'BEGIN{FS="|";OFS="#"}{print $1,$2}'  ~/tools/awk_test2.txt #$1,$2... ֮�������滻��Ч
