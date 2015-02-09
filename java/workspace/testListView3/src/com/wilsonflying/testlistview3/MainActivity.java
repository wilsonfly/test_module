package com.wilsonflying.testlistview3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//3.24 ���Բο�
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		int[] imageId = new int[]{R.drawable.img001_s,R.drawable.img002_s,R.drawable.img003_s};
		String[] str = new String[]{"item1", "item2", "item3"};
		
		ListView lv = (ListView) findViewById(R.id.listview);
		List<Map<String,Object>> listitems = new ArrayList<Map<String,Object>>();
		
		for(int i=0; i<imageId.length; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", imageId[i]);
			map.put("title", str[i]);
			listitems.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, listitems, R.layout.items, new String[]{"title","image"}, new int[]{R.id.textview,R.id.image});
		lv.setAdapter(adapter);
	}
}
