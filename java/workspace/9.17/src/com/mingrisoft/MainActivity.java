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
		
		final Animation rotate=AnimationUtils.loadAnimation(this, R.anim.anim_rotate);	//获取“旋转”动画资源
		final Animation translate=AnimationUtils.loadAnimation(this, R.anim.anim_translate);	//获取“平移”动画资源
		final Animation scale=AnimationUtils.loadAnimation(this, R.anim.anim_scale);	//获取“缩放”动画资源
		final Animation alpha=AnimationUtils.loadAnimation(this, R.anim.anim_alpha);	//获取“透明度变化”动画资源
		final ImageView iv=(ImageView)findViewById(R.id.imageView1);	//获取要应用动画效果的ImageView
		Button button1=(Button)findViewById(R.id.button1);	//获取“旋转”按钮
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(rotate);		//播放“旋转”动画
				
			}
		});	
		Button button2=(Button)findViewById(R.id.button2);	//获取“平移”按钮
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(translate);	//播放“平移”动画
				
			}
		});
		Button button3=(Button)findViewById(R.id.button3);	//获取“缩放”按钮
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(scale);	//播放“缩放”动画
				
			}
		});		
		Button button4=(Button)findViewById(R.id.button4);	//获取“透明度渐变”按钮
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iv.startAnimation(alpha);	//播放“透明度渐变”动画
				
			}
		});			
	}
}
