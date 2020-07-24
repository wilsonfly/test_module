#!/bin/sh

var=true
if [ "$var" = true ]; then
    echo "1"
fi
if [ "$var" = "true" ]; then
    echo "2"
fi
if [[ "$var" = true ]]; then
    echo "3"
fi
if [[ "$var" = "true" ]]; then
    echo "4"
fi
if [[ "$var" == true ]]; then
    echo "5"
fi
if [[ "$var" == "true" ]]; then
    echo "6"
fi
if test "$var" = true; then
    echo "7"
fi
if test "$var" = "true"; then
    echo "8"
fi



if [ "$var_notDefine" = true ]; then
    echo "var_notDefine true"
else
    echo "var_notDefine false"
fi

var_nullstring=''
if [ "$var_nullstring" = true ]; then
    echo "var_nullstring true"
else
    echo "var_nullstring false"
fi

var_noValue=
if [ "$var_no" = true ]; then
    echo "var_noValue true"
else
    echo "var_noValue false"
fi

var_unset="abcdef"
unset var_unset
if [ "$var_unset" = true ]; then
    echo "var_unset true"
else
    echo "var_unset false"
fi
