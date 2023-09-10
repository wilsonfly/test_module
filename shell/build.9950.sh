
if [ "$#" == 0 ];then
    echo "Please add one build target"
    echo "Usage:"
    echo "./build.sh all"
    echo "./build.sh android"
    echo "./build.sh driver"
    echo "./build.sh bootimage"
    echo "./build.sh uboot"
    echo "./build.sh dtv_svc"
    echo "./build.sh 3rd"
    echo "./build.sh packing"
    echo "./build.sh clean"
    exit
fi
cmd=$1
echo "####build target:$cmd"

jobs=12

sh vendor/mediatek/proprietary_tv/apollo/linux_mts/build/mak/android/android_p_cn_build_SQA_rls.sh
source build/envsetup.sh
#lunch mt9950_cn_oppo-user
lunch mt9950_cn_oppo-userdebug

case $cmd in 
    "all")
        echo "####building mtk_build####"
        make -j$jobs RLS_CUSTOM_BUILD=true mtk_build
        ;;
    "android")
        echo "####building android_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true android_all
        ;;
    "driver")
        echo "####building driver_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true driver_all
        ;;
    "bootimage")
        echo "####building bootimage_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true bootimage_all
        ;;
    "uboot")
        echo "####building uboot_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true uboot_all
        ;;
    "dtv_svc")
        echo "####building dtv_svc_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true dtv_svc_all
        ;;
    "3rd")
        echo "####building 3rd_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true 3rd_all
        ;;
    "packing")
        echo "####building packing_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true packing_all
        ;;
    "clean")
        echo "####building mtk_clean####"
        make -j$jobs RLS_CUSTOM_BUILD=true mtk_clean
        ;;
esac


