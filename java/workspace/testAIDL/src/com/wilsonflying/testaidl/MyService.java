package com.wilsonflying.testaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

	private final String TAG = "MyService";
	
	private IMyService.Stub mBinder = new IMyService.Stub() {
		
		@Override
		public int add(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			
			Log.d(TAG, "in IMyService.Stub, add:"+(a+b));
			
			return a+b;
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBind");

		return mBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(TAG, "onCreate");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroy");

		super.onDestroy();
		
	}

}
