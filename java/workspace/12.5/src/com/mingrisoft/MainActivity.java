package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable {
	private ImageView iv; // ����һ����ʾ���ͼƬ��ImageView����
	private Handler handler; // ����һ��Handler����
	private int[] path = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06 }; // ������ͼƬ������
	private String[] title = new String[] { "��̴ʵ�ϵ�в�Ʒ", "��Ч����", "���ַ���", "�û���Ⱥ",
			"����ѧϰ", "ȫ��λ��ѯ" }; // ������ʾ���������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.imageView1); // ��ȡ��ʾ���ͼƬ��ImageView
		Thread t = new Thread(this); // �������߳�
		t.start(); // �����߳�
		// ʵ����һ��Handler����
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// ����UI
				TextView tv = (TextView) findViewById(R.id.textView1); // ��ȡTextView���
				if (msg.what == 0x101) {
					tv.setText(msg.getData().getString("title")); // ���ñ���
					iv.setImageResource(path[msg.arg1]); // ����Ҫ��ʾ��ͼƬ
				}
				super.handleMessage(msg);
			}

		};

	}

	@Override
	public void run() {
		int index = 0;
		while (!Thread.currentThread().isInterrupted()) {
			index = new Random().nextInt(path.length); // ����һ�������
			Message m = handler.obtainMessage(); // ��ȡһ��Message
			m.arg1 = index; // ����Ҫ��ʾ���ͼƬ������ֵ
			Bundle bundle = new Bundle(); // ��ȡBundle����
			m.what = 0x101; // ������Ϣ��ʶ
			bundle.putString("title", title[index]); // �������
			m.setData(bundle); // ��Bundle���󱣴浽Message��
			handler.sendMessage(m); // ������Ϣ

			try {
				Thread.sleep(2000); // �߳�����2����
			} catch (InterruptedException e) {
				e.printStackTrace(); // ����쳣��Ϣ
			}

		}
	}
}