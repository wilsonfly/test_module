#!/bin/sh
set -v
rm 8865_rootfs.img
mksquashfs rootfs/ 8865_rootfs.img
cp 8865_rootfs.img  /mnt/hgfs/share/
