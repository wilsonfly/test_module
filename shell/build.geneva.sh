sh vendor/mediatek/tv/build/android/android_r_base_build_fusion.sh

source build/envsetup.sh && lunch Geneva_gtv-userdebug && m -j32 WEEKLY_BUILD_TYPE=PRETEST COMPILE_SYNC_BSP=true  BOARD_MMAP_SIZE=1.5G  2>&1 | tee make.log

source build/envsetup.sh && lunch Geneva_gtv-user && m -j32 WEEKLY_BUILD_TYPE=PRETEST COMPILE_SYNC_BSP=true SIGN_IMG_SUPPORT=true BOARD_MMAP_SIZE=1.5G 2>&1 | tee make.user.log

