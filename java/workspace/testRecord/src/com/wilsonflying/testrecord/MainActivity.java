package com.wilsonflying.testrecord;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private OnClickListener btnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.btnStartRecord:
				startRecord();
				
				break;
			case R.id.btnStopRecord:
				stopRecord();
				break;
			}
			
		}
	};
	
	
	private void startRecord(){
		if (mp==null) {
			
			File dir = new File(Environment.getExternalStorageDirectory(), "Sounds");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			File soundFile = new File(dir, System.currentTimeMillis()+".amr");
			if (!soundFile.exists()) {
				try {
					soundFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			mp = new MediaRecorder();
			mp.setAudioSource(MediaRecorder.AudioSource.MIC);
			mp.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);
			mp.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
			mp.setOutputFile(soundFile.getAbsolutePath());
			try {
				mp.prepare();
				mp.start();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void stopRecord(){
		if (mp!=null) {
			
			mp.stop();
			mp.release();
			mp = null;
		}
	}
	
	
	private MediaRecorder mp=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnStartRecord).setOnClickListener(btnClickListener);
		findViewById(R.id.btnStopRecord).setOnClickListener(btnClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
