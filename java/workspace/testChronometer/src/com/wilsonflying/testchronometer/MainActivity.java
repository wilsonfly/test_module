package com.wilsonflying.testchronometer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends Activity {

	private Chronometer clock;
	private int lastTime;
	private boolean started;

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		clock = (Chronometer) findViewById(R.id.chronometer);
		clock.setBase(SystemClock.elapsedRealtime());

		clock.setFormat("计时%s");
		lastTime = (int) SystemClock.elapsedRealtime();
		started = false;
	}

	public void onStart(View v) {
		if(started){
			clock.setBase(clock.getBase()+(SystemClock.elapsedRealtime()-lastTime));
			clock.start();
			Log.i("testchronometer", "onStart,lasttime:"+lastTime);
		}
		else{
			clock.setBase(SystemClock.elapsedRealtime());
			clock.start();
			started = true;
			Log.i("testchronometer", "started");
		}

	}

	public void onStop(View v) {
		lastTime = (int) SystemClock.elapsedRealtime();
		Log.i("testchronometer", "onStop,lasttime:"+lastTime);
		clock.stop();
	}

	public void onReset(View v) {
		clock.setBase(SystemClock.elapsedRealtime());
		started = false;
	}
}
