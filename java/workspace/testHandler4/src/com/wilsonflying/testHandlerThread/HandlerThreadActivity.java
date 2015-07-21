package com.wilsonflying.testHandlerThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerThreadActivity extends Activity {

	private TextView tv;
	private HandlerThread thread;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		tv= new TextView(this);
		tv.setText("handler thread");
		setContentView(tv);
		
		thread = new HandlerThread("myHandlerThread");
		thread.start();
		
		handler = new Handler(thread.getLooper()){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				
				System.out.println("in handlerThread:"+Thread.currentThread());
				
				Toast.makeText(getApplicationContext(), "in handlerThread", Toast.LENGTH_SHORT).show();
			}
			
		};
		
		handler.sendEmptyMessage(1);
		
	}

}
