package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll=new LinearLayout(this);	//创建线性布局管理器对象
		ll.setPadding(20,20,20,20);
		TextView tv=new TextView(this);	//创建TextView对象
		tv.setTextSize(24);		//设置字体大小
		tv.setText(R.string.about);	//设置要显示的内容
		ll.addView(tv);	//将TextView添加到线性布局管理器中
		setContentView(ll);		//设置该Activity显示的内容视图
	}
	
}
