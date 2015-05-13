package com.wilsonflying.testservice3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Messenger msgr;
	private boolean bound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), timeMessengerService.class);
				bindService(intent, connection, BIND_AUTO_CREATE);
				
				if(bound){
					Message msg = Message.obtain(null, timeMessengerService.GET_TIME, 0, 0);
					try {
						msgr.send(msg);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					Toast.makeText(getApplicationContext(), "call bindService", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			msgr = null;
			bound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			msgr = new Messenger(service);
			bound = true;
		}
	};
	
	protected void onStop() {
		super.onStop();
		if(bound){
			unbindService(connection);
			bound = false;
		}
	};
}
