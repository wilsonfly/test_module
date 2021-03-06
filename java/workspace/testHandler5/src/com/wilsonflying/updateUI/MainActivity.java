package com.wilsonflying.updateUI;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int UPDATE_TEXT = 100;
	private TextView tv;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == UPDATE_TEXT){
				tv.setText("update text from sendMessage");
			}
		};
	};

	public void method_post(){
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				tv.setText("update text from handler.post");
			}
		});
	}

	public void method_sendMessage(){
		handler.sendEmptyMessage(UPDATE_TEXT);
	}
	
	public void method_runOnUiThread(){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				tv.setText("update text from runOnUiThread");
			}
		});
	}
	
	public void method_viewPost(){
		tv.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				tv.setText("update text from textView.Post");

			}
		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		tv = (TextView) findViewById(R.id.text);
		
		new Thread(){
			
			public void run() {
				try {
					Thread.sleep(2000);
					method_post();
					
					Thread.sleep(2000);
					method_sendMessage();
					
					Thread.sleep(2000);
					method_runOnUiThread();
					
					Thread.sleep(2000);
					method_viewPost();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			
		}.start();
		
	}


}
