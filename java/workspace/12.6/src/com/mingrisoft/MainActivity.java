package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Handler handler;	//创建Handler对象
	private static LinearLayout linearLayout; // 整体布局
	public static TextView[] tv = new TextView[14]; // TextView数组
	int[] bgColor=new int[]{R.color.color1,R.color.color2,R.color.color3,	
			R.color.color4,R.color.color5,R.color.color6,R.color.color7};  		//使用颜色资源     
	private int index=0;	//当前颜色值

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		linearLayout=(LinearLayout)findViewById(R.id.ll);	//获取线性布局管理器
		int height=this.getResources().getDisplayMetrics().heightPixels;	//获取屏幕的高度
	    for(int i=0;i<tv.length;i++){
        	tv[i]=new TextView(this);	//创建一个文本框对象
        	tv[i].setWidth(this.getResources().getDisplayMetrics().widthPixels);	//设置文本框的宽度
        	tv[i].setHeight(height/tv.length);	//为设置文本框的高度
        	linearLayout.addView(tv[i]);	//将TextView组件添加到线性布局管理器中
        }

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int temp=0;	//临时变量
				if (msg.what == 0x101) {
					for(int i=0;i<tv.length;i++){
						temp=new Random().nextInt(bgColor.length);	//产生一个随机数
						//去掉重复的并且相邻的颜色
						if(index==temp){
							temp++;
							if(temp==bgColor.length){
								temp=0;
							}
						}
						index=temp;
						tv[i].setBackgroundColor(getResources().getColor(bgColor[index]));	//为文本框设置背景
					}
				}
				super.handleMessage(msg);
			}

		};
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					Message m = handler.obtainMessage();	//获取一个Message
					m.what=0x101;	//设置消息标识
					handler.sendMessage(m);	//发送消息
					try {
						Thread.sleep(new Random().nextInt(1000));	//休眠1秒钟
					} catch (InterruptedException e) {
						e.printStackTrace();		//输出异常信息
					}

				}
				
			}
			
		});
		t.start(); // 开启线程

	}

}