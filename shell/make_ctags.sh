#!/bin/sh

set -v
find ./device/ ./frameworks/ ./hardware/ ./system/core/ -name *.h -o -name *.c -o -name *.cpp > mytags.files
ctags -f tags -L mytags.files
rm mytags.files
