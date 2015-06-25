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
	private String username;	//保存用户名的变量
	private String pwd;	//保存密码的变量
	private String result = "";	//保存显示结果的变量
	private Handler handler; // 声明一个Handler对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);	//设置布局文件
		Button login = (Button) findViewById(R.id.button1);	//获取登录按钮
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				username = ((EditText) findViewById(R.id.editText1)).getText()
						.toString();	//获取输入的用户名
				pwd = ((EditText) findViewById(R.id.editText2)).getText()
						.toString();	//获取输入的密码

				// 创建一个新线程，实现用户登录
				new Thread(new Runnable() {
					public void run() {
						login();	//用户登录
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
					Intent intent = getIntent(); // 获取Intent对象
					Bundle bundle = new Bundle(); // 实例化传递的数据包
					bundle.putString("result", result);
					intent.putExtras(bundle); // 将数据包保存到intent中
					setResult(0x11, intent); // 设置返回的结果码，并返回调用该Activity的Activity
					finish(); // 关闭当前Activity
				}
				super.handleMessage(msg);
			}
		};
		Button exit = (Button) findViewById(R.id.button2);	//获取退出按钮
		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish(); // 关闭当前Activity
			}
		});
	}

	public void login() {
		String target = "http://192.168.1.66:8081/login/login.jsp"; // 要提交的目标地址
		HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
		// 将要传递的参数保存到List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username)); // 用户名
		params.add(new BasicNameValuePair("pwd", pwd)); // 密码
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = MainActivity.httpclient
					.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				result += EntityUtils.toString(httpResponse.getEntity()); // 获取返回的字符串
			} else {
				result = "请求失败！";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // 输出异常信息
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // 输出异常信息
		} catch (IOException e) {
			e.printStackTrace(); // 输出异常信息
		}
	}
}
