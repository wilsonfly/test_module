package com.mingrisoft;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

public class CurrentTimeService extends IntentService {

    public CurrentTimeService() {
        super("CurrentTimeService");// 调用父类非空构造方法
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Time time = new Time();// 创建Time对象
        time.setToNow();// 设置时间为当前时间
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");// 设置时间格式
        Log.i("CurrentTimeService", currentTime);// 记录当前时间
    }

}
