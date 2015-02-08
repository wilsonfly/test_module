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
	private int year; // ��
	private int month;
	private int day;
	private int hour;
	private int minute;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker1); // ��ȡ����ʰȡ���
		TimePicker timepicker = (TimePicker) findViewById(R.id.timePicker1); // ��ȡʱ��ʰȡ���
		timepicker.setIs24HourView(true);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR); // ��ȡ��ǰ���
		month = calendar.get(Calendar.MONTH); // ��ȡ��ǰ�·�
		day = calendar.get(Calendar.DAY_OF_MONTH); // ��ȡ��ǰ��
		hour = calendar.get(Calendar.HOUR_OF_DAY); // ��ȡ��ǰСʱ��
		minute = calendar.get(Calendar.MINUTE); // ��ȡ��ǰ������
		timepicker.setCurrentHour(hour);		//���õ�ǰ��Сʱ��������ӵģ�
		timepicker.setCurrentMinute(minute);		//���õ�ǰ�ķ�����������ӵģ�
		// ��ʼ������ʰȡ�������ڳ�ʼ��ʱָ��������
		datepicker.init(year, month, day, new OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker arg0, int year, int month,
					int day) {
				MainActivity.this.year = year; // �ı�year���Ե�ֵ
				MainActivity.this.month = month; // �ı�month���Ե�ֵ
				MainActivity.this.day = day; // �ı�day���Ե�ֵ
				show(year, month, day, hour, minute); // ͨ����Ϣ����ʾ����ʱ��
			}
		});
		// Ϊʱ��ʰȡ�����ü�����
		timepicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				MainActivity.this.hour = hourOfDay; // �ı�hour���Ե�ֵ
				MainActivity.this.minute = minute; // �ı�minute���Ե�ֵ
				show(year, month, day, hourOfDay, minute); // ͨ����Ϣ����ʾѡ�������ʱ��
			}
		});

	}

	private void show(int year, int month, int day, int hour, int minute) {
		String str = year + "��" + (month+1) + "��" + day + "��  " + hour + ":"
				+ minute; // ��ȡʰȡ�����õ����ں�ʱ��
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show(); // ��ʾ��Ϣ��ʾ��
	}
}