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
	private int i = 0; // ��¼����˼�ֻ����
	private ImageView mouse; // ����һ��ImageView����
	private Handler handler; // ����һ��Handler����
	public int[][] position = new int[][] { { 231, 325 }, { 424, 349 },
			{ 521, 256 }, { 543, 296 }, { 719, 245 }, { 832, 292 },
			{ 772, 358 } }; // ����һ����ʾ����λ�õ�����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mouse = (ImageView) findViewById(R.id.imageView1); // ��ȡImageView����
		mouse.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				v.setVisibility(View.INVISIBLE); // ���õ�����ʾ
				i++;
				Toast.makeText(MainActivity.this, "��[ " + i + " ]ֻ����",
						Toast.LENGTH_SHORT).show(); // ��ʾ��Ϣ��ʾ��
				return false;
			}
		});

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int index = 0;
				if (msg.what == 0x101) {
					index = msg.arg1; // ��ȡλ������ֵ
					mouse.setX(position[index][0]); // ����X��λ��
					mouse.setY(position[index][1]); // ����Y��λ��
					mouse.setVisibility(View.VISIBLE); // ���õ�����ʾ
				}
				super.handleMessage(msg);
			}

		};
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				int index = 0; // ����һ����¼����λ�õ�����ֵ
				while (!Thread.currentThread().isInterrupted()) {
					index = new Random().nextInt(position.length); // ����һ�������
					Message m = handler.obtainMessage(); // ��ȡһ��Message
					m.what = 0x101; // ������Ϣ��ʶ
					m.arg1 = index; // ��������λ�õ�����ֵ
					handler.sendMessage(m); // ������Ϣ

					try {
						Thread.sleep(new Random().nextInt(500) + 500); // ����һ��ʱ��
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

		});
		t.start(); // �����߳�

	}

}