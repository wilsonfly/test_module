#!/bin/sh

clear
cols=`tput cols`
lines=`tput lines`

tput cup 0 0
echo lesftop

tput cup 0 `expr $cols -  8`
echo righttop

tput cup  `expr $lines ` 0
echo -n leftend

tput cup  `expr $lines` `expr $cols - 8`
echo -n rightend

tput cup  `expr $lines / 2` `expr $cols / 2 - 4`
echo wilsonflying

read
clear
