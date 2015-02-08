package com.mingrisoft;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;



public class MainActivity extends Activity {
	private AnimationDrawable anim;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final ImageView iv=(ImageView)findViewById(R.id.imageView1);	//��ȡҪӦ�ö���Ч����ImageView
		final Animation translateright=AnimationUtils.loadAnimation(this, R.anim.translateright);	//��ȡ�����ұ��ܡ��Ķ�����Դ
		final Animation translateleft=AnimationUtils.loadAnimation(this, R.anim.translateleft);	//��ȡ�������ܡ��Ķ�����Դ
		anim=(AnimationDrawable)iv.getBackground();//��ȡӦ�õ�֡����
		LinearLayout ll=(LinearLayout)findViewById(R.id.linearLayout1);	//��ȡ���Բ��ֹ�����
		Toast.makeText(this,"������Ļ��ʼ����...", Toast.LENGTH_SHORT).show();	//��ʾһ����Ϣ��ʾ��
		ll.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				anim.start();	//��ʼ����֡����
				iv.startAnimation(translateright);	//���š����ұ��ܡ��Ķ���
				return false;
			}
		});
		translateright.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				iv.setBackgroundResource(R.anim.motionleft);	//��������ImageViewӦ�õ�֡����
				iv.startAnimation(translateleft);		//���š������ܡ��Ķ���	
				anim=(AnimationDrawable)iv.getBackground();//��ȡӦ�õ�֡����
				anim.start();	//��ʼ����֡����
			}
		});
		translateleft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				iv.setBackgroundResource(R.anim.motionright);	//��������ImageViewӦ�õ�֡����
				iv.startAnimation(translateright);	//���š����ұ��ܡ��Ķ���		
				anim=(AnimationDrawable)iv.getBackground();//��ȡӦ�õ�֡����
				anim.start();	//��ʼ����֡����
			}
		});

	}
}
