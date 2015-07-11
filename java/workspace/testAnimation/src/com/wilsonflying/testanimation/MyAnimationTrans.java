package com.wilsonflying.testanimation;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

public class MyAnimationTrans extends Animation {

//	@Override
//	public void initialize(int width, int height, int parentWidth,
//			int parentHeight) {
//		// TODO Auto-generated method stub
//		super.initialize(width, height, parentWidth, parentHeight);
//	}
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		// TODO Auto-generated method stub
		
//		t.setAlpha(0.5f);
//		t.getMatrix().setTranslate(200*interpolatedTime, 200*interpolatedTime);
//		t.getMatrix().setRotate(20, 0.5f, 0.5f);
		t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*10)*50), 0);
		
//		AnimationSet set = new AnimationSet(true);
//		RotateAnimation ra = new RotateAnimation(0, 10);
//		set.addAnimation(ra);
//		AnimationSet set2 = new AnimationSet(true);
//		RotateAnimation ra2 = new RotateAnimation(0, 10);
//		set.addAnimation(ra2);
//		t.set(set);//wrong
		
		
		super.applyTransformation(interpolatedTime, t);
	}
	
	
}
