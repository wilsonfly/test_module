package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class BigActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.big);	//����ʹ�õĲ����ļ�
		Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();	//��ȡ���ݹ��������ݰ�
		int imgId=bundle.getInt("imgId");
		ImageView iv=(ImageView)findViewById(R.id.imageView1);
		iv.setImageResource(imgId);		//����Ҫ��ʾ��ͼƬ
		
		Button button=(Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();	//����
			}
		});
		
	}

}
