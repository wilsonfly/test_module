#!/bin/sh

rm rootfs/lib/modules/linux-2.6.35-hi3716Cv200/misc/load
rm rootfs/lib/modules/linux-2.6.35-hi3716Cv200/misc/rt3070sta.ko
rm rootfs/lib/modules/linux-2.6.35-hi3716Cv200/misc/rt5572sta.ko
rm rootfs/usr/lib/libHA.AUDIO.AC3.decode.so
rm rootfs/usr/lib/libHA.AUDIO.DTS.decode.so
rm rootfs/usr/lib/libHA.AUDIO.EAC3.decode.so
rm rootfs_A1_C/usr/lib/libHA.AUDIO.DOLBYPLUS.decode.so
rm rootfs_A1_C/usr/lib/libHA.AUDIO.TRUEHD.decode.so
rm rootfs_A1_C/usr/lib/libHA.AUDIO.FFMPEG_ADEC.decode.so
rm rootfs/usr/lib/libswtuner_AVL6211.so
rm 8845B_rootfs.img
mksquashfs rootfs/ 8845B_rootfs.img
