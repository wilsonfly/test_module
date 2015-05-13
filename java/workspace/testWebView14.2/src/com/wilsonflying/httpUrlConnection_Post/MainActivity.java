package com.wilsonflying.httpUrlConnection_Post;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.mingrisoft.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText nickname; // ����һ�������ǳƵı༭�����
	private EditText content; // ����һ�������ı����ݵı༭�����
	private Button button; // ����һ������ť����
	private Handler handler; // ����һ��Handler����
	private String result = ""; // ����һ��������ʾ���ݵ��ַ���
	private TextView resultTV; // ����һ����ʾ������ı������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		content = (EditText) findViewById(R.id.content); // ��ȡ�����ı����ݵ�EditText���
		resultTV = (TextView) findViewById(R.id.result); // ��ȡ��ʾ�����TextView���
		nickname = (EditText) findViewById(R.id.nickname); // ��ȡ�����ǳƵ�EditText���
		button = (Button) findViewById(R.id.button); // ��ȡ��������ť���
		// Ϊ��ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(nickname.getText().toString())
						|| "".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "�뽫��������������",
							Toast.LENGTH_SHORT).show();
					return;
				}

				// ����һ�����̣߳����ڴ������ϻ�ȡ�ļ�
				new Thread(new Runnable() {
					public void run() {
						send();
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
					content.setText(""); // ������ݱ༭��
					nickname.setText(""); // ����ǳƱ༭��
				}
				super.handleMessage(msg);
			}
		};
	}

	public void send() {
		String target = "http://192.168.1.66:8081/blog/dealPost.jsp";	//Ҫ�ύ��Ŀ���ַ
		URL url;
		try {
			url = new URL(target);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection(); // ����һ��HTTP����
			urlConn.setRequestMethod("POST"); // ָ��ʹ��POST����ʽ
			urlConn.setDoInput(true); // ��������д������
			urlConn.setDoOutput(true); // �������ж�ȡ����
			urlConn.setUseCaches(false); // ��ֹ����
			urlConn.setInstanceFollowRedirects(true);	//�Զ�ִ��HTTP�ض���
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded"); // ������������
			DataOutputStream out = new DataOutputStream(
					urlConn.getOutputStream()); // ��ȡ�����
			String param = "nickname="
					+ URLEncoder.encode(nickname.getText().toString(), "utf-8")
					+ "&content="
					+ URLEncoder.encode(content.getText().toString(), "utf-8");	//����Ҫ�ύ������
			out.writeBytes(param);//��Ҫ���ݵ�����д�����������
			out.flush();	//�������
			out.close();	//�ر����������
			// �ж��Ƿ���Ӧ�ɹ�
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStreamReader in = new InputStreamReader(
						urlConn.getInputStream()); // ��ö�ȡ������
				BufferedReader buffer = new BufferedReader(in); // ��ȡ����������
				String inputLine = null;
				while ((inputLine = buffer.readLine()) != null) {
					result += inputLine + "\n";
				}
				in.close();	//�ر��ַ�������
			}
			urlConn.disconnect();	//�Ͽ�����
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}