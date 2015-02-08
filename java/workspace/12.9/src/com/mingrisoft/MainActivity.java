package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable {
	private TextView tv; // 声明一个显示广告文字的TextView组件
	private Handler handler; // 声明一个Handler对象
	int[] color=new int[]{R.color.color1,R.color.color2,R.color.color3,	
			R.color.color4,R.color.color5,R.color.color6,R.color.color7};  		//使用颜色资源	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv = (TextView) findViewById(R.id.textView1); // 获取显示广告文字的TextView
		Thread t = new Thread(this); // 创建新线程
		t.start(); // 开启线程
		// 实例化一个Handler对象
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 更新UI
				if (msg.what == 0x101) {
					tv.setTextColor(getResources().getColor(color[msg.arg1]));	//改变文字颜色
				}
				super.handleMessage(msg);
			}

		};

	}

	@Override
	public void run() {
		int index = 0;
		while (!Thread.currentThread().isInterrupted()) {
			index = new Random().nextInt(color.length); // 产生一个随机数
			Message m = handler.obtainMessage(); // 获取一个Message
			m.arg1 = index; // 保存要使用颜色的索引值
			m.what = 0x101; // 设置消息标识
			handler.sendMessage(m); // 发送消息

			try {
				Thread.sleep(2000); // 线程休眠2秒钟
			} catch (InterruptedException e) {
				e.printStackTrace(); // 输出异常信息
			}

		}
	}
}