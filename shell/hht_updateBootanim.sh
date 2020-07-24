#!/system/bin/sh

###1.U盘中update_bootanimation目录为标记,插入U盘时检测有此目录则尝试更新logo/bootanimation
###2.标记目录中可以全是图片(jpg/png),如果有logo.jpg文件则将此文件作为logo进行替换,其余作为bootanimation/part0/中内容来更新bootanimation
###3.标记目录中如果同时有part0/part1两个目录,则认为需要将这两个目录作为bootanimation的两个阶段内容来更新bootanimation,此时不考虑标记目录下图片
###4.标记目录中如果有desc.txt文件,则用此文件,不生成desc.txt.
###5.默认生成的desc.txt中指定图片分辨率为1920x1080,播放频率30.如果只有part0则无间隔无限重复,如果有part0/part1则part0无间隔播放1次part1无间隔无限播放.如果用此默认参数效果不佳,则提供desc.txt
###6.更新logo在/tvconfig/boot0.jpg,更新bootanimation在/factory/bootanim/bootanimation.zip
###7.logo/bootanimation两者之一有更新则自动重启,重启后能看到更新后的效果
###8.更新过后U盘可能仍然插在大屏上,再次触发升级时会比对新旧logo的MD5,如果相同则不再更新logo;比对bootanimation中图片的MD5,如果标记目录中有desc.txt也比对此文件,如果相同则不更新bootanimation
###9.logo和bootanimation可以单独更新,desc.txt不可单独更新需要有图片文件作为前提

magic_path=update_bootanimation
target_path=/factory/bootanim
logfile=$target_path/Upbootanim_log.txt
lastlogfile=$target_path/Upbootanim_log.no1.txt
target_logo=/tvconfig/boot0.jpg
target_zip=$target_path/bootanimation.zip
system_zip=/system/media/bootanimation.zip
tmp_zipname=bootanimation_tmp.zip
tmp_path=$target_path/Upbootanim_tmp
bootanim_type="picture"
willUpdateLogo=false
willUpdateBootanim=false
useCustomDesc=false
usb_path=""

function update_logo()
{
    if [ "$willUpdateLogo" == false ];then
        echo "[update_logo]do not update logo !" >> $logfile
        return
    fi

    cd $tmp_path/new_logo/

    ###remount /tvconfig to rw
    mount -o rw,remount /tvconfig
    sleep 1

    ###save some space for new logo and backup old one
    ###the selabel would change if be deleted, so just replace old one with null file
    #mv $target_logo ./
    cp $target_logo ./
    touch fake_logo
    cp fake_logo $target_logo
    sync

    cp logo.jpg $target_logo
    if [ $? -ne 0 ]; then
        echo "[update_logo]failed to update boot0.jpg! Maybe logo.jpg is too large" >> $logfile
        cp fake_logo $target_logo
        mv boot0.jpg $target_logo
        rm logo.jpg
        return
    fi

    ###remount /tvconfig to ro
    sync
    sleep 1
    mount -o ro,remount /tvconfig

    hht_setenv BootlogoFile boot0.jpg
    echo "[update_logo]updated boot0.jpg in /tvconfig !" >> $logfile
}

function update_bootanimation()
{
    if [ "$willUpdateBootanim" == false ];then
        echo "[update_bootanimation]do not update bootanim !" >> $logfile
        return
    fi

    cd $tmp_path

    if [ "$bootanim_type" == "picture" ];then
        if [ "$useCustomDesc" == false ];then
            echo "1920 1080 30" > desc.txt
            echo "p 0 0 part0" >> desc.txt
            echo "" >> desc.txt
        fi

        mkdir $tmp_path/part0
        mv $tmp_path/new_bootanim/* $tmp_path/part0/

        minizip -0 $tmp_path/$tmp_zipname desc.txt part0/*
    else
        if [ "$useCustomDesc" == false ];then
            echo "1920 1080 30" > desc.txt
            echo "p 1 0 part0" >> desc.txt
            echo "p 0 0 part1" >> desc.txt
            echo "" >> desc.txt
        fi

        mv $tmp_path/new_bootanim/part0/ $tmp_path
        mv $tmp_path/new_bootanim/part1/ $tmp_path

        minizip -0 $tmp_path/$tmp_zipname desc.txt part0/* part1/*
    fi

    if [ $? -eq 0 ]; then
        cp -v $tmp_path/$tmp_zipname $target_zip
        chmod 644 $target_zip
        echo "[update_bootanimation]updated bootanimation.zip in /factory/system/ !" >> $logfile
    else
        echo "[update_bootanimation]create zip failed !" >> $logfile
    fi
}

function get_files_ready()
{
    rm -rf $tmp_path
    mkdir -p $tmp_path/new_bootanim
    mkdir -p $tmp_path/new_logo

    if [ -d $usb_path/part0 ] && [ -d $usb_path/part1 ];then
        bootanim_type="dir"
    else
        bootanim_type="picture"
    fi
    echo "[get_files_ready]bootanim_type:$bootanim_type" >> $logfile

    if [ "$bootanim_type" == "picture" ];then
        cp $usb_path/*.jpg $tmp_path/new_bootanim/
        cp $usb_path/*.png $tmp_path/new_bootanim/

        if [ -f $tmp_path/new_bootanim/logo.jpg ];then
            mv $tmp_path/new_bootanim/logo.jpg $tmp_path/new_logo/
            echo "[get_files_ready]logo.jpg exist" >> $logfile
            willUpdateLogo=true
        fi

        cd $tmp_path/new_bootanim/
        num_png=$(ls *.png|wc -l)
        num_jpg=$(ls *.jpg|wc -l)
        num=$(($num_png + $num_jpg))
        if [ $num -eq 0 ];then
            echo "[get_files_ready]there are none pictures for bootanim" >> $logfile
        else
            echo "[get_files_ready]num_png:$num_png, num_jpg:$num_jpg" >> $logfile
            willUpdateBootanim=true
        fi
    else
        cp -r $usb_path/part0 $tmp_path/new_bootanim/
        cp -r $usb_path/part1 $tmp_path/new_bootanim/

        if [ -f $usb_path/logo.jpg ];then
            cp $usb_path/logo.jpg $tmp_path/new_logo/
            echo "[get_files_ready]logo.jpg exist" >> $logfile
            willUpdateLogo=true
        fi

        cd $tmp_path/new_bootanim/part0
        num_png=$(ls *.png|wc -l)
        num_jpg=$(ls *.jpg|wc -l)
        num0=$(($num_png + $num_jpg))
        cd $tmp_path/new_bootanim/part1
        num_png=$(ls *.png|wc -l)
        num_jpg=$(ls *.jpg|wc -l)
        num1=$(($num_png + $num_jpg))
        num=$(($num0 + $num1))
        if [ $num0 -eq 0 ] || [ $num1 -eq 0 ];then
            echo "[get_files_ready]empty dir, pictures in part0:$num0, pictures in part1:$num1" >> $logfile
        else
            echo "[get_files_ready]pictures in part0:$num0,pictures in part1:$num1" >> $logfile
            willUpdateBootanim=true
        fi
    fi

    if [ -f $usb_path/desc.txt ];then
        echo "[get_files_ready]$usb_path/desc.txt exists" >> $logfile
        cp $usb_path/desc.txt $tmp_path
        useCustomDesc=true
    fi
}

function check_difference()
{
    ### check logo
    if [ "$willUpdateLogo" == true ];then
        cd $tmp_path/new_logo
        new_logo_md5=$(md5sum logo.jpg | cut -d" " -f1)
        old_logo_md5=$(md5sum /tvconfig/boot0.jpg | cut -d" " -f1)
        if [ "$new_logo_md5" == "$old_logo_md5" ];then
            echo "[check_difference]md5sum of new_logo and old_logo are same: $new_logo_md5" >> $logfile
            willUpdateLogo=false
        else
            echo "[check_difference]md5sum of new_logo:$new_logo_md5, old_logo: $old_logo_md5" >> $logfile
        fi
    else
        echo "[check_difference]do not need to check logo" >> $logfile
    fi


    ### check bootanim
    if [ "$willUpdateBootanim" == false ];then
        echo "[check_difference]do not need to check bootanim,just return" >> $logfile
        return
    fi

    if [ "$bootanim_type" == "picture" ];then
        cd $tmp_path/new_bootanim/
        md5sum * > $tmp_path/new_pic.md5.txt
    else
        cd $tmp_path/new_bootanim/part0/
        md5sum * > $tmp_path/new_pic.md5.txt
        cd $tmp_path/new_bootanim/part1/
        md5sum * >> $tmp_path/new_pic.md5.txt
    fi

    mkdir $tmp_path/old_bootanim/
    cd $tmp_path/old_bootanim/
    if [ -f $target_zip ]; then
        old_zip=$target_zip
    else
        old_zip=$system_zip
    fi
    busybox unzip $old_zip -d $tmp_path/old_bootanim/
    cd $tmp_path/old_bootanim/part0
    md5sum * > $tmp_path/old_pic.md5.txt
    if [ -d $tmp_path/old_bootanim/part1 ];then
        cd $tmp_path/old_bootanim/part1
        md5sum * >> $tmp_path/old_pic.md5.txt
    fi

    ### check desc.txt if useCustomDesc=true
    if [ "$useCustomDesc" == true ];then
        cd $tmp_path
        md5sum desc.txt >> $tmp_path/new_pic.md5.txt
        cd $tmp_path/old_bootanim
        md5sum desc.txt >> $tmp_path/old_pic.md5.txt
    fi

    cd $tmp_path
    new_pic_md5=$(md5sum new_pic.md5.txt | cut -d" " -f1)
    old_pic_md5=$(md5sum old_pic.md5.txt | cut -d" " -f1)

    echo "======new_pic_md5======" >> $logfile
    cat $tmp_path/new_pic.md5.txt  >> $logfile
    echo "======old_pic_md5======" >> $logfile
    cat $tmp_path/old_pic.md5.txt  >> $logfile

    if [ "$new_pic_md5" == "$old_pic_md5" ];then
        echo "[check_difference]md5sum of pictures is same with $old_zip, do nothing!" >> $logfile
        willUpdateBootanim=false
    else
        echo "[check_difference]md5sum of pictures is different with $old_zip, will do update!" >> $logfile
    fi
}

function get_usb_path()
{
    usbdirnamelist=$(ls /mnt/usb/)
    echo "usbdirnamelist=$usbdirnamelist"
    for usbname in $usbdirnamelist
    do
        echo "usbname=$usbname"
        if [ -d /mnt/usb/$usbname/$magic_path ];then
            echo "[get_usb_path]in $usbname, $magic_path exist!" >> $logfile
            usb_path=/mnt/usb/$usbname/$magic_path
            break
        else
            echo "[get_usb_path]in $usbname, $magic_path not exist!" >> $logfile
            usb_path=""
        fi
    done

    if [ "$usb_path" == "" ];then
        echo "[get_usb_path]$magic_path not found at all !" >> $logfile
        exit 1
    fi
}

function clean_tmp_files()
{
    rm -rf $tmp_path
}

function wait_for_udisk_ready()
{
    mv $logfile $lastlogfile
    date > $logfile

    ###files can be accessed about 6s later after UsbHostManager/endUsbDeviceAdded
    sleep 10
}

function try_to_reboot()
{
    if [ "$willUpdateLogo" == true ] || [ "$willUpdateBootanim" == true ];then
        sync
        sleep 1
        reboot
    fi
}

wait_for_udisk_ready
get_usb_path
get_files_ready
check_difference
update_logo
update_bootanimation
clean_tmp_files
try_to_reboot

