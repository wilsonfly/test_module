package com.wilsonflying.testlistview;

import com.wisonflying.testlistview.R;

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

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

		//方法一：
		String[] theStringArray=new String[]{"item1","item2","item3","item4"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,theStringArray);
		//方法二：
		/*
		  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		 
				this, R.array.theStringArray,
				android.R.layout.simple_list_item_checked);
		*/

		final ListView listView = (ListView) findViewById(R.id.listView1);
		listView.addHeaderView(line()); // 设置header view
		listView.setAdapter(adapter);
		listView.addFooterView(line());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int pos,
					long id) {
				String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT)
						.show();
			}

		});

	}

	private View line() {
		ImageView image = new ImageView(this); // 创建一个图像视图
		image.setImageResource(R.drawable.line1); // 设置要显示的图片
		return image;
	}
}
