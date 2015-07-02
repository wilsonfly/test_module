package com.wilsonflying.testanimationlayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ShowListViewAty2 extends Activity {

	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview2);//使用layoutAnimation
		
		mListView = (ListView) findViewById(R.id.lv2);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		for (int i = 0; i < 20; i++) {
			adapter.add("内容"+i);
		}
		
		mListView.setAdapter(adapter);
	}
}
