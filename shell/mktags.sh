#!/bin/sh

find . -name "*.h" -o -name "*.c" > cscope.files
cscope -Rbkq -i cscope.files
ctags -R
find . -name "*.[chs]" -print > cscope.files
