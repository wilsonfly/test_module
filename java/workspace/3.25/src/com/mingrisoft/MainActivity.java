package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageButton imageButton=(ImageButton)findViewById(R.id.start);		//获取进入按钮
		CheckBox checkbox=(CheckBox)findViewById(R.id.checkBox1);	//获取布局文件中添加的复选按钮
		//为复选按钮添加监听器
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){			//当复选按钮被选中
					imageButton.setVisibility(View.VISIBLE);		//设置进入按钮显示
				}else{
					imageButton.setVisibility(View.INVISIBLE);		//设置进入按钮不显示
				}
				imageButton.invalidate();	//重绘ImageButton				
			}
		});
		//为进入按钮添加单击事件监听器
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(MainActivity.this, "进入游戏...", Toast.LENGTH_SHORT).show();	//显示消息提示框
			
			}
		});
	}
}