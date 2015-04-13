package com.wilsonflying.testservice2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;

public class timeBindService extends Service {

	private final IBinder binder = new localBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
//		return null;
		return binder;
	}

	public class localBinder extends Binder{
		timeBindService getService(){
			return timeBindService.this;
		}
	}
	
	public String getCurrentTime(){
		Time time = new Time();
		time.setToNow();
		String currentTime = time.format("%Y-%m-%d %H:%M:%S");
		return currentTime;
	}
}
