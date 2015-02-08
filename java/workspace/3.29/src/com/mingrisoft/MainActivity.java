package com.mingrisoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView listview = (ListView) findViewById(R.id.listView1); // 获取列表视图
		int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
				R.drawable.img03, R.drawable.img04, R.drawable.img05,
				R.drawable.img06, R.drawable.img07, R.drawable.img08 }; // 定义并初始化保存图片id的数组
		String[] title = new String[] { "保密设置", "安全", "系统设置", "上网", "我的文档",
				"GPS导航", "我的音乐", "E-mail" }; // 定义并初始化保存列表项文字的数组
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
		// 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map); // 将map对象添加到List集合中
		}

		SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.items, new String[] { "title", "image" }, new int[] {
						R.id.title, R.id.image }); // 创建SimpleAdapter
		listview.setAdapter(adapter); // 将适配器与ListView关联

	}
}