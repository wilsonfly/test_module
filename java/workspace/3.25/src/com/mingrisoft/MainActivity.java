package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageButton imageButton=(ImageButton)findViewById(R.id.start);		//��ȡ���밴ť
		CheckBox checkbox=(CheckBox)findViewById(R.id.checkBox1);	//��ȡ�����ļ�����ӵĸ�ѡ��ť
		//Ϊ��ѡ��ť��Ӽ�����
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){			//����ѡ��ť��ѡ��
					imageButton.setVisibility(View.VISIBLE);		//���ý��밴ť��ʾ
				}else{
					imageButton.setVisibility(View.INVISIBLE);		//���ý��밴ť����ʾ
				}
				imageButton.invalidate();	//�ػ�ImageButton				
			}
		});
		//Ϊ���밴ť��ӵ����¼�������
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(MainActivity.this, "������Ϸ...", Toast.LENGTH_SHORT).show();	//��ʾ��Ϣ��ʾ��
			
			}
		});
	}
}