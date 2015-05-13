package com.mingrisoft;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting);
	}
	//获取是否播放背景音乐的首选项的值
	public static boolean getBgSound(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context)
		.getBoolean("bgsound",true);
	}
}