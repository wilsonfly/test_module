package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements Runnable {
	private Thread thread;	//声明线程对象
	int i;	//循环变量
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button startButton=(Button)findViewById(R.id.button1);	//获取“开始”按钮
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i=0;
				thread=new Thread(MainActivity.this);	//创建一个线程
				thread.start();	//开启线程
				
			}
		});
		Button stopButton=(Button)findViewById(R.id.button2);	//获取“停止”按钮
		stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(thread!=null){
					thread.interrupt();	//中断线程
					thread=null;
				}
				Log.i("提示：","中断线程");
				
			}
		});
	}

	@Override
	protected void onDestroy() {

		if(thread!=null){
			thread.interrupt();	//中断线程
			thread=null;
		}
		super.onDestroy();
	}
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			i++;
			Log.i("循环变量：",String.valueOf(i));
		}
		
	}
}