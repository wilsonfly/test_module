package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageButton button1 = (ImageButton) findViewById(R.id.exit); // ��ȡ���˳�����ť
		// Ϊ���˳�����ť��ӵ����¼�������
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
						.create();
				alert.setIcon(R.drawable.advise); // ���öԻ����ͼ��
				alert.setTitle("�˳���"); // ���öԻ���ı���
				alert.setMessage("���Ҫ�˳���������Ϸ��"); // ����Ҫ��ʾ������
				// ���ȡ����ť
				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "��",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				// ���ȷ����ť
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "�ǵ�",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish(); // ����ϵͳ������

							}
						});
				alert.show(); // �����Ի�����ʾ
			}
		});

	}
}