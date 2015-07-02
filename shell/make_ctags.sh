#!/bin/sh
if [ $# -eq 0 ]; then
    echo "invalid params !!!"
    echo "Usage:"
    echo "input the at max 8 dirNames where the files in, such as:"
    echo "make_ctags.sh ./packages/apps/ frameworks/"
    exit
fi

#find ./device/ ./frameworks/ ./hardware/ ./system/core/ -name *.h -o -name *.c -o -name *.cpp > mytags.files

#cmd_find="find $1 $2 $3 $4 $5 $6 $7 $8 -name *.h -o -name *.c -o -name *.cpp -o -name *.java > mytags.files"
#cmd_ctags="ctags -f tags -L mytags.files"
#echo ${cmd_find}
#echo ${cmd_ctags}
#${cmd_find}
#${cmd_ctags}

set -v
find $1 $2 $3 $4 $5 $6 $7 $8 -name *.h -o -name *.c -o -name *.cpp -o -name *.java > mytags.files
ctags -f tags -L mytags.files
