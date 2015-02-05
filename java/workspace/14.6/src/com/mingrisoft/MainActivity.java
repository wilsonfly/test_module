package com.mingrisoft;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button1; // ����һ����ֱ�ӷ��ʡ���ť����
	private Button button2; // ����һ������¼����ʡ���ť����
	private Handler handler; // ����һ��Handler����
	private String result = ""; // ����һ��������ʾ���ݵ��ַ���
	private TextView resultTV; // ����һ����ʾ������ı������
	public static HttpClient httpclient; // ����һ����̬��ȫ�ֵ�HttpClient����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		httpclient = new DefaultHttpClient(); // ����HttpClient����
		resultTV = (TextView) findViewById(R.id.result); // ��ȡ��ʾ�����TextView���
		button1 = (Button) findViewById(R.id.button1); // ��ȡ������ҳ�桱��ť���
		// Ϊ��ť��ӵ����¼�������
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����һ�����̣߳����������������һ��GET����
				new Thread(new Runnable() {
					public void run() {
						access();
						Message m = handler.obtainMessage(); // ��ȡһ��Message
						handler.sendMessage(m); // ������Ϣ
					}
				}).start(); // �����߳�

			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); // ��ʾ��õĽ��
				}
				super.handleMessage(msg);
			}
		};
		button2 = (Button) findViewById(R.id.button2);	//��ȡ���û���¼����ť
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class); // ����Intent����
				startActivityForResult(intent, 0x11); // �����µ�Activity
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x11 && resultCode == 0x11) { // �ж��Ƿ�Ϊ������Ľ��
			Bundle bundle = data.getExtras(); // ��ȡ���ݵ����ݰ�
			result = bundle.getString("result");
			resultTV.setText(result); // ��ʾ��õĽ��
		}
	}

	public void access() {
		String target = "http://192.168.1.66:8081/login/index.jsp"; // Ҫ�ύ��Ŀ���ַ

		HttpGet httpRequest = new HttpGet(target); // ����HttpGet����
		HttpResponse httpResponse;
		try {
			httpResponse = httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity()); // ��ȡ���ص��ַ���
			} else {
				result = "����ʧ�ܣ�";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}