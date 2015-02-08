package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ListView listView=(ListView)findViewById(R.id.listView1);
		listView.addHeaderView(line());		//设置header view

		/****************创建用于为ListView指定列表项的适配器********************/
//		方法一
//		String[] ctype=new String[]{"情景模式","主题模式","手机","程序管理"};
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,ctype);
//		方法二
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.ctype,android.R.layout.simple_list_item_checked);	//创建一个适配器
		
		/***************************************************************************/		
		listView.setAdapter(adapter); // 将适配器与ListView关联
		listView.addFooterView(line());		//设置footer view
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int pos,
					long id) {
				String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}

		});
	}

	private View line() {
		ImageView image=new ImageView(this);	//创建一个图像视图
		image.setImageResource(R.drawable.line1);	//设置要显示的图片
		return image;
	}
}