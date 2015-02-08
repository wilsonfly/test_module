package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageButton button1 = (ImageButton) findViewById(R.id.exit); // 获取“退出”按钮
		// 为“退出”按钮添加单击事件监听器
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
						.create();
				alert.setIcon(R.drawable.advise); // 设置对话框的图标
				alert.setTitle("退出？"); // 设置对话框的标题
				alert.setMessage("真的要退出泡泡龙游戏吗？"); // 设置要显示的内容
				// 添加取消按钮
				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "不",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				// 添加确定按钮
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "是的",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish(); // 返回系统主界面

							}
						});
				alert.show(); // 创建对话框并显示
			}
		});

	}
}