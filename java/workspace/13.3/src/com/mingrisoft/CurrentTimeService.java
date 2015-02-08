package com.mingrisoft;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;

public class CurrentTimeService extends Service {

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        CurrentTimeService getService() {
            return CurrentTimeService.this;// 返回当前服务的实例
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }

    public String getCurrentTime() {
        Time time = new Time();// 创建Time对象
        time.setToNow();// 设置时间为当前时间
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");// 设置时间格式
        return currentTime;
    }
}
