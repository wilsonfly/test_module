package com.wilsonflying.testhttp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	private ArrayAdapter<ListCellData> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        
        adapter = new ArrayAdapter<ListCellData>(this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        
        adapter.add(new ListCellData("测试httpGet", new Intent(MainActivity.this, HttpGetAty.class)));
        adapter.add(new ListCellData("测试httpPost", new Intent(MainActivity.this, HttpPostAty.class)));
        adapter.add(new ListCellData("测试httpClient之Get", new Intent(MainActivity.this, HttpClientGetAty.class)));
        adapter.add(new ListCellData("测试httpClient之POst", new Intent(MainActivity.this, HttpClientPostAty.class)));
        adapter.add(new ListCellData("测试httpClient之POst2", new Intent(MainActivity.this, HttpClientPostAty2.class)));
        
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	
    	ListCellData data = adapter.getItem(position);
    	startActivity(data.getIntent());
    }

}
