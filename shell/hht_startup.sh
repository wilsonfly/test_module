#!/vendor/bin/sh

#insmod /vendor/lib/modules/HuaXinSE.ko

# rm the complete zip file to release storage
LAST_BUILD_DATE_FILE=/data/ota_package/last.build.date.txt
last_build_date=$(cat $LAST_BUILD_DATE_FILE)
latest_build_date=$(getprop ro.build.date.utc)
if [ "$last_build_date" != "$latest_build_date" ];then
    rm -f /data/ota_package/update_*.zip
    echo "$latest_build_date" > $LAST_BUILD_DATE_FILE
fi

#try to set productModel to env if it not exists in env
#try to update the property using the value from env if values from env and property are different
envModel="productModel"
modelProperty="persist.product.model"
pValue=$(getprop $modelProperty)
if [ "$pValue" != "" ];then
    hht_setenv $envModel $pValue
fi


##set persist.sys.route_mode according to persist.product.model[16:17]
##force set persist.sys.route_mode by persist.sys.route_mode.debug
#persist.product.model[16:17]:01:router 02:wifi other:router
#persist.sys.route_mode: 2:router 0:wifi other:wifi
#persist.sys.route_mode.debug: [router|wifi]
modelProperty="persist.product.model"
netModeProperty="persist.sys.route_mode"
pValue=$(getprop $modelProperty)
mValue=${pValue:16:2}
echo "mValue:$mValue"
if [ "$mValue" == "01" ];then
    ##router mode
    setprop $netModeProperty 2
else if [ "$mValue" == "02" ];then
    ##wifi mode
    setprop $netModeProperty 0
else
    ##default router mode
    setprop $netModeProperty 2
fi
fi

debugProperty="persist.sys.route_mode.debug"
dValue=$(getprop $debugProperty)
if [ "$dValue" == "router" ];then
    ##router mode
    setprop $netModeProperty 2
else if [ "$dValue" == "wifi" ];then
    ##wifi mode
    setprop $netModeProperty 0
fi
fi

