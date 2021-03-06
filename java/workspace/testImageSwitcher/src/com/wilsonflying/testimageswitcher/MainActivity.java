package com.wilsonflying.testimageswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	int[] imgId = new int[]{R.drawable.img01,R.drawable.img02,R.drawable.img03,R.drawable.img04,R.drawable.img05,R.drawable.img06,R.drawable.img07,R.drawable.img08,R.drawable.img09};

	private ImageSwitcher is;
	private int index = 0;
	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		
		is = (ImageSwitcher) findViewById(R.id.imageswitcher);
		is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		is.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		is.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView iv = new ImageView(MainActivity.this);
				iv.setScaleType(ScaleType.FIT_CENTER);
				//iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				return iv;
			}
		});
		is.setImageResource(imgId[index]);
	}
	
	public void buttonDown_onClick(View v){
		if(index>=1){
			index--;
		}
		else{
			index = imgId.length-1;
		}
		is.setImageResource(imgId[index]);
	}
	public void buttonUp_onClick(View v){
		if(index<imgId.length-1){
			index++;
		}
		else{
			index = 0;
		}
		is.setImageResource(imgId[index]);
	}
}
