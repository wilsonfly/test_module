package com.wilsonflying.testbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button_send, button_reg, button_unreg;
	private final MyBC receiver = new MyBC();  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		
		button_send = (Button) findViewById(R.id.button_send);
		button_reg = (Button) findViewById(R.id.button_reg);
		button_unreg = (Button) findViewById(R.id.button_unreg);
		
		
		button_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//方法一：在AndroidManifest中注册receiver
//				Intent intent = new Intent(MainActivity.this, MyBc.class);
//				intent.putExtra("info", "hello broadcast");
//				sendBroadcast(intent);
				
				//方法二：通过动态注册broadcastreceiver
				Intent intent = new Intent(MyBC.ACTION);
				intent.putExtra("info", "hello broadcast");
				sendBroadcast(intent);
			}
		});
		
		button_reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerReceiver(receiver, new IntentFilter(MyBC.ACTION));
			}
		});
		
		button_unreg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unregisterReceiver(receiver);
			}
		});
	}
}
