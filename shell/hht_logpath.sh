#!/vendor/bin/sh

LOG_PATH=/data/Logs
RECOVERY_SOURCE=/cache/recovery
RECOVERY_LOG_PATH=$LOG_PATH/recovery
LOGDIR_NUM=3

###
### PROP_SAVE_LOG_SWITCH will take effect after the next power-up.
### PROP_SAVE_LOG_ENABLER will start/stop the logcat_service at once.
###
PROP_SAVE_LOG_SWITCH="persist.hht.save.logcat"
PROP_SAVE_LOG_ENABLER="service.logcat.enable"

# set default permission 0775
umask 002

# Create the folders if necessary
i=0
while [ "$i" -lt "$LOGDIR_NUM" ]
do
    mkdir -p $LOG_PATH/Log.$i
    i=$(($i+1))
done

# Transfer the files in folders
i=$(($LOGDIR_NUM-2))
j=$(($i+1))
rm -r $LOG_PATH/Log.$(($LOGDIR_NUM-1))/*
while [ "$i" -ge "0" ]
do 
    mv $LOG_PATH/Log.$i/*  $LOG_PATH/Log.$j
    j=$i
    i=$(($i-1))
done
rm -r $LOG_PATH/Log.0/*

# start/stop logcat service
savelog=$(getprop $PROP_SAVE_LOG_SWITCH)
if [ "$savelog" == "1" ]; then
    setprop $PROP_SAVE_LOG_ENABLER 1
else
    setprop $PROP_SAVE_LOG_ENABLER 0
fi

# Save the recovery log
mkdir -p $RECOVERY_LOG_PATH
rm $RECOVERY_LOG_PATH/*
cp $RECOVERY_SOURCE/last_* $RECOVERY_LOG_PATH/

