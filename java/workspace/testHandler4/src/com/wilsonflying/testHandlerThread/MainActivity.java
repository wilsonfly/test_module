package com.wilsonflying.testHandlerThread;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tv;
	private MyThread thread;
	private Handler handler;
	
	class MyThread extends Thread{
		public Handler handler;
		public Looper looper;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Looper.prepare();//创建looper
			looper = Looper.myLooper();
			handler = new Handler(){
				public void handleMessage(android.os.Message msg) {
					System.out.println("mythread:"+Thread.currentThread());
					Toast.makeText(getApplicationContext(), "in mythread thread", Toast.LENGTH_SHORT).show();
					tv.setText("update text in mythread");
				};
			};
			
			Looper.loop();//循环接收消息
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		tv = new TextView(this);
		tv.setText("hello handler");
		setContentView(tv);
		
		thread = new MyThread();
		thread.start();
		
		
		handler = new Handler(thread.looper){//此处会报空指针的异常，多线程并发，thread线程中looper还没有创建成功
			
			public void handleMessage(android.os.Message msg) {
				System.out.println("UI thread:"+Thread.currentThread());
				Toast.makeText(getApplicationContext(), "in UI thread", Toast.LENGTH_SHORT).show();
				//tv.setText("update text in UI thread");

			};
		};
		
		handler.sendEmptyMessage(1);
		
		
		
	}


}
