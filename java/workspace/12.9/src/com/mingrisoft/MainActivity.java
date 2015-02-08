package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable {
	private TextView tv; // ����һ����ʾ������ֵ�TextView���
	private Handler handler; // ����һ��Handler����
	int[] color=new int[]{R.color.color1,R.color.color2,R.color.color3,	
			R.color.color4,R.color.color5,R.color.color6,R.color.color7};  		//ʹ����ɫ��Դ	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv = (TextView) findViewById(R.id.textView1); // ��ȡ��ʾ������ֵ�TextView
		Thread t = new Thread(this); // �������߳�
		t.start(); // �����߳�
		// ʵ����һ��Handler����
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// ����UI
				if (msg.what == 0x101) {
					tv.setTextColor(getResources().getColor(color[msg.arg1]));	//�ı�������ɫ
				}
				super.handleMessage(msg);
			}

		};

	}

	@Override
	public void run() {
		int index = 0;
		while (!Thread.currentThread().isInterrupted()) {
			index = new Random().nextInt(color.length); // ����һ�������
			Message m = handler.obtainMessage(); // ��ȡһ��Message
			m.arg1 = index; // ����Ҫʹ����ɫ������ֵ
			m.what = 0x101; // ������Ϣ��ʶ
			handler.sendMessage(m); // ������Ϣ

			try {
				Thread.sleep(2000); // �߳�����2����
			} catch (InterruptedException e) {
				e.printStackTrace(); // ����쳣��Ϣ
			}

		}
	}
}