package com.wilsonflying.testservice;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class timeIntentService extends IntentService{


	public timeIntentService() {
		super("timeIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		String currentTime = time.format("%Y-%m-%d %H:%M:%S");
		Log.i("intentService", currentTime);
		Toast.makeText(getApplicationContext(), "time:"+currentTime, Toast.LENGTH_SHORT).show();
		
		new Thread(){
			
			public void run() {
				long current = System.currentTimeMillis();
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long past = System.currentTimeMillis() - current;
					if(past >5000){
						Log.i("intentService", "运行时间超过5s");
						break;
					}
				}
			};
		}.start();
	}

}
