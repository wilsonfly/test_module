package com.wilsonflying.testListActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private ArrayAdapter<ListCellData> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(adapter);
		
		adapter = new ArrayAdapter<ListCellData>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		
		adapter.add(new ListCellData(this, "The template", new Intent(this,Aty_Template.class)));
		adapter.add(new ListCellData(this, "test AutoCompleteTextView", new Intent(this,Aty_testAutoCompleteTextView.class)));
		adapter.add(new ListCellData(this, "test RadioGroup", new Intent(this,Aty_testRadioGroup.class)));
		adapter.add(new ListCellData(this, "test Timer", new Intent(this,Aty_testTimer.class)));
		adapter.add(new ListCellData(this, "test linearLayout", new Intent(this,Aty_testComplexLinearLayout.class)));
		adapter.add(new ListCellData(this, "test linearLayout 2", new Intent(this,Aty_testComplexLinearLayout2.class)));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		ListCellData data = adapter.getItem(position);
		data.startActivity();
		
		super.onListItemClick(l, v, position, id);
	}
	
}
