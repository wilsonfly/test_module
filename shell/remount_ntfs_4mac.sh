##another way to umount
##hdiutil eject /dev/disk2s1

sudo umount /dev/disk2s1
sudo mkdir /Volumes/mymnt
sudo mount_ntfs -o rw,nobrowse /dev/disk2s1 /Volumes/mymnt
