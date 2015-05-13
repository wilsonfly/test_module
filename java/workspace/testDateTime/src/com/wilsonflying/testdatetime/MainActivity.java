package com.wilsonflying.testdatetime;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		DatePicker datepicker = (DatePicker) findViewById(R.id.datepicker);
		TimePicker timepicker = (TimePicker) findViewById(R.id.timepicker);
		
		timepicker.setIs24HourView(true);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		
		timepicker.setCurrentHour(hour);
		timepicker.setCurrentMinute(minute);
		timepicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				MainActivity.this.hour = hourOfDay;
				MainActivity.this.minute = minute;
				showTheTime(year, month, day, hourOfDay, minute);
			}
		});
		
		datepicker.init(year, month, day, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				MainActivity.this.year = year;
				MainActivity.this.month = monthOfYear;
				MainActivity.this.day = dayOfMonth;
				showTheTime(year, month, day, hour, minute);
			}
		});
	}
	
	public void showTheTime(int year, int month, int day, int hour, int minute){
		String time = year+"/"+month+"/"+day+" "+hour+":"+minute;
		Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
	}
}
