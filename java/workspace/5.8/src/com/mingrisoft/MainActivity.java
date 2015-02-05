package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Demonstration of using fragments to implement different activity layouts.
 * This sample provides a different layout (and activity flow) when run in
 * landscape.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // ���ø�Activityʹ�õĲ���
		Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();		//��ȡ���ݵ����ݰ�
		String nickname=bundle.getString("nickname");	//��ȡ���ݹ������ǳ�
		TextView tv=(TextView)findViewById(R.id.nickname);	//��ȡ������ʾ��ǰ��¼�û���TextView���
		tv.setText("��ǰ��¼��"+nickname);	//��ʾ��ǰ��¼�û����ǳ�
		
		Button button=(Button)findViewById(R.id.m_exit);	//��ȡ���˳���¼����ť
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();		//�رյ�ǰActivity
			}
		});
	}
}
