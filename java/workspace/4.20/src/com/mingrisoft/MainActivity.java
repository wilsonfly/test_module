package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		// 自定义的用户登录对话框
		Button button1 = (Button) findViewById(R.id.button1); // 获取布局文件中添加的按钮
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise); // 设置对话框的图标
				builder.setTitle("用户登录："); // 设置对话框的标题
				LayoutInflater inflater=getLayoutInflater();
				View view=inflater.inflate(R.layout.login, null);
				builder.setView(view);
				builder.setPositiveButton("登录", null);								//添加确定按钮
				builder.setNegativeButton("退出", null);								//添加取消按钮
				builder.create().show(); // 创建对话框并显示
			}
		});

	}
}