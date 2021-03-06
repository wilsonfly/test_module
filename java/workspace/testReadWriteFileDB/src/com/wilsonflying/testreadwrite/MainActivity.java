package com.wilsonflying.testreadwrite;

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
//		setContentView(R.layout.activity_main);
		
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);		
		
		adapter.add(new ListCellData(this, "Read Asset", new Intent(this, testAsset.class)));
		adapter.add(new ListCellData(this, "Read Raw", new Intent(this, testRaw.class)));
		adapter.add(new ListCellData(this, "ReadWrite Internal", new Intent(this, testInternal.class)));
		adapter.add(new ListCellData(this, "ReadWrite External", new Intent(this, testExternal.class)));
		adapter.add(new ListCellData(this, "ReadWrite Picture in External", new Intent(this, testExternalPicture.class)));
		adapter.add(new ListCellData(this, "ReadWrite sharedPreference", new Intent(this, testSharedPreference.class)));
		adapter.add(new ListCellData(this, "ReadWrite DB", new Intent(this, testDB.class)));
		adapter.add(new ListCellData(this, "test PreferenceActivity", new Intent(this, testSharedPreferenceActivity.class)));
	
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		ListCellData data = adapter.getItem(position);
		startActivity(data.getTargetIntent());

		super.onListItemClick(l, v, position, id);
	}
	
}
