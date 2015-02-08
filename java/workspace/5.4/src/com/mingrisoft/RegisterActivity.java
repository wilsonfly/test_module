package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);	//���ø�Activity��Ҫ��ʾ��������ͼ
		final Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();	//��ȡ���ݵ����ݰ�
		TextView user=(TextView)findViewById(R.id.user);		//��ȡ��ʾ�û�����TextView���
		user.setText("�û�����"+bundle.getString("user"));		//��ȡ������û�������ʾ��TextView�����
		TextView pwd=(TextView)findViewById(R.id.pwd);		//��ȡ��ʾ�����TextView���
		pwd.setText("���룺"+bundle.getString("pwd"));		//��ȡ��������벢��ʾ��TextView�����
		TextView email=(TextView)findViewById(R.id.email);		//��ȡ��ʾE-mail��TextView���
		email.setText("E-mail��"+bundle.getString("email"));		//��ȡ�����E-mail����ʾ��TextView�����
		Button button=(Button)findViewById(R.id.back);	//��ȡ��������һ������ť
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(0x717,intent);	//���÷��صĽ���룬�����ص��ø�Activity��Activity
				finish();		//�رյ�ǰActivity
				
			}
		});
	}

}
