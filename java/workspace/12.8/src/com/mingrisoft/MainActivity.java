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
	private boolean flag = true; // ��Ǳ���
	private boolean flag_x=true;	//Ϊtrue��ʾ��������
	private ImageView mouse; // ����һ��ImageView����
	private Handler handler; // ����һ��Handler����
	private int x=50;
	private int y=100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mouse = (ImageView) findViewById(R.id.imageView1); // ��ȡImageView����

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int index = 0;
				if (msg.what == 0x101) {
					index = msg.arg1; // ��ȡ�ƶ��ľ���
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
					mouse.setX(x); // ����X��λ��
					mouse.setY(y); // ����Y��λ��
				}
				super.handleMessage(msg);
			}

		};
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				int index = 0; // �ƶ��ľ���
				while (!Thread.currentThread().isInterrupted()) {
					index = new Random().nextInt(100); // ����һ�������
					
					Message m = handler.obtainMessage(); // ��ȡһ��Message
					m.what = 0x101; // ������Ϣ��ʶ
					m.arg1 = index; // �����ƶ��ľ���
					handler.sendMessage(m); // ������Ϣ

					try {
						Thread.sleep(new Random().nextInt(500) + 100); // ����һ��ʱ��
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

		});
		t.start(); // �����߳�

	}

}