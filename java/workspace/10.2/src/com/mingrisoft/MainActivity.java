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
	private SoundPool soundpool;	//����һ��SoundPool����
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();	//����һ��HashMap����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button chimes = (Button) findViewById(R.id.button1);	//��ȡ������������ť
		Button enter = (Button) findViewById(R.id.button2);		//��ȡ���������������ť
		Button notify = (Button) findViewById(R.id.button3);	//��ȡ������������ť
		Button ringout = (Button) findViewById(R.id.button4);	//��ȡ���绰������ť
		soundpool = new SoundPool(5,
				AudioManager.STREAM_SYSTEM, 0);	//����һ��SoundPool���󣬸ö����������5����Ƶ��
		//��Ҫ���ŵ���Ƶ�����浽HashMap������
		soundmap.put(1, soundpool.load(this, R.raw.chimes, 1));
		soundmap.put(2, soundpool.load(this, R.raw.enter, 1));
		soundmap.put(3, soundpool.load(this, R.raw.notify, 1));
		soundmap.put(4, soundpool.load(this, R.raw.ringout, 1));
		soundmap.put(5, soundpool.load(this, R.raw.ding, 1));
		//Ϊ����ť��ӵ����¼�������
		chimes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(1), 1, 1, 0, 0, 1);	//����ָ������Ƶ
			}
		});
		enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);//����ָ������Ƶ
				
			}
		});
		notify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1);//����ָ������Ƶ
				
			}
		});
		ringout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);//����ָ������Ƶ
				soundpool.play(soundpool.load(MainActivity.this, R.raw.notify, 1), 1, 1, 0, 0, 1);
			}
		});
	
	}
	//��д�������µ��¼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		soundpool.play(soundmap.get(5), 1, 1, 0, 0, 1);		//���Ű�����
		return true;
	}
}