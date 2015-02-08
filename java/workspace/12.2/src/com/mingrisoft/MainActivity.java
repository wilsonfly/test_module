package com.mingrisoft;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Thread thread; // 声明一个线程对象
	private static MediaPlayer mp = null; // 声明一个MediaPlayer对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.button1); // 获取布局管理器中添加的“开始”按钮
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v).setEnabled(false); // 设置按钮不可用
				// 创建一个用于播放背景音乐的线程
				thread = new Thread(new Runnable() {

					@Override
					public void run() {
						playBGSound(); // 播放背景音乐

					}
				});
				thread.start(); // 开启线程
			}
		});
	}

	// 播放背景音乐
	private void playBGSound() {
		if (mp != null) {
			mp.release(); // 释放资源
		}
		mp = MediaPlayer.create(MainActivity.this, R.raw.jasmine);
		mp.start(); // 开始播放
		// 为MediaPlayer添加播放完成事件监听器
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				try {
					Thread.sleep(5000); // 线程休眠5秒钟
					playBGSound();	// 重新播放音乐
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	protected void onDestroy() {
		if (mp != null) {
			mp.stop(); // 停止播放
			mp.release(); // 释放资源
			mp = null;
		}
		if (thread != null) {
			thread = null;
		}
		super.onDestroy();
	}

}