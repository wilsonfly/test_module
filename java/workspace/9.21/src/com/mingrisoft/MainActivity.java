package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Animation flare=AnimationUtils.loadAnimation(this, R.anim.flare);	//获取“透明度变化”动画资源
        final ImageView iv=(ImageView)findViewById(R.id.imageView1);		//获取要应用动画效果的ImageView
        iv.setX(100);	//设置星星的X坐标的位置
        iv.setY(50);	//设置星星的Y坐标的位置
        iv.startAnimation(flare);	//应用动画效果
    }
}