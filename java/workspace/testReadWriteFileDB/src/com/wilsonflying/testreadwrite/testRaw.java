package com.wilsonflying.testreadwrite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class testRaw extends Activity {

	private static final String FILENAME = "myFile.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_raw);

		final TextView textView = (TextView) findViewById(R.id.textView);
		
		findViewById(R.id.button_read).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				InputStream in = getResources().openRawResource(R.raw.myfile);
				InputStreamReader isr = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(isr);
				String buf;
				try {
					while ((buf = br.readLine()) != null) {
						textView.append(buf);
						textView.append("\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
