package com.mingrisoft;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.format.Time;
import android.widget.Toast;

public class CurrentTimeService extends Service {

    public static final int CURRENT_TIME = 0;

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CURRENT_TIME) {
                Time time = new Time();// 创建Time对象
                time.setToNow();// 设置时间为当前时间
                String currentTime = time.format("%Y-%m-%d %H:%M:%S");// 设置时间格式
                Toast.makeText(CurrentTimeService.this, currentTime, Toast.LENGTH_LONG).show();
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Messenger messenger = new Messenger(new IncomingHandler());
        return messenger.getBinder();
    }

}
