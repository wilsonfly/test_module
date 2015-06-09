package com.wilsonflying.uicontrols;

import cn.eoe.uicontrols.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AtyUsingSpinner extends Activity {

	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_spinner);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		adapter.add("Hello");
		adapter.add("eoe");
		adapter.add("eoe.cn");
		
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter);
	}
}
