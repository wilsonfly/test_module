package com.wilsonflying.playsound;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private SoundPool soundpool;
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();
	private int soundId2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);

		soundpool = new SoundPool(4, AudioManager.STREAM_SYSTEM, 0);
		soundmap.put(1, soundpool.load(getApplicationContext(), R.raw.chimes, 1));
		soundmap.put(2, soundpool.load(getApplicationContext(), R.raw.enter, 1));
		soundmap.put(3, soundpool.load(getApplicationContext(), R.raw.notify, 1));
		soundmap.put(4, soundpool.load(getApplicationContext(), R.raw.ding, 1));
		
		soundId2 = soundpool.load(getApplicationContext(), R.raw.enter, 1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundpool.play(soundmap.get(1), 1, 1, 0, 0, 1);
			}
		});
		
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//方法一：
//				soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);
				
				//方法二：
				soundpool.play(soundId2, 1, 1, 0, 0, 1);
			}
		});
		
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1);
			}
		});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
		return super.onKeyDown(keyCode, event);
	}
}
