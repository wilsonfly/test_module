package com.wilsonflying.testreadwrite;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class testSharedPreference extends Activity {

	private EditText edit;
	private TextView text;
	private Button button_write,button_read;
	private SharedPreferences preference;
	private Editor editor;
	private final String KEY_NO1 = "key_no1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_external);
		
		edit = (EditText) findViewById(R.id.editText_ext);
		text = (TextView) findViewById(R.id.textView_ext);
		
		preference = getPreferences(Context.MODE_PRIVATE);
		editor = preference.edit();
		
		findViewById(R.id.button_write_ext).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if( "".equals(edit.getText().toString()) ){
					Toast.makeText(testSharedPreference.this, "得先输入点东西",Toast.LENGTH_SHORT).show();
					return;
				}
				editor.putString(KEY_NO1, edit.getText().toString());
				if (editor.commit()) {
					Toast.makeText(testSharedPreference.this, "保存成功",Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(testSharedPreference.this, "保存失败",Toast.LENGTH_SHORT).show();
				}
			}
		});
		findViewById(R.id.button_read_ext).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String buf = preference.getString(KEY_NO1, "没有读到信息,特此提示");
				text.setText(buf);
				
			}
		});
	}
}
