package com.wilsonflying.testservice;

import java.util.Currency;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class timeService2 extends Service {

	private Timer timer;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		timer = new Timer(true);//true: 启动daemon线程
		
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "定时6s结束", Toast.LENGTH_SHORT).show();
				Log.i("timservice2", "定时6s结束");
				timeService2.this.stopSelf();
			}
		}, 6000);
	}

}
