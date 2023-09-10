

cmdDelete="rm patched vendor/androiddrm -f"
cmdCopy="cp 00FilesToWindows*/android_q_aosp_fusion.sh vendor/mediatek/proprietary_tv/limit_open/custom/mtk/mtk/aosp_patch_script/android_q_aosp_fusion.sh"
cmdApply="sh  vendor/mediatek/proprietary_tv/apollo/linux_mts/build/mak/android/android_q_base_build_fusion.sh"

echo $cmdDelete
echo $cmdCopy
echo $cmdApply

$cmdDelete
$cmdCopy
if [ "$?" != 0 ];then
    echo "=======cp failed!========"
    exit
fi
$cmdApply
