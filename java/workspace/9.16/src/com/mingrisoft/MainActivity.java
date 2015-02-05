package com.mingrisoft;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private boolean flag=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout ll=(LinearLayout)findViewById(R.id.ll);	//获取布局文件中添加的线性布局管理器
        final AnimationDrawable anim=(AnimationDrawable)ll.getBackground();	//获取AnimationDrawable对象
        //为线性布局管理器添加单击事件监听器
        ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(flag){
					anim.start();	//开始播放动画
					flag=false;
				}else{
					anim.stop();	//停止播放动画
					flag=true;
				}
			}
		});
    }

}