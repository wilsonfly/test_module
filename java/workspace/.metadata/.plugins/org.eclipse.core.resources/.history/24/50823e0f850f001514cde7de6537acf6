package com.wilsonflying.testadapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	private ArrayAdapter<ListCellData> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<ListCellData>(MainActivity.this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        
        adapter.add(new ListCellData("测试ListView,显示文本", new Intent(MainActivity.this, Aty_listview.class)));
        adapter.add(new ListCellData("测试ListView,显示图片及文本", new Intent(MainActivity.this, Aty_listview_ext.class)));
        adapter.add(new ListCellData("测试ListActiviy,显示图片及文本", new Intent(MainActivity.this, Aty_listview_listActivity.class)));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	ListCellData data = adapter.getItem(position);
    	startActivity(data.getIntent());
    	
    	super.onListItemClick(l, v, position, id);
    }
}
