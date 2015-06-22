package com.wilsonflying.testanimationraymenu;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private int[] resId = { R.id.iv_a, R.id.iv_b, R.id.iv_c, R.id.iv_d, R.id.iv_e, R.id.iv_f };
	private List<ImageView> list = new ArrayList<ImageView>();
	private boolean isShowed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for (int i = 0; i < resId.length; i++) {
			ImageView iv = (ImageView) findViewById(resId[i]);
			iv.setOnClickListener(this);
			list.add(iv);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_a:
			if( !isShowed ){
				showMenu();
				isShowed = true;
			}else{
				hideMenu();
				isShowed = false;
			}
			break;

		default:
			Toast.makeText(MainActivity.this, "onclick id:"+v.getId(), Toast.LENGTH_SHORT).show();
			break;
		}
	}

	private void hideMenu() {
		// TODO Auto-generated method stub
		for (int i = 1; i < resId.length; i++) {
			ObjectAnimator anim = ObjectAnimator.ofFloat(list.get(i), "translationY", 180f*i, 0);
			anim.setDuration(500);
			anim.setStartDelay(250);
			anim.start();
		}
	}

	private void showMenu() {
		// TODO Auto-generated method stub
		for (int i = 1; i < resId.length; i++) {
			ObjectAnimator anim = ObjectAnimator.ofFloat(list.get(i), "translationY", 0f, 180f*i);
			anim.setDuration(500);
			
			anim.setStartDelay(250);
			anim.setInterpolator(new BounceInterpolator());
			
			anim.start();
		}	
	}

}
