package com.wilsonflying.testadapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Aty_listview_listActivity extends ListActivity implements OnItemClickListener {

	private ListView_ext_adapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview_listactivity);

//		setListAdapter(new ListView_ext_adapter(this));
		adapter = new ListView_ext_adapter(this);
		setListAdapter(adapter);
		
		ListView lv = (ListView) findViewById(android.R.id.list);//留意这的id
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		cellData_listview_ext celldata = (cellData_listview_ext) adapter.getItem(position);
		Toast.makeText(Aty_listview_listActivity.this, celldata.getName()+" "+celldata.getDescription(), Toast.LENGTH_SHORT).show();
		
	}
}
