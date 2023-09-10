
function build_uboot()
{
    echo "====build_uboot===="
    cd $root_path/u-boot/
    make clean
    make mrproper
    ./make.sh evb-px30
}

function build_kernel()
{
    echo "====build_kernel===="
    cd $root_path
    rm kernel/*.img
    rm out/target/product/px30_evb/boot.img
    rm out/target/product/px30_evb/kernel

    cd $root_path/kernel
    make ARCH=arm64 rockchip_defconfig
    make ARCH=arm64 px30-evb-ddr3-v10.img -j4
}

function build_android()
{
    echo "====build_android===="
    cd $root_path
    source build/envsetup.sh
    lunch px30_evb-userdebug
    make -j32
}

function build_image()
{
    echo "====build_image===="
    cd $root_path
    ./mkimage.sh ota
}

function showUsage()
{
    echo "Usage:"
    echo "[${0##*/} uboot] to build uboot"
    echo "[${0##*/} kernel] to build kernel"
    echo "[${0##*/} android] to build android"
    echo "[${0##*/} image] to build image"
    echo "[${0##*/} all] to build all"
}

root_path=`pwd`
echo "$root_path"

cmd=$1
case "$cmd" in
    u-boot* | uboot*)
        build_uboot
        ;;
    kernel*)
        build_kernel
        ;;
    android)
        build_kernel
        ;;
    image)
        build_image
        ;;
    all)
        build_uboot
        build_kernel
        build_android
        build_image
        ;;
    *)
        showUsage
        ;;
esac
