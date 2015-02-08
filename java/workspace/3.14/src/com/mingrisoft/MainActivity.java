package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final RadioGroup sex = (RadioGroup) findViewById(R.id.radioGroup1);	//��ȡ��ѡ��ť��
		//Ϊ��ѡ��ť������¼�����
		sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton r = (RadioButton) findViewById(checkedId);	//��ȡ��ѡ��ĵ�ѡ��ť
				Log.i("��ѡ��ť", "����ѡ���ǣ�" + r.getText());
			}
		});
		Button button = (Button) findViewById(R.id.button1);		//��ȡ�ύ��ť
		//Ϊ�ύ��ť��ӵ����¼�����
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//ͨ��forѭ��������ѡ��ť��
				for (int i = 0; i < sex.getChildCount(); i++) {
					RadioButton r = (RadioButton) sex.getChildAt(i);
					if (r.isChecked()) {//�жϵ�ѡ��ť�Ƿ�ѡ��
						Log.i("��ѡ��ť", "�Ա�" + r.getText());
						break;		//����forѭ��
					}
				}
			}
		});

	}
}