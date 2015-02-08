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
		final Chronometer ch = (Chronometer) findViewById(R.id.chronometer1); // 获取计时器组件
		ch.setBase(SystemClock.elapsedRealtime()); // 设置起始时间
		ch.setFormat("已用时间：%s"); // 设置显示时间的格式
		ch.start(); // 开启计时器
		// 添加监听器
		ch.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer chronometer) {
				// TODO Auto-generated method stub
				if (SystemClock.elapsedRealtime() - ch.getBase() >= 10000) {
					ch.stop(); // 停止计时器
				}

			}
		});
	}
}