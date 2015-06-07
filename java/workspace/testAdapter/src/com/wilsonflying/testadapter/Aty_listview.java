package com.wilsonflying.testadapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Aty_listview extends Activity implements OnItemClickListener {

	private ArrayAdapter<cellData_listview> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview);
		
		adapter = new ArrayAdapter<cellData_listview>(Aty_listview.this, android.R.layout.simple_list_item_1);

		adapter.add(new cellData_listview("李雷", "男", 20));
		adapter.add(new cellData_listview("韩梅梅", "女", 18));
		
		ListView lv = (ListView) findViewById(R.id.listview);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		cellData_listview data = adapter.getItem(position);
		Toast.makeText(Aty_listview.this, ""+data.getName()+" "+data.getSex()+" "+data.getAge(), Toast.LENGTH_SHORT).show();
	}
	
	
}
