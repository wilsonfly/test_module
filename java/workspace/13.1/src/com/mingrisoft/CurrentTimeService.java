package com.mingrisoft;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

public class CurrentTimeService extends IntentService {

    public CurrentTimeService() {
        super("CurrentTimeService");// ���ø���ǿչ��췽��
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Time time = new Time();// ����Time����
        time.setToNow();// ����ʱ��Ϊ��ǰʱ��
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");// ����ʱ���ʽ
        Log.i("CurrentTimeService", currentTime);// ��¼��ǰʱ��
    }

}
