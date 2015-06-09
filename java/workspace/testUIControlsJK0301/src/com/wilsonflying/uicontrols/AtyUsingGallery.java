package com.wilsonflying.uicontrols;

import cn.eoe.uicontrols.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Gallery;

public class AtyUsingGallery extends Activity {

	private Gallery gallery;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_gallery);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		for (int i = 0; i < 20; i++) {
			adapter.add("Item "+i);
		}
		
		
		gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(adapter);
	}
}
