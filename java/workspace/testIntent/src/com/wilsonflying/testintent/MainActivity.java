package com.wilsonflying.testintent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
    protected void onCreate(Bundle param) {
        super.onCreate(param);
        setContentView(R.layout.my_layout);
        
        Button button = (Button) findViewById(R.id.button_home);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(intent.ACTION_MAIN);
				intent.addCategory(intent.CATEGORY_HOME);
				startActivity(intent);
			}
		});
        
        Button button_newactivity = (Button) findViewById(R.id.button_newactivity);
        button_newactivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(intent.ACTION_VIEW);
				startActivity(intent);
			}
		});
        
        Button button_thirdactivity = (Button) findViewById(R.id.button_thirdactivity);
        button_thirdactivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("my_action");//不需要指定完整的包名+类名即隐式intent。action可以自定义，不必包名+intent.action+类名
				startActivity(intent);
			}
		});
        
        Button button_webactivity = (Button) findViewById(R.id.button_webactivity);
        button_webactivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));
				startActivity(intent);
			}
		});
        
        Button button_forth = (Button) findViewById(R.id.button_forthactivity);
        button_forth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.wilsonflying.testintent.intent.action.ForthActivity");//不需要指定完整的包名+类名即隐式intent。
				startActivity(intent);
			}
		});
        
        Button button_fifth = (Button) findViewById(R.id.button_fifthactivity);
        button_fifth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//方法一：用context和目标类类名构造component
//				Intent intent = new Intent();
//				intent.setComponent(new ComponentName(MainActivity.this, ForthActivity.class));
				
				//方法二：直接在构造intent对象时候传入context和目标类类名
				Intent intent = new Intent(MainActivity.this, ForthActivity.class);
				startActivity(intent);//显示intent
			}
		});
	}
}
