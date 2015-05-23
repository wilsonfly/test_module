package com.wilsonflying.testreadwritefile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class testExternal extends Activity {

	private EditText edit;
	private TextView text;
	private static final String FILE_NAME = "myFile.txt";
	File sdcard = Environment.getExternalStorageDirectory();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_external);
		
		findViewById(R.id.button_read).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				File file = new File(sdcard+FILE_NAME);
				File file = new File(sdcard,FILE_NAME);
				if(!file.exists()){
					Toast.makeText(testExternal.this, "文件"+file.getName()+"不存在", Toast.LENGTH_SHORT).show();
					return;
				}
			}
		});
		
		findViewById(R.id.button_write).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
