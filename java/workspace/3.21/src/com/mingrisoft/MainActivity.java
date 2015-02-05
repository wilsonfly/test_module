package com.mingrisoft;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int year; // 年
	private int month;
	private int day;
	private int hour;
	private int minute;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker1); // 获取日期拾取组件
		TimePicker timepicker = (TimePicker) findViewById(R.id.timePicker1); // 获取时间拾取组件
		timepicker.setIs24HourView(true);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR); // 获取当前年份
		month = calendar.get(Calendar.MONTH); // 获取当前月份
		day = calendar.get(Calendar.DAY_OF_MONTH); // 获取当前日
		hour = calendar.get(Calendar.HOUR_OF_DAY); // 获取当前小时数
		minute = calendar.get(Calendar.MINUTE); // 获取当前分钟数
		timepicker.setCurrentHour(hour);		//设置当前的小时数（后添加的）
		timepicker.setCurrentMinute(minute);		//设置当前的分钟数（后添加的）
		// 初始化日期拾取器，并在初始化时指定监听器
		datepicker.init(year, month, day, new OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker arg0, int year, int month,
					int day) {
				MainActivity.this.year = year; // 改变year属性的值
				MainActivity.this.month = month; // 改变month属性的值
				MainActivity.this.day = day; // 改变day属性的值
				show(year, month, day, hour, minute); // 通过消息框显示日期时间
			}
		});
		// 为时间拾取器设置监听器
		timepicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				MainActivity.this.hour = hourOfDay; // 改变hour属性的值
				MainActivity.this.minute = minute; // 改变minute属性的值
				show(year, month, day, hourOfDay, minute); // 通过消息框显示选择的日期时间
			}
		});

	}

	private void show(int year, int month, int day, int hour, int minute) {
		String str = year + "年" + (month+1) + "月" + day + "日  " + hour + ":"
				+ minute; // 获取拾取器设置的日期和时间
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show(); // 显示消息提示框
	}
}