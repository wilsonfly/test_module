package com.wilsonflying.testreadwrite;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MyPreferenceActivity extends PreferenceActivity {
	
	PreferenceManager manager;
	CheckBoxPreference checkbox;
	ListPreference list;
	EditTextPreference editText;
	
//	private final String KEY_CHECKBOX = "checkbox_pre";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference_page);
		
		manager = getPreferenceManager();
		checkbox = (CheckBoxPreference) manager.findPreference(getResources().getString( R.string.key_checkbox));
		Toast.makeText(MyPreferenceActivity.this, "当前proxy状态:"+checkbox.isChecked(), Toast.LENGTH_SHORT).show();

		list = (ListPreference) manager.findPreference(getResources().getString(R.string.key_list));
		Toast.makeText(MyPreferenceActivity.this, "当前语言环境为:"+list.getEntry()+",将要进行："+list.getValue(), Toast.LENGTH_LONG).show();
		
		editText = (EditTextPreference) manager.findPreference(getResources().getString(R.string.key_edit));
		Toast.makeText(MyPreferenceActivity.this, "输入的editText内容:"+editText.getText(), Toast.LENGTH_LONG).show();
		
	}
}
