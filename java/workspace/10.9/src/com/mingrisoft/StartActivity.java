package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class StartActivity extends Activity {
	private VideoView video; // ����VideoView����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		video = (VideoView) findViewById(R.id.video); // ��ȡVideoView���
		Uri uri = Uri.parse("android.resource://com.mingrisoft/"
				+ R.raw.mingrisoft); // ��ȡҪ���ŵ��ļ���Ӧ��URI
		video.setVideoURI(uri); // ָ��Ҫ���ŵ���Ƶ
		video.requestFocus(); // ��VideoView��ý���
		try {
			video.start(); // ��ʼ������Ƶ
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		// ΪVideoView�������¼�������
		video.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				startMain(); // ������Ϸ������
			}
		});

	}

	// ������Ϸ������
	private void startMain() {
		Intent intent = new Intent(StartActivity.this, MainActivity.class); // ����Intent
		startActivity(intent); // �����µ�Activity
		StartActivity.this.finish(); // ������ǰActivity
	}
}