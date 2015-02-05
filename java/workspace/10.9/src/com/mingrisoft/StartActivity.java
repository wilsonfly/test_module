package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class StartActivity extends Activity {
	private VideoView video; // 声明VideoView对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		video = (VideoView) findViewById(R.id.video); // 获取VideoView组件
		Uri uri = Uri.parse("android.resource://com.mingrisoft/"
				+ R.raw.mingrisoft); // 获取要播放的文件对应的URI
		video.setVideoURI(uri); // 指定要播放的视频
		video.requestFocus(); // 让VideoView获得焦点
		try {
			video.start(); // 开始播放视频
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
		// 为VideoView添加完成事件监听器
		video.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				startMain(); // 进入游戏主界面
			}
		});

	}

	// 进入游戏主界面
	private void startMain() {
		Intent intent = new Intent(StartActivity.this, MainActivity.class); // 创建Intent
		startActivity(intent); // 启动新的Activity
		StartActivity.this.finish(); // 结束当前Activity
	}
}