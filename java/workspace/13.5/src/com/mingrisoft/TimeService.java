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
        timer = new Timer(true);// 创建Timer对象
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager manager = (NotificationManager) getSystemService(ns);// 获得通知管理器
                Notification notification = new Notification(R.drawable.warning, getText(R.string.ticker_text), System.currentTimeMillis());// 创建通知
                CharSequence contentTitle = getText(R.string.content_title);// 定义通知的标题
                CharSequence contentText = getText(R.string.content_text);// 定义通知的内容
                Intent intent = new Intent(TimeService.this, TimeActivity.class);// 创建Intent对象
                PendingIntent contentIntent = PendingIntent.getActivity(TimeService.this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);// 创建PendingIntent对象
                notification.setLatestEventInfo(TimeService.this, contentTitle, contentText, contentIntent);// 定义通知行为
                manager.notify(0, notification);// 显示通知
                TimeService.this.stopSelf();// 停止服务
            }
        }, 60000);
    }
}
