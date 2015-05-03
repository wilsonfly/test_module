package com.mingrisoft;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
	private EditText nickname; // 声明一个输入昵称的编辑框对象
	private EditText content; // 声明一个输入文本内容的编辑框对象
	private Button button; // 声明一个发表按钮对象
	private Handler handler; // 声明一个Handler对象
	private String result = ""; // 声明一个代表显示内容的字符串
	private TextView resultTV; // 声明一个显示结果的文本框对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		content = (EditText) findViewById(R.id.content); // 获取输入文本内容的EditText组件
		resultTV = (TextView) findViewById(R.id.result); // 获取显示结果的TextView组件
		nickname = (EditText) findViewById(R.id.nickname); // 获取输入昵称的EditText组件
		button = (Button) findViewById(R.id.button); // 获取“发表”按钮组件
		// 为按钮添加单击事件监听器
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(nickname.getText().toString())
						|| "".equals(content.getText().toString())) {
					Toast.makeText(MainActivity.this, "请将内容输入完整！",
							Toast.LENGTH_SHORT).show();
					return;
				}

				// 创建一个新线程，用于从网络上获取文件
				new Thread(new Runnable() {
					public void run() {
						send();
						Message m = handler.obtainMessage(); // 获取一个Message
						handler.sendMessage(m); // 发送消息
					}
				}).start(); // 开启线程

			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result != null) {
					resultTV.setText(result); // 显示获得的结果
					content.setText(""); // 清空内容编辑框
					nickname.setText(""); // 清空昵称编辑框
				}
				super.handleMessage(msg);
			}
		};
	}

	public void send() {
		String target = "http://192.168.1.66:8081/blog/dealPost.jsp";	//要提交的目标地址
		URL url;
		try {
			url = new URL(target);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection(); // 创建一个HTTP连接
			urlConn.setRequestMethod("POST"); // 指定使用POST请求方式
			urlConn.setDoInput(true); // 向连接中写入数据
			urlConn.setDoOutput(true); // 从连接中读取数据
			urlConn.setUseCaches(false); // 禁止缓存
			urlConn.setInstanceFollowRedirects(true);	//自动执行HTTP重定向
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded"); // 设置内容类型
			DataOutputStream out = new DataOutputStream(
					urlConn.getOutputStream()); // 获取输出流
			String param = "nickname="
					+ URLEncoder.encode(nickname.getText().toString(), "utf-8")
					+ "&content="
					+ URLEncoder.encode(content.getText().toString(), "utf-8");	//连接要提交的数据
			out.writeBytes(param);//将要传递的数据写入数据输出流
			out.flush();	//输出缓存
			out.close();	//关闭数据输出流
			// 判断是否响应成功
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStreamReader in = new InputStreamReader(
						urlConn.getInputStream()); // 获得读取的内容
				BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
				String inputLine = null;
				while ((inputLine = buffer.readLine()) != null) {
					result += inputLine + "\n";
				}
				in.close();	//关闭字符输入流
			}
			urlConn.disconnect();	//断开连接
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}