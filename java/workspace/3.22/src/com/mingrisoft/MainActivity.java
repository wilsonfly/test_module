package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Chronometer ch = (Chronometer) findViewById(R.id.chronometer1); // ��ȡ��ʱ�����
		ch.setBase(SystemClock.elapsedRealtime()); // ������ʼʱ��
		ch.setFormat("����ʱ�䣺%s"); // ������ʾʱ��ĸ�ʽ
		ch.start(); // ������ʱ��
		// ��Ӽ�����
		ch.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer chronometer) {
				// TODO Auto-generated method stub
				if (SystemClock.elapsedRealtime() - ch.getBase() >= 10000) {
					ch.stop(); // ֹͣ��ʱ��
				}

			}
		});
	}
}