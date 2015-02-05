package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final Animation rotate=AnimationUtils.loadAnimation(this, R.anim.anim_rotate);	//��ȡ����ת��������Դ
		final Animation translate=AnimationUtils.loadAnimation(this, R.anim.anim_translate);	//��ȡ��ƽ�ơ�������Դ
		final Animation scale=AnimationUtils.loadAnimation(this, R.anim.anim_scale);	//��ȡ�����š�������Դ
		final Animation alpha=AnimationUtils.loadAnimation(this, R.anim.anim_alpha);	//��ȡ��͸���ȱ仯��������Դ
		final ImageView iv=(ImageView)findViewById(R.id.imageView1);	//��ȡҪӦ�ö���Ч����ImageView
		Button button1=(Button)findViewById(R.id.button1);	//��ȡ����ת����ť
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(rotate);		//���š���ת������
				
			}
		});	
		Button button2=(Button)findViewById(R.id.button2);	//��ȡ��ƽ�ơ���ť
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(translate);	//���š�ƽ�ơ�����
				
			}
		});
		Button button3=(Button)findViewById(R.id.button3);	//��ȡ�����š���ť
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(scale);	//���š����š�����
				
			}
		});		
		Button button4=(Button)findViewById(R.id.button4);	//��ȡ��͸���Ƚ��䡱��ť
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(alpha);	//���š�͸���Ƚ��䡱����
				
			}
		});			
	}
}
