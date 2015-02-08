package com.mingrisoft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText urlText;	//���ص�ַ�༭��
	private Button button;	//���ذ�ť
	private Handler handler; // ����һ��Handler����
	private boolean flag = false;	//����Ƿ�ɹ��ı���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		urlText = (EditText) findViewById(R.id.editText_url); // ��ȡ���ֹ���������ӵ����ص�ַ�༭��
		button = (Button) findViewById(R.id.button_go); // ��ȡ���ֹ���������ӵ����ذ�ť
		// Ϊ�����ء���ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����һ�����̣߳����ڴ������ϻ�ȡ�ļ�
				new Thread(new Runnable() {
					public void run() {
						try {
							String sourceUrl = urlText.getText().toString(); // ��ȡ���ص�ַ
							URL url = new URL(sourceUrl); // �������ص�ַ��Ӧ��URL����
							HttpURLConnection urlConn = (HttpURLConnection) url
									.openConnection(); // ����һ������
							InputStream is = urlConn.getInputStream(); // ��ȡ����������
							if (is != null) {
								String expandName = sourceUrl.substring(
										sourceUrl.lastIndexOf(".") + 1,
										sourceUrl.length()).toLowerCase(); // ��ȡ�ļ�����չ��
								String fileName = sourceUrl.substring(
										sourceUrl.lastIndexOf("/") + 1,
										sourceUrl.lastIndexOf(".")); // ��ȡ�ļ���
								File file = new File("/sdcard/pictures/"
										+ fileName + "." + expandName); // ��SD���ϴ����ļ�
								FileOutputStream fos = new FileOutputStream(
										file); // ����һ���ļ����������
								byte buf[] = new byte[128];// ����һ���ֽ�����
								// ��ȡ�ļ��������������
								while (true) {
									int numread = is.read(buf);
									if (numread <= 0) {
										break;
									} else {
										fos.write(buf, 0, numread);
									}
								}
							}
							is.close(); // �ر�����������
							urlConn.disconnect(); // �ر�����
							flag = true;
						} catch (MalformedURLException e) {
							e.printStackTrace(); // ����쳣��Ϣ
							flag = false;
						} catch (IOException e) {
							e.printStackTrace(); // ����쳣��Ϣ
							flag = false;
						}
						Message m = handler.obtainMessage(); // ��ȡһ��Message
						handler.sendMessage(m); // ������Ϣ
					}
				}).start(); // �����߳�

			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (flag) {
					Toast.makeText(MainActivity.this, "�ļ�������ɣ�",
							Toast.LENGTH_SHORT).show(); // ��ʾ��Ϣ��ʾ
				} else {
					Toast.makeText(MainActivity.this, "�ļ�����ʧ�ܣ�",
							Toast.LENGTH_SHORT).show(); // ��ʾ��Ϣ��ʾ
				}
				super.handleMessage(msg);
			}
		};
	}
}