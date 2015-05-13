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
	private SoundPool soundpool;	//声明一个SoundPool对象
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();	//创建一个HashMap对象
	private ImageView rabbit;
	private int x=0;	//兔子在x轴的位置
	private int y=0;	//兔子在y轴的位置
	private int width=0;	//屏幕的宽度
	private int height=0;	//屏幕的高度
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		soundpool = new SoundPool(5,
				AudioManager.STREAM_SYSTEM, 0);	//创建一个SoundPool对象，该对象可以容纳5个音频流
		//将要播放的音频流保存到HashMap对象中
		soundmap.put(1, soundpool.load(this, R.raw.chimes, 1));
		soundmap.put(2, soundpool.load(this, R.raw.enter, 1));
		soundmap.put(3, soundpool.load(this, R.raw.notify, 1));
		soundmap.put(4, soundpool.load(this, R.raw.ringout, 1));
		soundmap.put(5, soundpool.load(this, R.raw.ding, 1));
		rabbit=(ImageView)findViewById(R.id.rabbit);
		width= MainActivity.this.getResources().getDisplayMetrics().widthPixels;
		height=MainActivity.this.getResources().getDisplayMetrics().heightPixels;
		x=width/2-44;	//计算兔子在x轴的位置
		y=height/2-35;	//计算兔子在y轴的位置
		rabbit.setX(x);//设置兔子在x轴的位置
		rabbit.setY(y);//设置兔子在y轴的位置
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(this);		//实例化一个MenuInflater对象
		inflater.inflate(R.menu.setting, menu);		//解析菜单文件
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getGroupId()==R.id.setting){			//判断是否选择了参数设置菜单组
			if(item.isChecked()){					//当菜单项已经被选中
				item.setChecked(false);			//设置菜单项不被选中
				Music.stop(this);
			}else{
				item.setChecked(true);			//设置菜单项被选中
				Music.play(this, R.raw.jasmine);
			}
		}
		return true;
	}

	//重写键盘按键被按下的事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
			case KeyEvent.KEYCODE_DPAD_LEFT:	//向左方向键
				soundpool.play(soundmap.get(1), 1, 1, 0, 0, 1);	//播放指定的音频
				if(x>0){
					x-=10;
					rabbit.setX(x);	//移动小兔子
				}
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:	//向右方向键
				soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1);//播放指定的音频
				if(x<width-88){
					x+=10;
					rabbit.setX(x);	//移动小兔子
				}
				break;
			case KeyEvent.KEYCODE_DPAD_UP:	//向上方向键
				soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1);//播放指定的音频
				if(y>0){
					y-=10;
					rabbit.setY(y);	//移动小兔子
				}
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:	//向下方向键
				soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);//播放指定的音频
				if(y<height-70){
					y+=10;
					rabbit.setY(y);	//移动小兔子
				}
				break;
			default:
				soundpool.play(soundmap.get(5), 1, 1, 0, 0, 1);		//播放默认按键音
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		Music.stop(this);	//停止播放背景音乐
		super.onPause();
	}
	@Override
	protected void onResume() {
		Music.play(this, R.raw.jasmine);	//播放背景音乐
		super.onResume();
	}
	
}