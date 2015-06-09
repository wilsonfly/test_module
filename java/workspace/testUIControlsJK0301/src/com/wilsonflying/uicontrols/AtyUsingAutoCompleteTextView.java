package com.wilsonflying.uicontrols;

import cn.eoe.uicontrols.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AtyUsingAutoCompleteTextView extends Activity {


	private AutoCompleteTextView actv;
	private MultiAutoCompleteTextView mactv;
	private ArrayAdapter<String> actvAdapter,mactvAdapter;
	private String[] strs = new String[]{
			"hello",
			"hello eoe",
			"hello eoe.cn",
			"hello eoeAndroid",
			"hello eoe iOS",
			"java",
			"javascript",
			"php",
			"python"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_autocompletetextview);

		actvAdapter = new ArrayAdapter<String>(this, R.layout.aty_using_autocompletetextview_dropdown_item,strs);

		actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
		actv.setAdapter(actvAdapter);

		mactv = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
		mactvAdapter = new ArrayAdapter<String>(this, R.layout.aty_using_autocompletetextview_dropdown_item,strs);
		mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		mactv.setAdapter(mactvAdapter);
	}
}
