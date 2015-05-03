package com.mingrisoft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText urlText;	//下载地址编辑框
	private Button button;	//下载按钮
	private Handler handler; // 声明一个Handler对象
	private boolean flag = false;	//标记是否成功的变量

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		urlText = (EditText) findViewById(R.id.editText_url); // 获取布局管理器中添加的下载地址编辑框
		button = (Button) findViewById(R.id.button_go); // 获取布局管理器中添加的下载按钮
		// 为“下载”按钮添加单击事件监听器
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建一个新线程，用于从网络上获取文件
				new Thread(new Runnable() {
					public void run() {
						try {
							String sourceUrl = urlText.getText().toString(); // 获取下载地址
							URL url = new URL(sourceUrl); // 创建下载地址对应的URL对象
							HttpURLConnection urlConn = (HttpURLConnection) url
									.openConnection(); // 创建一个连接
							InputStream is = urlConn.getInputStream(); // 获取输入流对象
							if (is != null) {
								String expandName = sourceUrl.substring(
										sourceUrl.lastIndexOf(".") + 1,
										sourceUrl.length()).toLowerCase(); // 获取文件的扩展名
								String fileName = sourceUrl.substring(
										sourceUrl.lastIndexOf("/") + 1,
										sourceUrl.lastIndexOf(".")); // 获取文件名
								File file = new File("/sdcard/pictures/"
										+ fileName + "." + expandName); // 在SD卡上创建文件
								FileOutputStream fos = new FileOutputStream(
										file); // 创建一个文件输出流对象
								byte buf[] = new byte[128];// 创建一个字节数组
								// 读取文件到输出流对象中
								while (true) {
									int numread = is.read(buf);
									if (numread <= 0) {
										break;
									} else {
										fos.write(buf, 0, numread);
									}
								}
							}
							is.close(); // 关闭输入流对象
							urlConn.disconnect(); // 关闭连接
							flag = true;
						} catch (MalformedURLException e) {
							e.printStackTrace(); // 输出异常信息
							flag = false;
						} catch (IOException e) {
							e.printStackTrace(); // 输出异常信息
							flag = false;
						}
						Message m = handler.obtainMessage(); // 获取一个Message
						handler.sendMessage(m); // 发送消息
					}
				}).start(); // 开启线程

			}
		});
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (flag) {
					Toast.makeText(MainActivity.this, "文件下载完成！",
							Toast.LENGTH_SHORT).show(); // 显示消息提示
				} else {
					Toast.makeText(MainActivity.this, "文件下载失败！",
							Toast.LENGTH_SHORT).show(); // 显示消息提示
				}
				super.handleMessage(msg);
			}
		};
	}
}