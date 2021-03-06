package com.wilsonflying.testservice2;

import com.wilsonflying.testservice2.timeBindService.localBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private timeBindService ts;
	private boolean bound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();//别落下这个了
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), timeBindService.class);
				bindService(intent, connection, BIND_AUTO_CREATE);
				if(bound){
					Toast.makeText(getApplicationContext(), "time:"+ts.getCurrentTime(), Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "call bindService", Toast.LENGTH_SHORT).show();

				}
			}
		});
	}
	
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			bound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			localBinder binder = (localBinder) service;
			ts = binder.getService();
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
