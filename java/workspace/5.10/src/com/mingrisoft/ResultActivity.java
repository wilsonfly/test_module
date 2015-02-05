package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result); // 设置该Activity使用的布局
		TextView birthday = (TextView) findViewById(R.id.birthday); // 获取显示生日的文本框
		TextView result = (TextView) findViewById(R.id.result); // 获取显示星座的文本框
		Intent intent = getIntent(); // 获取Intent对象
		Bundle bundle = intent.getExtras(); // 获取传递的数据包
		Info info = (Info) bundle.getSerializable("info"); // 获取一个可序列化的info对象
		birthday.setText("您的阳历生日是" + info.getBirthday()); // 获取性别并显示到相应文本框中

		result.setText( query(info.getBirthday())); // 显示计算后的星座
	}

	/**
	 * 功能根据生日查询星座
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	public String query(String birthday) {
		int month=0;
		int day=0;
		try{
			month=Integer.parseInt(birthday.substring(5, 7));
			day=Integer.parseInt(birthday.substring(8, 10));
		}catch(Exception e){
			e.printStackTrace();
		}
		String name = "";// 提示信息
		if (month > 0 && month < 13 && day > 0 && day < 32) { // 如果输入的月和日有效
			if ((month == 3 && day > 20) || (month == 4 && day < 21)) {
				name = "您是白羊座！";
			} else if ((month == 4 && day > 20) || (month == 5 && day < 21)) {
				name = "您是金牛座！";
			} else if ((month == 5 && day > 20) || (month == 6 && day < 22)) {
				name = "您是双子座！";
			} else if ((month == 6 && day > 21) || (month == 7 && day < 23)) {
				name = "您是巨蟹座！";
			} else if ((month == 7 && day > 22) || (month == 8 && day < 23)) {
				name = "您是狮子座！";
			} else if ((month == 8 && day > 22) || (month == 9 && day < 23)) {
				name = "您是处女座！";
			} else if ((month == 9 && day > 22) || (month == 10 && day < 23)) {
				name = "您是天平座！";
			} else if ((month == 10 && day > 22) || (month == 11 && day < 22)) {
				name = "您是天蝎座！";
			} else if ((month == 11 && day > 21) || (month == 12 && day < 22)) {
				name = "您是射手座！";
			} else if ((month == 12 && day > 21) || (month == 1 && day < 20)) {
				name = "您是摩羯座！";
			} else if ((month == 1 && day > 19) || (month == 2 && day < 19)) {
				name = "您是水牛座！";
			} else if ((month == 2 && day > 18) || (month == 3 && day < 21)) {
				name = "您是双鱼座！";
			}
			name = month + "月" + day + "日  " + name;
		} else {// 如果输入的月和日无效
			name = "您输入的生日格式不正确或者不是真实生日！";
		}
		return name;// 返回星座或提示信息
	}
}
