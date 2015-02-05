package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//ͨ��makeText()����������Ϣ��ʾ��
		Toast.makeText(this, "����ͨ��makeText()������������Ϣ��ʾ��", Toast.LENGTH_LONG).show();
		//ͨ�����췽��������Ϣ��ʾ��
		Toast toast=new Toast(this);
		toast.setDuration(Toast.LENGTH_SHORT);	//���ó���ʱ��
		toast.setGravity(Gravity.CENTER, 0, 0);	//���ö��뷽ʽ 
		LinearLayout ll=new LinearLayout(this);	//����һ�����Բ��ֹ�����
		ImageView iv=new ImageView(this);	//����һ��ImageView
		iv.setImageResource(R.drawable.alerm);	//����Ҫ��ʾ��ͼƬ
		iv.setPadding(0, 0, 5, 0);	//����ImageView���ұ߾�
		ll.addView(iv);	//��ImageView��ӵ����Բ��ֹ�������
		TextView tv=new TextView(this);		//����һ��TextView
		tv.setText("����ͨ�����췽����������Ϣ��ʾ��");	//ΪTextView�����ı�����
		ll.addView(tv);	//��TextView��ӵ����Բ��ֹ�������
		toast.setView(ll);	//������Ϣ��ʾ����Ҫ��ʾ����ͼ
		toast.show();	//��ʾ��Ϣ��ʾ��
	}
}