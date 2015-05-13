package com.wilsonflying.httpClient_Get;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.mingrisoft.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button; // ����һ������ť����
	private Handler handler; // ����һ��Handler����
	private String result = ""; // ����һ��������ʾ������ַ���
	private TextView resultTV; // ����һ����ʾ������ı������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		resultTV = (TextView) findViewById(R.id.result); // ��ȡ��ʾ�����TextView���
		button = (Button) findViewById(R.id.button); // ��ȡ��������ť���
		// Ϊ��ť��ӵ����¼�������
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// ����һ�����̣߳����ڷ��Ͳ���ȡGET����
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
				}
				super.handleMessage(msg);
			}
		};
	}

	public void send() {
		String target = "http://192.168.1.66:8081/blog/deal_httpclient.jsp?param=get";	//Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient = new DefaultHttpClient();//����HttpClient����
		HttpGet httpRequest = new HttpGet(target);	//����HttpGet���Ӷ���
		HttpResponse httpResponse;
		try {
			httpResponse = httpclient.execute(httpRequest);	//ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				result = EntityUtils.toString(httpResponse.getEntity());	//��ȡ���ص��ַ���
			}else{
				result="����ʧ�ܣ�";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}