package com.mingrisoft;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private SoundPool soundpool;	//����һ��SoundPool����
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();	//����һ��HashMap����
	private ImageView rabbit;
	private int x=0;	//������x���λ��
	private int y=0;	//������y���λ��
	private int width=0;	//��Ļ�Ŀ��
	private int height=0;	//��Ļ�ĸ߶�
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		soundpool = new SoundPool(5,
				AudioManager.STREAM_SYSTEM, 0);	//����һ��SoundPool���󣬸ö����������5����Ƶ��
		//��Ҫ���ŵ���Ƶ�����浽HashMap������
		soundmap.put(1, soundpool.load(this, R.raw.chimes, 1));
		soundmap.put(2, soundpool.load(this, R.raw.enter, 1));
		soundmap.put(3, soundpool.load(this, R.raw.notify, 1));
		soundmap.put(4, soundpool.load(this, R.raw.ringout, 1));
		soundmap.put(5, soundpool.load(this, R.raw.ding, 1));
		rabbit=(ImageView)findViewById(R.id.rabbit);
		width= MainActivity.this.getResources().getDisplayMetrics().widthPixels;
		height=MainActivity.this.getResources().getDisplayMetrics().heightPixels;
		x=width/2-44;	//����������x���λ��
		y=height/2-35;	//����������y���λ��
		rabbit.setX(x);//����������x���λ��
		rabbit.setY(y);//����������y���λ��
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(this);		//ʵ����һ��MenuInflater����
		inflater.inflate(R.menu.setting, menu);		//�����˵��ļ�
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getGroupId()==R.id.setting){			//�ж��Ƿ�ѡ���˲������ò˵���
			if(item.isChecked()){					//���˵����Ѿ���ѡ��
				item.setChecked(false);			//���ò˵����ѡ��
				Music.stop(this);
			}else{
				item.setChecked(true);			//���ò˵��ѡ��
				Music.play(this, R.raw.jasmine);
			}
		}
		return true;
	}

	//��д���̰��������µ��¼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
			case KeyEvent.KEYCODE_DPAD_LEFT:	//�������
				soundpool.play(soundmap.get(1), 1, 1, 0, 0, 1);	//����ָ������Ƶ
				if(x>0){
					x-=10;
					rabbit.setX(x);	//�ƶ�С����
				}
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:	//���ҷ����
				soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);//����ָ������Ƶ
				if(x<width-88){
					x+=10;
					rabbit.setX(x);	//�ƶ�С����
				}
				break;
			case KeyEvent.KEYCODE_DPAD_UP:	//���Ϸ����
				soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1);//����ָ������Ƶ
				if(y>0){
					y-=10;
					rabbit.setY(y);	//�ƶ�С����
				}
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:	//���·����
				soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);//����ָ������Ƶ
				if(y<height-70){
					y+=10;
					rabbit.setY(y);	//�ƶ�С����
				}
				break;
			default:
				soundpool.play(soundmap.get(5), 1, 1, 0, 0, 1);		//����Ĭ�ϰ�����
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		Music.stop(this);	//ֹͣ���ű�������
		super.onPause();
	}
	@Override
	protected void onResume() {
		Music.play(this, R.raw.jasmine);	//���ű�������
		super.onResume();
	}
	
}