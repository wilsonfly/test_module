package com.wilsonflying.testmenu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		tv = (TextView) findViewById(R.id.text);
		registerForContextMenu(tv);
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.contextmenu, menu);
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.setHeaderTitle("选择选项改变文字颜色");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onContextItemSelected(item);
		switch(item.getItemId()){
		case R.id.color_chi:
			tv.setTextColor(Color.rgb(255, 0, 0));
			break;
		case R.id.color_cheng:
			tv.setTextColor(getResources().getColor(R.color.color_cheng));
			break;
		case R.id.color_huang:
			tv.setTextColor(getResources().getColor(R.color.color_huang));
			break;
		case R.id.color_lv:
			tv.setTextColor(getResources().getColor(R.color.color_lv));
			break;
		case R.id.color_default:
			tv.setTextColor(getResources().getColor(R.color.color_hei));
			break;		
		case R.id.color_qing:
				tv.setTextColor(getResources().getColor(R.color.color_qing));
				break;		
		case R.id.color_lan:
					tv.setTextColor(getResources().getColor(R.color.color_lan));
					break;
		}
		
		if(item.getGroupId() == R.id.other){
			Toast.makeText(MainActivity.this, "修改颜色为："+item.getTitle(), Toast.LENGTH_SHORT).show();
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.optionmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		if(item.getItemId() != R.id.item2){
			Toast.makeText(MainActivity.this, "选择菜单选项："+item.getTitle(), Toast.LENGTH_SHORT).show();
		}
		if(item.getGroupId() == R.id.setting){
			if(item.isChecked()){
				item.setChecked(false);
			}else{
				item.setChecked(true);
			}
		}
		
		
		return true;
	}
}
