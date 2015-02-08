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
            return CurrentTimeService.this;// ���ص�ǰ�����ʵ��
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }

    public String getCurrentTime() {
        Time time = new Time();// ����Time����
        time.setToNow();// ����ʱ��Ϊ��ǰʱ��
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");// ����ʱ���ʽ
        return currentTime;
    }
}
