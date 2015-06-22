package com.wilsonflying.testanimationvalueanimator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View view){
    	final Button button = (Button) view;
    	ValueAnimator anim = ValueAnimator.ofInt(0, 100);
    	anim.setDuration(5000);
    	anim.start();
    	anim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				Integer value = (Integer) animation.getAnimatedValue();
//				((Button)view).setText(value);
				button.setText(""+value);
			}
		});
    }
}
