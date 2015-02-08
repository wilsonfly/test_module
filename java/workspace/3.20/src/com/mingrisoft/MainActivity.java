package com.mingrisoft;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/****************创建用于为ListView指定列表项的适配器********************/
		String[] ctype=new String[]{"情景模式","主题模式","手机","程序管理"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,ctype);
	
		/***************************************************************************/		
		setListAdapter(adapter); //设置该窗口中显示的列表
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
				String result = l.getItemAtPosition(position).toString(); // 获取选择项的值
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}
	}
