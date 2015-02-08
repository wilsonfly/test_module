package com.mingrisoft;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Thread thread; // ����һ���̶߳���
	private static MediaPlayer mp = null; // ����һ��MediaPlayer����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.button1); // ��ȡ���ֹ���������ӵġ���ʼ����ť
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Button) v).setEnabled(false); // ���ð�ť������
				// ����һ�����ڲ��ű������ֵ��߳�
				thread = new Thread(new Runnable() {

					@Override
					public void run() {
						playBGSound(); // ���ű�������

					}
				});
				thread.start(); // �����߳�
			}
		});
	}

	// ���ű�������
	private void playBGSound() {
		if (mp != null) {
			mp.release(); // �ͷ���Դ
		}
		mp = MediaPlayer.create(MainActivity.this, R.raw.jasmine);
		mp.start(); // ��ʼ����
		// ΪMediaPlayer��Ӳ�������¼�������
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				try {
					Thread.sleep(5000); // �߳�����5����
					playBGSound();	// ���²�������
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	protected void onDestroy() {
		if (mp != null) {
			mp.stop(); // ֹͣ����
			mp.release(); // �ͷ���Դ
			mp = null;
		}
		if (thread != null) {
			thread = null;
		}
		super.onDestroy();
	}

}