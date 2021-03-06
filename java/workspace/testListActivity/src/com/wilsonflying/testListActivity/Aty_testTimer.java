package com.wilsonflying.testListActivity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Aty_testTimer extends Activity {

	private Timer timer = null;
	private TimerTask task = null;
	private int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_testtimer);
		
		findViewById(R.id.button_starttimer).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startTimer();
			}
		});
		
		findViewById(R.id.button_stoptimer).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopTimer();
			}
		});

	}


	
	public void startTimer(){
		if(timer == null){
			timer = new Timer();
			task = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					count++;
					Log.i("huasheng", "count :"+count);
				}
			};

			timer.schedule(task, 100, 500);
		}
	}
	
	public void stopTimer(){
		if(timer != null){
			timer.cancel();
			task.cancel();
			
			timer = null;
			task = null;
		}
	}
}
