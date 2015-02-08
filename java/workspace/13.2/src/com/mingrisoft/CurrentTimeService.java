package com.mingrisoft;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

public class CurrentTimeService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Time time = new Time();// 创建Time对象
        time.setToNow();// 设置时间为当前时间
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");// 设置时间格式
        Log.i("CurrentTimeService", currentTime);// 记录当前时间
        return START_STICKY;
    }
}
