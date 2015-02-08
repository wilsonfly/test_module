package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	//创建选项菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(this);		//实例化一个MenuInflater对象
		inflater.inflate(R.menu.optionmenu, menu);		//解析菜单文件
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getGroupId()==R.id.setting){	//判断是否选择了参数设置菜单组
			if(item.isChecked()){				//当菜单项已经被选中
				item.setChecked(false);			//设置菜单项不被选中
			}else{
				item.setChecked(true);			//设置菜单项被选中
			}
		}
		if(item.getItemId()!=R.id.item2){
			//弹出消息提示框显示选择的菜单项的标题
			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
		}
		return true;
	}
	
}
