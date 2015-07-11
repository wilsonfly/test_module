package com.wilsonflying.testanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        
    }

    public void onClickBtn(View view){
    
//    	view.startAnimation(new MyAnimation());
    	
    	MyAnimationTrans myAnim = new MyAnimationTrans();
    	myAnim.setDuration(300);
    	view.startAnimation(myAnim);
    }
}
