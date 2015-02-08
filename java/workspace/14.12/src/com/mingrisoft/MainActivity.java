package com.mingrisoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
	private EditText content;	//声明一个输入文本内容的编辑框对象
	private Button button;	//声明一个发表按钮对象
	private Handler handler; // 声明一个Handler对象
	private String result = "";	//声明一个代表显示内容的字符串
	private TextView resultTV;	//声明一个显示结果的文本框对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		content = (EditText) findViewById(R.id.content);	//获取输入文本内容的EditText组件
		resultTV = (TextView) findViewById(R.id.result);	//获取显示结果的TextView组件
		button = (Button) findViewById(R.id.button);	//获取“发表”按钮组件
		//为按钮添加单击事件监听器
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "请输入要发表的内容！",
							Toast.LENGTH_SHORT).show();	//显示消息提示
					return;
				}

				// 创建一个新线程，用于发送并读取微博信息
				new Thread(new Runnable() {
					public void run() {
						send();	//发送文本内容到Web服务器
						Message m = handler.obtainMessage(); // 获取一个Message
						handler.sendMessage(m); // 发送消息
					}
				}).start(); // 开启线程
			}
		});
		//创建一个Handler对象
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); // 显示获得的结果
					content.setText("");		//清空文本框
				}
				super.handleMessage(msg);
			}
		};
	}

	public void send() {
		String target="";
		target = "http://192.168.1.66:8081/blog/deal_sjlx01.jsp?content="
					+URLEncoder.encode(content.getText().toString().trim());	//要访问的URL地址
		URL url;
		try {
			url = new URL(target);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();	//创建一个HTTP连接
			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream()); // 获得读取的内容
			BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
			String inputLine = null;
			//通过循环逐行读取输入流中的内容
			while ((inputLine = buffer.readLine()) != null) {
				result += inputLine + "\n";
			}
			in.close();	//关闭字符输入流对象
			urlConn.disconnect();	//断开连接
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}