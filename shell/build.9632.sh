
function showUsage()
{
    echo "Usage:"
    echo "./build.sh target [user|userdebug]"
    echo "e.g."
    echo "./build.sh all"
    echo "./build.sh all-ota"
    echo "./build.sh mi_all(vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/OUT/ko)"
    echo "./build.sh utopia"
    echo "./build.sh android"
    echo "./build.sh kernel"
    echo "./build.sh packing"
    echo "./build.sh an-packing(android and packing)"
    echo "./build.sh clean"
    exit
}

if [ "$#" == 0 ];then
    echo "invalid target!!!"
    showUsage
fi

jobs=32
product="OPPOCNM632"
lunchTarget=""

target=$1
variant=$2
case "$variant" in
    "userdebug")
        lunchTarget="$product"-"$variant"
        ;;
    "user")
        lunchTarget="$product"-"$variant"
        ;;
    *)
        if [ "$variant" != "" ];then
            echo "invalid variant!!!"
            showUsage
        else
            ##default build userdebug
            lunchTarget="$product"-userdebug
        fi
        ;;
esac

echo "####build target[$target]"
echo "####lunch $lunchTarget"

source build/envsetup.sh
#lunch OPPOCNM632-userdebug
lunch "$lunchTarget"

case $target in 
    "all")
        echo "####building mtk_build####"
        make -j$jobs RLS_CUSTOM_BUILD=true mtk_build
        ;;
    "all-ota")
        echo "####building mtk_build with ota####"
        make -j$jobs RLS_CUSTOM_BUILD=true ENABLE_OTA=true mtk_build
        ;;
    "mi-all")
        echo "####building mi_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true mi_all
        ;;
    "utopia")
        echo "####building utopia####"
        make -j$jobs RLS_CUSTOM_BUILD=true utopia_all
        ;;
    "android")
        echo "####building android_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true android_all
        ;;
    "kernel")
        echo "####building kernel_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true mi_kernel_all packing_all
        ;;
    "packing")
        echo "####building android_all packing_all####"
        #make -j$jobs RLS_CUSTOM_BUILD=true android_all packing_all
        make -j$jobs RLS_CUSTOM_BUILD=true packing_all
        ;;
    "an-packing")
        echo "####building android_all packing_all####"
        make -j$jobs RLS_CUSTOM_BUILD=true android_all packing_all
        ;;
    "clean")
        echo "####building mtk_clean####"
        make -j$jobs RLS_CUSTOM_BUILD=true mtk_clean
        ;;
esac

