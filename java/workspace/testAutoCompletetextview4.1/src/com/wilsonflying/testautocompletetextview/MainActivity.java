package com.wilsonflying.testautocompletetextview;

import com.wilsonflying.testautocompletetextview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.Tokenizer;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String[] COUNTRIES = new String[] { "auto",
			"autocomplete", "autocompletetext", "autocompletetextview",
			"autocompletexxxx" };
	private AutoCompleteTextView textView;
	private MultiAutoCompleteTextView macTV;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*
		 * 1.初始化控件 
		 * 2.创建适配器 
		 * 3.准备数据源 
		 * 4.将textview跟adapter绑定
		 */
		textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		textView.setAdapter(adapter);
		
		macTV = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
		macTV.setAdapter(adapter);
		macTV.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}