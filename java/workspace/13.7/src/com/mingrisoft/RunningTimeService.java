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
                long currentTime = System.currentTimeMillis();// ��ó���ǰʱ��
                while (true) {
                    try {
                        Thread.sleep(5000);// �߳�����5����
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long runningTime = System.currentTimeMillis() - currentTime;// ��ó�������ʱ��
                    Log.i("RunningTimeService", "��������ʱ�䣺" + runningTime / 1000 + "��!");// ��¼����ʱ��
                    if (runningTime / 1000 == 60) {// �����������ʱ�����60����˳�ѭ��
                        break;
                    }
                }
            };
        }.start();
    }

}
