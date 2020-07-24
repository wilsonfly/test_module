#!/bin/sh

echo "ROOT_PATH:$ROOT_PATH"
echo "ANDROID_BUILD_TOP:$ANDROID_BUILD_TOP"

LOGO_PATH=$ANDROID_BUILD_TOP/vendor/mstar/supernova/projects/board/INI/misc/
BOOTANIMATION_PATH=$ANDROID_BUILD_TOP/device/mstar/almond/preinstall/customer/data/

function customize_bootanim()
{
    echo "====customize_bootanim begin===="
    cp -vf $ANDROID_BUILD_TOP/hht/bootanim/bootanimation.zip $BOOTANIMATION_PATH/bootanimation.zip
}

function customize_logo()
{
    echo "====customize_logo begin===="
    cp -vf $ANDROID_BUILD_TOP/hht/bootanim/boot0.jpg $LOGO_PATH/boot0.jpg
}

function customize_all()
{
    customize_logo
    customize_bootanim
}


###
###main
###
if [ "$#" -eq 0 ];then
   customize_all
else
    case "$1" in
        all)
            customize_all
            ;;
        logo)
            customize_logo
            ;;
        bootanim)
            customize_bootanim
            ;;
        *)
            echo "invalid param:[$*], do nothing."
            exit 1
            ;;
        esac
fi
