package com.wilsonflying.httpClient_Post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
		String target = "http://192.168.1.66:8081/blog/deal_httpclient.jsp";	//Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient = new DefaultHttpClient();	//����HttpClient����
		HttpPost httpRequest = new HttpPost(target);	//����HttpPost����
		//��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("param", "post"));	//��ǲ���
		params.add(new BasicNameValuePair("nickname", nickname.getText().toString()));	//�ǳ�
		params.add(new BasicNameValuePair("content", content.getText().toString()));	//����
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); //���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest);	//ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){	//�������ɹ�
				result += EntityUtils.toString(httpResponse.getEntity());	//��ȡ���ص��ַ���
				
			}else{
				result = "����ʧ�ܣ�";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();	//����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace();	//����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace();	//����쳣��Ϣ
		}
	}
}