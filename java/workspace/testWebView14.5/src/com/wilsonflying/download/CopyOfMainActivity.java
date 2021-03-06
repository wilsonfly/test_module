package com.wilsonflying.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.mingrisoft.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CopyOfMainActivity extends Activity implements Runnable {
	private EditText urlText;
	private Button button;
	private Handler handler; // ����һ��Handler����
	private boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		urlText = (EditText) findViewById(R.id.editText_url);
		button = (Button) findViewById(R.id.button_go);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Thread t = new Thread(CopyOfMainActivity.this);
				t.start(); // �����߳�
				handler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						if (flag) {
							Toast.makeText(CopyOfMainActivity.this, "�ļ�������ɣ�",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(CopyOfMainActivity.this, "�ļ�����ʧ�ܣ�",
									Toast.LENGTH_SHORT).show();
						}
						super.handleMessage(msg);
					}
				};
			}
		});
	}

	@Override
	public void run() {
		try {
			String sourceUrl = urlText.getText().toString();
			URL url = new URL(sourceUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			InputStream is = urlConn.getInputStream(); // ��ȡ����������
			if (is != null) {
				String expandName = sourceUrl.substring(
						sourceUrl.lastIndexOf(".") + 1, sourceUrl.length())
						.toLowerCase();
				String fileName = sourceUrl.substring(
						sourceUrl.lastIndexOf("/") + 1,
						sourceUrl.lastIndexOf("."));
				File file = new File("/sdcard/pictures/" + fileName + "."
						+ expandName);
				FileOutputStream fos = new FileOutputStream(file);
				byte buf[] = new byte[128];
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
			urlConn.disconnect();
			flag = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			flag = false;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		Message m = handler.obtainMessage(); // ��ȡһ��Message
		handler.sendMessage(m); // ������Ϣ
	}
}