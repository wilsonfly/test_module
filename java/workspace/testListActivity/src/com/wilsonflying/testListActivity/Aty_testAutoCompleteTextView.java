package com.wilsonflying.testListActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Aty_testAutoCompleteTextView extends Activity {

	private AutoCompleteTextView actv;
	private ArrayAdapter<String> adapter;
	private String[] strs = new String[]{
			"hello",
			"hello world",
			"hello android",
			"hello ios",
			"java",
			"java nimei",
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_autocomplete_textview);

		actv = (AutoCompleteTextView) findViewById(R.id.autocomplete_textview);

		//方法一：通过adapter.add 方法添加匹配内容
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
//		adapter.add("hello");
//		adapter.add("hello world");
//		adapter.add("hello android");
//		adapter.add("hello ios");
//		adapter.add("java");
//		adapter.add("java nimei");

		//方法二：以数组形式传到adapter构造函数中
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, strs);
		
		actv.setAdapter(adapter);
		
	}
}
