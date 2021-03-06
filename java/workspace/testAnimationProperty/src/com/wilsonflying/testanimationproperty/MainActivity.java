package com.wilsonflying.testanimationproperty;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private ImageView mImageView;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mImageView = (ImageView) findViewById(R.id.iv);
        mImageView.setOnClickListener(this);
        
        
    }

    public void onBtnClick(View view){
    	switch (view.getId()) {
		case R.id.btn_tweenMove:
			TranslateAnimation anim = new TranslateAnimation(0, 200, 0, 0);
			anim.setDuration(1000);
//			anim.setFillAfter(true);
			mImageView.startAnimation(anim);
			break;

		case R.id.btn_move:
			ObjectAnimator.ofFloat(mImageView, "translationX", 0F, 200F).setDuration(1000).start();
			ObjectAnimator.ofFloat(mImageView, "translationY", 0F, 200F).setDuration(1000).start();
			ObjectAnimator.ofFloat(mImageView, "rotation", 0F, 360F).setDuration(1000).start();
			break;
		case R.id.btn_move2:
			PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0F, 200F);
			PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY", 0F, 200F);
			PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation", 0F, 360F);
			ObjectAnimator.ofPropertyValuesHolder(mImageView, p1, p2, p3).setDuration(1000).start();
			break;
		case R.id.btn_move3:
			ObjectAnimator a1 = ObjectAnimator.ofFloat(mImageView, "translationX", 0, 200F);
			ObjectAnimator a2 = ObjectAnimator.ofFloat(mImageView, "translationY", 0, 200F);
			ObjectAnimator a3 = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360F);
			AnimatorSet set = new AnimatorSet();
			set.playTogether(a1, a2, a3);
			set.setDuration(1000);
			set.start();
			break;
		case R.id.btn_move4:
			ObjectAnimator a4 = ObjectAnimator.ofFloat(mImageView, "translationX", 0, 200F);
			ObjectAnimator a5 = ObjectAnimator.ofFloat(mImageView, "translationY", 0, 200F);
			ObjectAnimator a6 = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360F);
			AnimatorSet set2 = new AnimatorSet();
			set2.playSequentially(a4, a5, a6);
			set2.setDuration(1000);
			set2.start();
			break;
		case R.id.btn_move5:
			ObjectAnimator a7 = ObjectAnimator.ofFloat(mImageView, "translationX", 0, 200F);
			ObjectAnimator a8 = ObjectAnimator.ofFloat(mImageView, "translationY", 0, 200F);
			ObjectAnimator a9 = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360F);
			AnimatorSet set3 = new AnimatorSet();
			set3.play(a7).with(a8);
			set3.play(a9).after(a7);
			set3.setDuration(1000);
			set3.start();
			break;
			
		case R.id.btn_move6:
			ObjectAnimator a10 = ObjectAnimator.ofFloat(mImageView, "translationX", 0F, 200F);
			a10.setDuration(1000);
			a10.start();

			a10.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}
			});
//			a10.start();//在listerner上下都可以

			break;
		case R.id.btn_move7:
			ObjectAnimator a11 = ObjectAnimator.ofFloat(mImageView, "translationX", 0F, 200F);
			a11.setDuration(1000);
			a11.start();
			a11.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					super.onAnimationEnd(animation);
					Toast.makeText(MainActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
				}
			});
			
		default:
			break;
		}
    	
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
	}
 
}
