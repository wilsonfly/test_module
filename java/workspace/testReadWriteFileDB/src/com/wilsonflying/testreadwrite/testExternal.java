package com.wilsonflying.testreadwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_external);

		final File sdcard = Environment.getExternalStorageDirectory();
		final File file = new File(sdcard,FILE_NAME);
		
		final EditText edit = (EditText) findViewById(R.id.editText_ext);
		final TextView text = (TextView) findViewById(R.id.textView_ext);
		
		findViewById(R.id.button_read_ext).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				File file = new File(sdcard+FILE_NAME);
				if(!file.exists()){
					Toast.makeText(testExternal.this, "文件"+file.getAbsolutePath()+"不存在", Toast.LENGTH_SHORT).show();
					return;
				}else{
					try {
						FileInputStream fis = new FileInputStream(file);
						InputStreamReader is = new InputStreamReader(fis, "UTF-8");
						
						char[] buf = new char[fis.available()];
						is.read(buf);
						
						String output = new String(buf);
						text.setText(output);
						
						is.close();
						fis.close();

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		findViewById(R.id.button_write_ext).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if(!sdcard.exists()){
						Toast.makeText(getApplicationContext(), "sdcard不存在", Toast.LENGTH_SHORT).show();
						return;
					}
					if(!file.exists()){
						file.createNewFile();
					}
					FileOutputStream fo = new FileOutputStream(file);
					OutputStreamWriter osw = new OutputStreamWriter(fo, "UTF-8");
					
					String buf_write = edit.getText().toString();
					if("".equals(buf_write)){
						Toast.makeText(getApplicationContext(), "你得先输入点东西", Toast.LENGTH_SHORT).show();
						return;
					}
					osw.write(buf_write);
					osw.flush();
					osw.close();
					fo.close();
					
					Toast.makeText(getApplicationContext(), "写入成功", Toast.LENGTH_SHORT).show();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
