package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	public TextView text2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FrameLayout frameLayout = new FrameLayout(this); // 创建帧部局管理器
		frameLayout.setBackgroundDrawable(this.getResources().getDrawable(
				R.drawable.background)); // 设置背景
		setContentView(frameLayout); // 设置在Activity中显示frameLayout

		TextView text1 = new TextView(this);
		text1.setText("在代码中控制UI界面"); // 设置显示的文字
		text1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 24); // 设置文字大小，单位为像素
		text1.setTextColor(Color.rgb(1, 1, 1)); // 设置文字的颜色
		frameLayout.addView(text1); // 将text1添加到布局管理器中

		text2 = new TextView(this);
		text2.setText("单击进入游戏......"); // 设置显示文字
		text2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 80); // 设置文字大小，单位为像素
		text2.setTextColor(Color.rgb(1, 1, 1)); // 设置文字的颜色
		LayoutParams params = new LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT); // 创建保存布局参数的对象
		params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL; // 设置居中显示
		text2.setLayoutParams(params); // 设置布局参数

		text2.setOnClickListener(new OnClickListener() { // 为text2添加单击事件监听

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(MainActivity.this).setTitle("系统提示") // 设置对话框的标题
						.setMessage("游戏有风险，进入需谨慎，真的要进入吗？") // 设置对话框的显示内容
						.setPositiveButton("确定", // 为确定按钮添加单击事件
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Log.i("3.2", "进入游戏"); // 输出消息日志
									}
								}).setNegativeButton("退出", // 为取消按钮添加单击事件
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Log.i("3.2", "退出游戏"); // 输出消息日志
										finish(); // 结束游戏
									}
								}).show();
			}
		});
		frameLayout.addView(text2);				// 将text2添加到布局管理器中

	}
}