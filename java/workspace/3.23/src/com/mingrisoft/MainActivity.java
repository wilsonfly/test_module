package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageButton imageButton=(ImageButton)findViewById(R.id.start);		//��ȡ���밴ť
		//Ϊ��ť��ӵ����¼�������
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(MainActivity.this, "������Ϸ...", Toast.LENGTH_SHORT).show();	//��ʾ��Ϣ��ʾ��
			
			}
		});
	}
}