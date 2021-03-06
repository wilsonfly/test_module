package com.wilsonflying.frameAnimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private boolean notPlaying = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);
		final AnimationDrawable anim = (AnimationDrawable) ll.getBackground();
		
		ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(notPlaying){
					anim.start();
					notPlaying = false;
				}else{
					anim.stop();
					notPlaying = true;
				}
			}
		});
	}
}
