package com.wilsonflying.testreadwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class testExternalPicture extends Activity {

	private final String FILENAME = "ic_launcher.png";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_external_picture);
		
		findViewById(R.id.button_write_ext_pic).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File sdcard = Environment.getExternalStorageDirectory();
				File file_pic = new File(sdcard,FILENAME);
				
				try {
					InputStream is = getResources().getAssets().open(FILENAME);
					FileOutputStream fos = new FileOutputStream(new File(sdcard, FILENAME));
					int count = 0;
					byte[] buffer = new byte[512];
					
					while( (count = is.read(buffer)) >= 0){
						fos.write(buffer, 0, count);
					}
					
					is.close();
					fos.close();
					
					Toast.makeText(testExternalPicture.this, "写入"+new File(sdcard, FILENAME), Toast.LENGTH_SHORT).show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		findViewById(R.id.button_read_ext_pic).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String filename = Environment.getExternalStorageDirectory() + "/" + FILENAME;
				if(!new File(filename).exists()){
					Toast.makeText(testExternalPicture.this, filename+"尚不存在，请先写入", Toast.LENGTH_SHORT).show();
					return;
				}
				
				try {
					FileInputStream fis = new FileInputStream(new File(filename));
					Bitmap bitmap = BitmapFactory.decodeStream(fis);
					
					ImageView iv = (ImageView) findViewById(R.id.iv_ext_pic);
					iv.setImageBitmap(bitmap);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
