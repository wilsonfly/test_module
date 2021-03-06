package com.wilsonflying.testgridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	int[] imgId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12 };

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		String[] title = new String[] { "ͼƬ1", "ͼƬ2", "ͼƬ3", "ͼƬ4", "ͼƬ5",
				"ͼƬ6", "ͼƬ7", "ͼƬ8", "ͼƬ9", "ͼƬ10", "ͼƬ11", "ͼƬ12" };

		GridView gv = (GridView) findViewById(R.id.gridview);

		List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < imgId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", imgId[i]);
			map.put("title", title[i]);
			listitems.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, listitems,
				R.layout.items, new String[] { "title","image" }, new int[] {
						R.id.textview,R.id.imageview });
		gv.setAdapter(adapter);
	}
}
