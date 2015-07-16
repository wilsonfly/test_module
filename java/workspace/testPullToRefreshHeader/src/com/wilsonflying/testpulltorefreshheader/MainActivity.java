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
        for (int i = 0; i < 30; i++) {
        	adapter.add("item "+i);
		}
     
        
        lv.setAdapter(adapter);
        
    }


}
