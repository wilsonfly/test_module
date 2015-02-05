package com.mingrisoft;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class RunningTimeService extends IntentService {

    public RunningTimeService() {
        super("RunningTimeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        new Thread() {
            public void run() {
                long currentTime = System.currentTimeMillis();// 获得程序当前时间
                while (true) {
                    try {
                        Thread.sleep(5000);// 线程休眠5秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long runningTime = System.currentTimeMillis() - currentTime;// 获得程序运行时间
                    Log.i("RunningTimeService", "程序运行时间：" + runningTime / 1000 + "秒!");// 记录运行时间
                    if (runningTime / 1000 == 60) {// 如果程序运行时间等于60秒就退出循环
                        break;
                    }
                }
            };
        }.start();
    }

}
