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
		
		final ImageView iv=(ImageView)findViewById(R.id.imageView1);	//获取要应用动画效果的ImageView
		final Animation translateright=AnimationUtils.loadAnimation(this, R.anim.translateright);	//获取“向右奔跑”的动画资源
		final Animation translateleft=AnimationUtils.loadAnimation(this, R.anim.translateleft);	//获取“向左奔跑”的动画资源
		anim=(AnimationDrawable)iv.getBackground();//获取应用的帧动画
		LinearLayout ll=(LinearLayout)findViewById(R.id.linearLayout1);	//获取线性布局管理器
		Toast.makeText(this,"触摸屏幕开始播放...", Toast.LENGTH_SHORT).show();	//显示一个消息提示框
		ll.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				anim.start();	//开始播放帧动画
				iv.startAnimation(translateright);	//播放“向右奔跑”的动画
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
				iv.setBackgroundResource(R.anim.motionleft);	//重新设置ImageView应用的帧动画
				iv.startAnimation(translateleft);		//播放“向左奔跑”的动画	
				anim=(AnimationDrawable)iv.getBackground();//获取应用的帧动画
				anim.start();	//开始播放帧动画
			}
		});
		translateleft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				iv.setBackgroundResource(R.anim.motionright);	//重新设置ImageView应用的帧动画
				iv.startAnimation(translateright);	//播放“向右奔跑”的动画		
				anim=(AnimationDrawable)iv.getBackground();//获取应用的帧动画
				anim.start();	//开始播放帧动画
			}
		});

	}
}
