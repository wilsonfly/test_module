package com.wilsonflying.httpUrlConnection_Get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.mingrisoft.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText content;	//����һ�������ı����ݵı༭�����
	private Button button;	//����һ������ť����
	private Handler handler; // ����һ��Handler����
	private String result = "";	//����һ��������ʾ���ݵ��ַ���
	private TextView resultTV;	//����һ����ʾ������ı������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		content = (EditText) findViewById(R.id.content);	//��ȡ�����ı����ݵ�EditText���
		resultTV = (TextView) findViewById(R.id.result);	//��ȡ��ʾ�����TextView���
		button = (Button) findViewById(R.id.button);	//��ȡ��������ť���
		//Ϊ��ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "������Ҫ��������ݣ�",
							Toast.LENGTH_SHORT).show();	//��ʾ��Ϣ��ʾ
					return;
				}

				// ����һ�����̣߳����ڷ��Ͳ���ȡ΢����Ϣ
				new Thread(new Runnable() {
					public void run() {
						send();	//�����ı����ݵ�Web������
						Message m = handler.obtainMessage(); // ��ȡһ��Message
						handler.sendMessage(m); // ������Ϣ
					}
				}).start(); // �����߳�
			}
		});
		//����һ��Handler����
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); // ��ʾ��õĽ��
					content.setText("");		//����ı���
				}
				super.handleMessage(msg);
			}
		};
	}

	public void send() {
		String target="";
		target = "http://192.168.1.66:8081/blog/index.jsp?content="
					+base64(content.getText().toString().trim());	//Ҫ���ʵ�URL��ַ
		URL url;
		try {
			url = new URL(target);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();	//����һ��HTTP����
			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream()); // ��ö�ȡ������
			BufferedReader buffer = new BufferedReader(in); // ��ȡ����������
			String inputLine = null;
			//ͨ��ѭ�����ж�ȡ�������е�����
			while ((inputLine = buffer.readLine()) != null) {
				result += inputLine + "\n";
			}
			in.close();	//�ر��ַ�����������
			urlConn.disconnect();	//�Ͽ�����
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//���ַ�������Base64����
	public String base64(String content){
		try {
			content=Base64.encodeToString(content.getBytes("utf-8"), Base64.DEFAULT);	//���ַ�������Base64����
			content=URLEncoder.encode(content);	//���ַ�������URL����
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();	//����쳣��Ϣ
		}
		return content;
	}
}