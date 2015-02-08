package com.mingrisoft;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mingrisoft.CurrentTimeService.LocalBinder;

public class CurrentTimeActivity extends Activity {

    CurrentTimeService cts;
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button button = (Button) findViewById(R.id.current_time);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(CurrentTimeActivity.this, CurrentTimeService.class);
                bindService(intent, sc, BIND_AUTO_CREATE);// 绑定服务
                if (bound) {// 如果绑定则显示当前时间
                    Toast.makeText(CurrentTimeActivity.this, cts.getCurrentTime(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            bound = false;
            unbindService(sc);// 解绑定
        }
    }

    private ServiceConnection sc = new ServiceConnection() {

        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder binder = (LocalBinder) service;// 获得自定义的LocalBinder对象
            cts = binder.getService();// 获得CurrentTimeService对象
            bound = true;
        }
    };
}
