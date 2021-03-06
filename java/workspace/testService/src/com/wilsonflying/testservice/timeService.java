package com.wilsonflying.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class timeService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("Service", "onBind");

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("Service", "onStartCommand");
		Time time = new Time();
		time.setToNow();
		
		String currentTime = time.format("%Y-%m-%d %H:%M:%S");
		Log.i("Service", currentTime);
		Toast.makeText(getApplicationContext(), "time:"+currentTime, Toast.LENGTH_SHORT).show();
		
		return START_STICKY;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("Service", "onCreate");

		super.onCreate();
	}
	
	public void onDestroy() {
		Log.i("Service", "onDestroy");

	};
	
}
