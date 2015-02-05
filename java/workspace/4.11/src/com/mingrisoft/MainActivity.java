package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//通过makeText()方法创建消息提示框
		Toast.makeText(this, "我是通过makeText()方法创建的消息提示框", Toast.LENGTH_LONG).show();
		//通过构造方法创建消息提示框
		Toast toast=new Toast(this);
		toast.setDuration(Toast.LENGTH_SHORT);	//设置持续时间
		toast.setGravity(Gravity.CENTER, 0, 0);	//设置对齐方式 
		LinearLayout ll=new LinearLayout(this);	//创建一个线性布局管理器
		ImageView iv=new ImageView(this);	//创建一个ImageView
		iv.setImageResource(R.drawable.alerm);	//设置要显示的图片
		iv.setPadding(0, 0, 5, 0);	//设置ImageView的右边距
		ll.addView(iv);	//将ImageView添加到线性布局管理器中
		TextView tv=new TextView(this);		//创建一个TextView
		tv.setText("我是通过构造方法创建的消息提示框");	//为TextView设置文本内容
		ll.addView(tv);	//将TextView添加到线性布局管理器中
		toast.setView(ll);	//设置消息提示框中要显示的视图
		toast.show();	//显示消息提示框
	}
}