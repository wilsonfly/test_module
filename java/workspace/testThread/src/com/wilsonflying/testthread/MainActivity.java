package com.wilsonflying.testthread;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements Runnable {

	private Thread thread;
	private int count;
	private final String TAG = "testThread";
	private Boolean requiredQuit = false;//线程有sleep，Thread.interrupt()停不掉，需要另外加开关

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		final Button button_start = (Button) findViewById(R.id.button_start);
		final Button button_stop = (Button) findViewById(R.id.button_stop);

		button_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				thread = new Thread(MainActivity.this);
				thread.start();
				requiredQuit = false;
				button_start.setEnabled(false);
				button_stop.setEnabled(true);
			}
		});

		button_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(thread != null){
					thread.interrupt();
					thread = null;
					requiredQuit = true;
					Log.i(TAG, "即将停掉线程");
					// Toast.makeText(MainActivity.this, "即将停止线程",Toast.LENGTH_SHORT).show();//失败
					button_stop.setEnabled(false);
					button_start.setEnabled(true);
				}
			}
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted() && !requiredQuit) {

			try {
				Thread.sleep(2 * 1000);
				count++;
				Log.i(TAG, "thread is running, count:" + count);
				// Toast.makeText(MainActivity.this, "thread is running,count:"+count,Toast.LENGTH_SHORT).show();//失败
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(thread != null){
			thread.interrupt();
			thread = null;
			requiredQuit = true;
			Log.i(TAG, "onDestroy");
		}
		super.onDestroy();
	}
}
