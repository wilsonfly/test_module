#!/bin/sh
set -v
make_ext4fs -s -l 335544320 -a system system.img system
/home/sunhuasheng/projects_code/android_8040C/out/target/product/godbox/mksystemext4
