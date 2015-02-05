package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean flag = true; // 标记变量
	private boolean flag_x=true;	//为true表示从左向右
	private ImageView mouse; // 声明一个ImageView对象
	private Handler handler; // 声明一个Handler对象
	private int x=50;
	private int y=100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mouse = (ImageView) findViewById(R.id.imageView1); // 获取ImageView对象

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int index = 0;
				if (msg.what == 0x101) {
					index = msg.arg1; // 获取移动的距离
					if(x>900){
						flag_x=false;
					}else if(x<50){
						flag_x=true;
					}
					if(flag_x){
						x+=index;
					}else{
						x-=index;
					}
					
					if(flag){
						y-=10;
						flag=false;
					}else{
						y+=10;
						flag=true;
					}
					mouse.setX(x); // 设置X轴位置
					mouse.setY(y); // 设置Y轴位置
				}
				super.handleMessage(msg);
			}

		};
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				int index = 0; // 移动的距离
				while (!Thread.currentThread().isInterrupted()) {
					index = new Random().nextInt(100); // 产生一个随机数
					
					Message m = handler.obtainMessage(); // 获取一个Message
					m.what = 0x101; // 设置消息标识
					m.arg1 = index; // 保存移动的距离
					handler.sendMessage(m); // 发送消息

					try {
						Thread.sleep(new Random().nextInt(500) + 100); // 休眠一段时间
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

		});
		t.start(); // 开启线程

	}

}