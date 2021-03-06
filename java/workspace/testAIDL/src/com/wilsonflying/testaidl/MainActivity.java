package com.wilsonflying.testaidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private IMyService mService;
	private final String TAG = "shs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initService();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		releaseService();
	}
	
	private void releaseService() {
		// TODO Auto-generated method stub
		unbindService(connection);
		connection = null;
	}

	private void initService() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this, MyService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
		Log.d(TAG, "in initService");

	}
	

	ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(TAG, "in onServiceDisconnected");

		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mService = IMyService.Stub.asInterface(service);
			Log.d(TAG, "in onServiceConnected");

		}
	};
	
	public void onClickBtn(View view){
		try {
			Log.d(TAG, "the result:"+mService.add(1, 2));
			Toast.makeText(MainActivity.this, "计算结果1+2:"+ mService.add(1, 2), Toast.LENGTH_SHORT).show();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
