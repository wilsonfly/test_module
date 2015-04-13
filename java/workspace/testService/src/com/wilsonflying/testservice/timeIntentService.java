package com.wilsonflying.testservice;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

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
	}

}
