package com.mingrisoft;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv=(TextView)findViewById(R.id.show);
		registerForContextMenu(tv);		//为文本框注册上下文菜单

	}
	//创建上下文菜单
	/************************************************************/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflator=new MenuInflater(this); 	//实例化一个MenuInflater对象
		inflator.inflate(R.menu.contextmenu, menu); 	//解析菜单文件
		menu.setHeaderIcon(R.drawable.ic_launcher);		//为菜单头设置图标
		menu.setHeaderTitle("请选择文字颜色：");			//为菜单头设置标题

	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getGroupId()==R.id.other){			//判断是否选择了参数设置菜单组
			if(item.getItemId()==R.id.other1){					//当菜单项已经被选中
				tv.setTextColor(Color.rgb(118, 146, 60));
			}else if(item.getItemId()==R.id.other2){
				tv.setTextColor(Color.rgb(0, 255, 255));
			}
		}else{
			switch(item.getItemId()){
				case R.id.color1:		//当选择红颜色时
					tv.setTextColor(Color.rgb(255, 0, 0));
					break;
				case R.id.color2:		//当选择绿颜色时
					tv.setTextColor(Color.rgb(0, 255, 0));
					break;
				case R.id.color3:		//当选择蓝颜色时
					tv.setTextColor(Color.rgb(0, 0, 255));
					break;
				case R.id.color4:		//当选择橙色时
					tv.setTextColor(Color.rgb(255, 180, 0));
					break;
				default:
					tv.setTextColor(Color.rgb(255, 255, 255));
			}
		}
		return true;
	}
	
}
