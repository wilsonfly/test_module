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
        Time time = new Time();// ����Time����
        time.setToNow();// ����ʱ��Ϊ��ǰʱ��
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");// ����ʱ���ʽ
        Log.i("CurrentTimeService", currentTime);// ��¼��ǰʱ��
        return START_STICKY;
    }
}
