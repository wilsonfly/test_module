package com.wilsonflying.testlistview2;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {//�̳�ListActivity!!!

	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		//setContentView();//û��layout��
		String[] theItem = new String[]{"ddf","fffffff","�ط�","��¥��˵��"};
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, theItem);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, theItem);

		setListAdapter(adapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String result = l.getItemAtPosition(position).toString();
		Toast.makeText(MainActivity.this, "ѡ���ˣ�"+result, Toast.LENGTH_SHORT).show();
	}
}
