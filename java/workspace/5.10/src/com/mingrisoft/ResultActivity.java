package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result); // ���ø�Activityʹ�õĲ���
		TextView birthday = (TextView) findViewById(R.id.birthday); // ��ȡ��ʾ���յ��ı���
		TextView result = (TextView) findViewById(R.id.result); // ��ȡ��ʾ�������ı���
		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		Info info = (Info) bundle.getSerializable("info"); // ��ȡһ�������л���info����
		birthday.setText("��������������" + info.getBirthday()); // ��ȡ�Ա���ʾ����Ӧ�ı�����

		result.setText( query(info.getBirthday())); // ��ʾ����������
	}

	/**
	 * ���ܸ������ղ�ѯ����
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
		String name = "";// ��ʾ��Ϣ
		if (month > 0 && month < 13 && day > 0 && day < 32) { // ���������º�����Ч
			if ((month == 3 && day > 20) || (month == 4 && day < 21)) {
				name = "���ǰ�������";
			} else if ((month == 4 && day > 20) || (month == 5 && day < 21)) {
				name = "���ǽ�ţ����";
			} else if ((month == 5 && day > 20) || (month == 6 && day < 22)) {
				name = "����˫������";
			} else if ((month == 6 && day > 21) || (month == 7 && day < 23)) {
				name = "���Ǿ�з����";
			} else if ((month == 7 && day > 22) || (month == 8 && day < 23)) {
				name = "����ʨ������";
			} else if ((month == 8 && day > 22) || (month == 9 && day < 23)) {
				name = "���Ǵ�Ů����";
			} else if ((month == 9 && day > 22) || (month == 10 && day < 23)) {
				name = "������ƽ����";
			} else if ((month == 10 && day > 22) || (month == 11 && day < 22)) {
				name = "������Ы����";
			} else if ((month == 11 && day > 21) || (month == 12 && day < 22)) {
				name = "������������";
			} else if ((month == 12 && day > 21) || (month == 1 && day < 20)) {
				name = "����Ħ������";
			} else if ((month == 1 && day > 19) || (month == 2 && day < 19)) {
				name = "����ˮţ����";
			} else if ((month == 2 && day > 18) || (month == 3 && day < 21)) {
				name = "����˫������";
			}
			name = month + "��" + day + "��  " + name;
		} else {// ���������º�����Ч
			name = "����������ո�ʽ����ȷ���߲�����ʵ���գ�";
		}
		return name;// ������������ʾ��Ϣ
	}
}
