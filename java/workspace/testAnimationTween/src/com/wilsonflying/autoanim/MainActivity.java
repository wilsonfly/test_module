package com.wilsonflying.autoanim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private boolean isRunning = false;
	AnimationDrawable anim_pig;
	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);

		/********************补间动画部分********************/
		final Animation rotate = AnimationUtils.loadAnimation(this,
				R.anim.anim_rotate);
		final Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.anim_translate);
		final Animation scale = AnimationUtils.loadAnimation(this,
				R.anim.animj_scale);
		final Animation alpha = AnimationUtils.loadAnimation(this,
				R.anim.anim_alpha);

		image = (ImageView) findViewById(R.id.image);

		Button button_rotate = (Button) findViewById(R.id.button_rotate);
		Button button_translate = (Button) findViewById(R.id.button_translate);
		Button button_scale = (Button) findViewById(R.id.button_scale);
		Button button_alpha = (Button) findViewById(R.id.button_alpha);

		button_rotate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// rotate.start();
				image.startAnimation(rotate);
			}
		});

		button_translate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				image.startAnimation(translate);
			}
		});

		button_scale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				image.startAnimation(scale);
			}
		});

		button_alpha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				image.startAnimation(alpha);
			}
		});
		/********************补间动画部分********************/
		
		/********************小猪快跑部分********************/
		final ImageView imagePig = (ImageView) findViewById(R.id.pig);
		anim_pig = (AnimationDrawable) imagePig.getBackground();
		final Button button_pig = (Button) findViewById(R.id.button_pig);
		final Animation animMoveToRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_pig_runto_right);
		final Animation animMoveToLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_pig_runto_left);
		
		button_pig.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!isRunning){
					anim_pig.start();
					imagePig.startAnimation(animMoveToRight);
					button_pig.setText("小猪停下");
					isRunning = true;
				}else{
					anim_pig.stop();
					imagePig.clearAnimation();
					button_pig.setText("小猪快跑");
					isRunning = false;
				}
			}
		});
		animMoveToRight.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				imagePig.setBackgroundResource(R.anim.anim_pig_leftward);
				imagePig.startAnimation(animMoveToLeft);
				anim_pig = (AnimationDrawable) imagePig.getBackground();
				anim_pig.start();
			}
		});
		
		animMoveToLeft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				imagePig.setBackgroundResource(R.anim.anim_pig_rightward);
				imagePig.startAnimation(animMoveToRight);
				anim_pig = (AnimationDrawable) imagePig.getBackground();
				anim_pig.start();
			}
		});
		/********************小猪快跑部分********************/

	}
	
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.button_alpha_multi:
			Animation anim_alpha_multi = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_alpha_multi);
			image.startAnimation(anim_alpha_multi);
			break;
		case R.id.btn_activity:
			Intent intent = new Intent(MainActivity.this, Aty_Second.class);
			startActivity(intent);
			overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out);
			break;
		default:
			break;
		}
	}
	
	
}
