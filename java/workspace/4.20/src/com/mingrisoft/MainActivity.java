package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		// �Զ�����û���¼�Ի���
		Button button1 = (Button) findViewById(R.id.button1); // ��ȡ�����ļ�����ӵİ�ť
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise); // ���öԻ����ͼ��
				builder.setTitle("�û���¼��"); // ���öԻ���ı���
				LayoutInflater inflater=getLayoutInflater();
				View view=inflater.inflate(R.layout.login, null);
				builder.setView(view);
				builder.setPositiveButton("��¼", null);								//���ȷ����ť
				builder.setNegativeButton("�˳�", null);								//���ȡ����ť
				builder.create().show(); // �����Ի�����ʾ
			}
		});

	}
}