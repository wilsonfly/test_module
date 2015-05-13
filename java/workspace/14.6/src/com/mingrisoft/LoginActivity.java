package com.mingrisoft;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private String username;	//�����û����ı���
	private String pwd;	//��������ı���
	private String result = "";	//������ʾ����ı���
	private Handler handler; // ����һ��Handler����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);	//���ò����ļ�
		Button login = (Button) findViewById(R.id.button1);	//��ȡ��¼��ť
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				username = ((EditText) findViewById(R.id.editText1)).getText()
						.toString();	//��ȡ������û���
				pwd = ((EditText) findViewById(R.id.editText2)).getText()
						.toString();	//��ȡ���������

				// ����һ�����̣߳�ʵ���û���¼
				new Thread(new Runnable() {
					public void run() {
						login();	//�û���¼
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
					Intent intent = getIntent(); // ��ȡIntent����
					Bundle bundle = new Bundle(); // ʵ�������ݵ����ݰ�
					bundle.putString("result", result);
					intent.putExtras(bundle); // �����ݰ����浽intent��
					setResult(0x11, intent); // ���÷��صĽ���룬�����ص��ø�Activity��Activity
					finish(); // �رյ�ǰActivity
				}
				super.handleMessage(msg);
			}
		};
		Button exit = (Button) findViewById(R.id.button2);	//��ȡ�˳���ť
		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish(); // �رյ�ǰActivity
			}
		});
	}

	public void login() {
		String target = "http://192.168.1.66:8081/login/login.jsp"; // Ҫ�ύ��Ŀ���ַ
		HttpPost httpRequest = new HttpPost(target); // ����HttpPost����
		// ��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username)); // �û���
		params.add(new BasicNameValuePair("pwd", pwd)); // ����
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse = MainActivity.httpclient
					.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				result += EntityUtils.toString(httpResponse.getEntity()); // ��ȡ���ص��ַ���
			} else {
				result = "����ʧ�ܣ�";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // ����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
}
