package com.wilsonflying.playmp3;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Thread thread;
	private MediaPlayer mp = null;
	private final String TAG = "testThread2";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		final Button button_start = (Button) findViewById(R.id.button_start);
		//Button button_stop = (Button) findViewById(R.id.button_stop);
		
		button_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				button_start.setEnabled(false);
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						playMP3();
					}
				});
				thread.start();
			}
		});
	}
	
	private void playMP3(){
		if(mp != null){
			mp.release();
		}
		mp = MediaPlayer.create(MainActivity.this, R.raw.jasmine);
		mp.start();
		
		mp.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					Log.i(TAG, "播放完毕，重新开始");
					playMP3();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(mp != null){
			mp.stop();
			mp.release();
			mp = null;
			
		}
		
		super.onDestroy();
	}
}
