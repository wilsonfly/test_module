package com.wilsonflying.testadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Aty_listview_Multi extends Activity implements OnItemClickListener {

	private ListView_ext_adapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview_multi);
		
		ListView lv = (ListView) findViewById(R.id.listview_ext);
//		lv.setAdapter(new ListView_ext_adapter(Aty_listview_ext.this));
		adapter = new ListView_ext_adapter(Aty_listview_Multi.this);
		lv.setAdapter(adapter);
		
//		lv.setOnItemClickListener(doHandleClick());
		lv.setOnItemClickListener(this);
	}

	private OnItemClickListener doHandleClick() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		cellData_listview_ext celldata = (cellData_listview_ext) adapter.getItem(position);
		Toast.makeText(Aty_listview_Multi.this, celldata.getName()+" "+celldata.getDescription(), Toast.LENGTH_SHORT).show();
		
	}
}