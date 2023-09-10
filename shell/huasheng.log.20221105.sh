

if [ "$1" == "0" ];then
    echo "====logcat -c===="
    logcat -c
elif [ "$1" == "1" ];then
    echo "====will echo libserial_tv_jni.so >> /system/etc/public.libraries.txt"
    echo libserial_tv_jni.so >> /system/etc/public.libraries.txt
elif [ "$1" == "2" ];then
    echo "====will show log about oppo/cvte at===="
    logcat |grep -E "FactorySettings|PictureBase|TvInfo|Gamma|FactorySettingsModule|OptvFactoryIniManager|SecurityKeys|FACAPIJAR-File|KONKAAT|GlobalKeyReceiver|TvFactoryService|SerialCom|Oem|CommandHandler|Gamma"
elif [ "$1" == "3" ];then
    echo "====will show log about GetBluetoothLeRssiCommandHandler===="
    logcat |grep -E "TvFactoryService|SerialComDataManager|Oem|Wifi|Bluetooth|GetBluetoothLeRssiCommandHandler|AbstractCommandHandler"
elif [ "$1" == "4" ];then
    echo "====start factorymenu===="
    am start -n com.heytap.optvfactory/.ui.FactoryMenuActivity
    am start-service -n com.cvte.fac.menu/com.cvte.fac.menu.app.TvMenuWindowManagerService
elif [ "$1" == "5" ];then
    echo "====start settings===="
    am start -n com.android.tv.settings/.MainSettings
elif [ "$1" == "6" ];then
    echo "====stty -F /dev/ttyS1 -hupcl 115200===="
    stty -F /dev/ttyS1 -hupcl 115200
elif [ "$1" == "7" ];then
    echo "====stty -F /dev/ttyS1 -a===="
    stty -F /dev/ttyS1 -a
elif [ "$1" == "8" ];then
    echo "====open tvapi debug===="
    touch /data/mtk_fapi_debug
    touch /data/tvfapi_hal_debug
elif [ "$1" == "9" ];then
    echo "====get screensaver/screenoff timeout===="
    echo "system-screen_off_timeout:"
    settings get system screen_off_timeout
	echo "system-attentive_timeout:"
    settings get secure attentive_timeout
	echo "system-sleep_timeout:"
    settings get secure sleep_timeout
elif [ "$1" == "10" ];then
    echo "====get provisioned/ff_enabled/ff_banned===="
    settings get global device_provisioned
    settings get global key_voice_control_enabled
    settings get global key_voice_control_banned
else
    echo "====will show not much log===="
    logcat -c
    logcat WificondScannerImpl:s RecommendedChannelsThread:s hwcomposer:s gralloc4:s FirebaseInstanceId:s Finsky:s chromium:s gralloc:s RMParser:s FlvExtractor:s ESQueue:s avc_utils:s MtkAC4Parser:s MKV:s MtkMPEG2PSExtractor:s FLACExtractor:s WAVExtractor:s RMExtractor:s ASFExtractor:s AACExtractor:s ESExtractor:s OgmExtractor:s AC3Extractor:s MPEG2TSExtractor:s ATSParser:s MtkMP4Extractor:s ExifInterface:s com.heytap.mcs:s Router:s VideoCapabilities:s Network-ReportVolleyImpl:s  Network-VolleyImpl:s MS_OMX_PASSTHROUGHDEC:s StreamContentSource:s mstplayer:s router:s ncent.wxservic:s OPWeather:s Line_Novatek:s BtGatt.ScanManager:s chatty:s MI3:s EventMonitor:s EventHandler:s bt_vendor:s GRM:s MTK_KL:s BLEAudioDrive_HV406:s   hpplay-java:s vsyncbridge:s voc_hw_primary:s audio_hw_primary:s|grep -v mars |grep -v Update|grep -v pictoria |grep -v pictorial |grep -v MMP|grep -v NearxTrack|grep -v Track.Near
fi

