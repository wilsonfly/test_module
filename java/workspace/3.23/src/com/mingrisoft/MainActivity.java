package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageButton imageButton=(ImageButton)findViewById(R.id.start);		//获取进入按钮
		//为按钮添加单击事件监听器
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(MainActivity.this, "进入游戏...", Toast.LENGTH_SHORT).show();	//显示消息提示框
			
			}
		});
	}
}