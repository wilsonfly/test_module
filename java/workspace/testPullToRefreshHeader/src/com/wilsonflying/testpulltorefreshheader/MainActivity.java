package com.wilsonflying.testpulltorefreshheader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

//	private ListView lv;
	private RefreshListView lv;
	private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv = (RefreshListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("hello 001");
        adapter.add("hello 002");
        adapter.add("hello 003");
        adapter.add("hello 004");
        
        lv.setAdapter(adapter);
        
    }


}
