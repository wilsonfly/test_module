package com.mingrisoft;

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
	private SoundPool soundpool;	//声明一个SoundPool对象
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();	//创建一个HashMap对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button chimes = (Button) findViewById(R.id.button1);	//获取“风铃声”按钮
		Button enter = (Button) findViewById(R.id.button2);		//获取“布谷鸟叫声”按钮
		Button notify = (Button) findViewById(R.id.button3);	//获取“门铃声”按钮
		Button ringout = (Button) findViewById(R.id.button4);	//获取“电话声”按钮
		soundpool = new SoundPool(5,
				AudioManager.STREAM_SYSTEM, 0);	//创建一个SoundPool对象，该对象可以容纳5个音频流
		//将要播放的音频流保存到HashMap对象中
		soundmap.put(1, soundpool.load(this, R.raw.chimes, 1));
		soundmap.put(2, soundpool.load(this, R.raw.enter, 1));
		soundmap.put(3, soundpool.load(this, R.raw.notify, 1));
		soundmap.put(4, soundpool.load(this, R.raw.ringout, 1));
		soundmap.put(5, soundpool.load(this, R.raw.ding, 1));
		//为各按钮添加单击事件监听器
		chimes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(1), 1, 1, 0, 0, 1);	//播放指定的音频
			}
		});
		enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);//播放指定的音频
				
			}
		});
		notify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1);//播放指定的音频
				
			}
		});
		ringout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);//播放指定的音频
				soundpool.play(soundpool.load(MainActivity.this, R.raw.notify, 1), 1, 1, 0, 0, 1);
			}
		});
	
	}
	//重写键被按下的事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		soundpool.play(soundmap.get(5), 1, 1, 0, 0, 1);		//播放按键音
		return true;
	}
}