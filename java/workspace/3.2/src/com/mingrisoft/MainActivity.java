package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	public TextView text2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FrameLayout frameLayout = new FrameLayout(this); // ����֡���ֹ�����
		frameLayout.setBackgroundDrawable(this.getResources().getDrawable(
				R.drawable.background)); // ���ñ���
		setContentView(frameLayout); // ������Activity����ʾframeLayout

		TextView text1 = new TextView(this);
		text1.setText("�ڴ����п���UI����"); // ������ʾ������
		text1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 24); // �������ִ�С����λΪ����
		text1.setTextColor(Color.rgb(1, 1, 1)); // �������ֵ���ɫ
		frameLayout.addView(text1); // ��text1��ӵ����ֹ�������

		text2 = new TextView(this);
		text2.setText("����������Ϸ......"); // ������ʾ����
		text2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 80); // �������ִ�С����λΪ����
		text2.setTextColor(Color.rgb(1, 1, 1)); // �������ֵ���ɫ
		LayoutParams params = new LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT); // �������沼�ֲ����Ķ���
		params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL; // ���þ�����ʾ
		text2.setLayoutParams(params); // ���ò��ֲ���

		text2.setOnClickListener(new OnClickListener() { // Ϊtext2��ӵ����¼�����

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(MainActivity.this).setTitle("ϵͳ��ʾ") // ���öԻ���ı���
						.setMessage("��Ϸ�з��գ���������������Ҫ������") // ���öԻ������ʾ����
						.setPositiveButton("ȷ��", // Ϊȷ����ť��ӵ����¼�
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Log.i("3.2", "������Ϸ"); // �����Ϣ��־
									}
								}).setNegativeButton("�˳�", // Ϊȡ����ť��ӵ����¼�
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Log.i("3.2", "�˳���Ϸ"); // �����Ϣ��־
										finish(); // ������Ϸ
									}
								}).show();
			}
		});
		frameLayout.addView(text2);				// ��text2��ӵ����ֹ�������

	}
}