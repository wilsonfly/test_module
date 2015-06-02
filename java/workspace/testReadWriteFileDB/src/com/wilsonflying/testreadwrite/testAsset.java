package com.wilsonflying.testreadwrite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class testAsset extends Activity {

	private static final String FILENAME = "myFile.txt";
	private static final String TAG = "testAsset";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_asset);
		final TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText("");

		findViewById(R.id.button_read).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						try {
							InputStream is = getResources().getAssets().open(
									FILENAME);
							InputStreamReader isr = new InputStreamReader(is, "UTF-8");
							BufferedReader br = new BufferedReader(isr);
							String buf;
							while ((buf = br.readLine()) != null) {
								Log.i(TAG, buf);
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