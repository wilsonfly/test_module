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
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class testInternal extends Activity {

	private static String FILE_NAME = "myfile.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_external);

		final EditText edit = (EditText) findViewById(R.id.editText_ext);
		final TextView text = (TextView) findViewById(R.id.textView_ext);

		findViewById(R.id.button_write_ext).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String buf = edit.getText().toString();
						if ("".equals(buf)) {
							Toast.makeText(getApplicationContext(), "你得先输入点东西",
									Toast.LENGTH_SHORT).show();
							return;
						}

						try {
							FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);//专用写内部存储文件的接口
							OutputStreamWriter osw = new OutputStreamWriter(fos);
							osw.write(buf);
							osw.flush();
							osw.close();
							fos.close();
							
							Toast.makeText(getApplicationContext(), "保存成功",
									Toast.LENGTH_SHORT).show();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
					}
				});
		
		findViewById(R.id.button_read_ext).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File file = new File(FILE_NAME);
				
				try {
					FileInputStream fis = openFileInput(FILE_NAME);
					InputStreamReader isr = new InputStreamReader(fis);
					
					char[] data = new char[fis.available()];
					isr.read(data);
					
					String buf = new String(data);
					text.setText(buf);
					
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
