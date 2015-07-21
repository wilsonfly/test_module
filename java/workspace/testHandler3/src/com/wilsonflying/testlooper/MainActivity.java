package com.wilsonflying.testlooper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tv;
	private Handler handler = new Handler() {

		// 此handlemessage 不要执行耗时操作，会导致界面卡顿
		public void handleMessage(android.os.Message msg) {
			System.out.println("UI thread:" + Thread.currentThread());
			Toast.makeText(getApplicationContext(), "in UI thread",
					Toast.LENGTH_SHORT).show();
			// tv.setText("update text in UI thread");

		};
	};

	class MyThread extends Thread {
		public Handler handler;

		@Override
		public void run() {
			// TODO Auto-generated method stub

			Looper.prepare();// 创建looper

			handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					System.out.println("mythread:" + Thread.currentThread());
					Toast.makeText(getApplicationContext(),
							"in mythread thread", Toast.LENGTH_SHORT).show();
					tv.setText("update text in mythread");
				};
			};

			Looper.loop();// 循环接收消息

		}
	}

	private MyThread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.layout_main);

		tv = new TextView(this);
		tv.setText("hello handler");
		setContentView(tv);

		thread = new MyThread();
		thread.start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		thread.handler.sendEmptyMessage(1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.sendEmptyMessage(1);
	}

}
