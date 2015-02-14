package com.wilsonflying.testtoast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		//����һ����
		Toast.makeText(MainActivity.this, "maketext(����ʽ��ʾ����ʾ��", Toast.LENGTH_SHORT).show();
		
		//��������
		Toast toast = new Toast(MainActivity.this);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		
		LinearLayout ll = new LinearLayout(MainActivity.this);
		
		ImageView iv = new ImageView(MainActivity.this);
		iv.setImageResource(R.drawable.alerm);
		iv.setPadding(0, 0, 5, 0);
		ll.addView(iv);
		
		TextView tv = new TextView(MainActivity.this);
		tv.setText("�ֶ�����textview��ӵ������еķ�ʽ��ʾ����ʾ��Ϣ");
		ll.addView(tv);
		
		toast.setView(ll);
		toast.show();
	}
}
