package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements Runnable {
	private Thread thread;	//�����̶߳���
	int i;	//ѭ������
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button startButton=(Button)findViewById(R.id.button1);	//��ȡ����ʼ����ť
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i=0;
				thread=new Thread(MainActivity.this);	//����һ���߳�
				thread.start();	//�����߳�
				
			}
		});
		Button stopButton=(Button)findViewById(R.id.button2);	//��ȡ��ֹͣ����ť
		stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(thread!=null){
					thread.interrupt();	//�ж��߳�
					thread=null;
				}
				Log.i("��ʾ��","�ж��߳�");
				
			}
		});
	}

	@Override
	protected void onDestroy() {

		if(thread!=null){
			thread.interrupt();	//�ж��߳�
			thread=null;
		}
		super.onDestroy();
	}
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			i++;
			Log.i("ѭ��������",String.valueOf(i));
		}
		
	}
}