package com.wilsonflying.testlistview3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//3.24 可以参考
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
			map.put("title", str[i]);
			map.put("image", imageId[i]);//put的顺序无所谓
			listitems.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, listitems, R.layout.items, new String[]{"image","title"}, new int[]{R.id.image,R.id.textview});
//		SimpleAdapter adapter = new SimpleAdapter(this, listitems, R.layout.items, new String[]{"title","image"}, new int[]{R.id.textview,R.id.image});//两个数组中内容顺序无所谓，对应就ok
//		SimpleAdapter adapter = new SimpleAdapter(this, listitems, R.layout.items, new String[]{"image","title"}, new int[]{R.id.textview,R.id.image});//两个数组中内容顺序不匹配，显示乱码
		lv.setAdapter(adapter);
	}
}
