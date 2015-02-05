package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll=new LinearLayout(this);	//�������Բ��ֹ���������
		ll.setPadding(20,20,20,20);
		TextView tv=new TextView(this);	//����TextView����
		tv.setTextSize(24);		//���������С
		tv.setText(R.string.about);	//����Ҫ��ʾ������
		ll.addView(tv);	//��TextView��ӵ����Բ��ֹ�������
		setContentView(ll);		//���ø�Activity��ʾ��������ͼ
	}
	
}
