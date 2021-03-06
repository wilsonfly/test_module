package com.wilsonflying.testthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private final String TAG = "testThreadPool";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClickBtn(View view) {
		switch (view.getId()) {
		case R.id.button1:
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			for (int i = 0; i < 10; i++) {
				final int index = i;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				cachedThreadPool.execute(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.d(TAG, "in cachedThreadPool :" + index);
					}
				});
			}
			break;
		case R.id.button2:
			ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
			for (int i = 0; i < 10; i++) {
				final int index = i;
				fixedThreadPool.execute(new Runnable() {

					@Override
					public void run() {
						try {
							Log.d(TAG, "in fixedThreadPool :" + index);
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			break;
		case R.id.button3:
			ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
			scheduledThreadPool.schedule(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.d(TAG, "delay 3 seconds");
				}
			}, 3, TimeUnit.SECONDS);
			
			break;
		case R.id.button4:
			ScheduledExecutorService scheduledThreadPool2 = Executors.newScheduledThreadPool(5);
			scheduledThreadPool2.scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.d(TAG,"delay 1 seconds, and excute every 3 seconds");
				}
			}, 2, 3, TimeUnit.SECONDS);
			break;
		case R.id.button5:
			ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
			for (int i = 0; i < 10; i++) {
				final int index = i;
				singleThreadExecutor.execute(new Runnable() {

					@Override
					public void run() {
						try {
							Log.d(TAG,"in singleThreadExecutor :"+index);
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			break;
		default:
			break;
		}
	}
}
