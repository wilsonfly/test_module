package com.mingrisoft;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class TimeService extends Service {

    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer(true);// ����Timer����
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager manager = (NotificationManager) getSystemService(ns);// ���֪ͨ������
                Notification notification = new Notification(R.drawable.warning, getText(R.string.ticker_text), System.currentTimeMillis());// ����֪ͨ
                CharSequence contentTitle = getText(R.string.content_title);// ����֪ͨ�ı���
                CharSequence contentText = getText(R.string.content_text);// ����֪ͨ������
                Intent intent = new Intent(TimeService.this, TimeActivity.class);// ����Intent����
                PendingIntent contentIntent = PendingIntent.getActivity(TimeService.this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);// ����PendingIntent����
                notification.setLatestEventInfo(TimeService.this, contentTitle, contentText, contentIntent);// ����֪ͨ��Ϊ
                manager.notify(0, notification);// ��ʾ֪ͨ
                TimeService.this.stopSelf();// ֹͣ����
            }
        }, 60000);
    }
}
