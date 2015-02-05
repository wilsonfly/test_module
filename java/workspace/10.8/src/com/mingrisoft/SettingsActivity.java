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
	//��ȡ�Ƿ񲥷ű������ֵ���ѡ���ֵ
	public static boolean getBgSound(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context)
		.getBoolean("bgsound",true);
	}
}