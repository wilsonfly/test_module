package com.wilsonflying.playVideoByUri;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity{

	private VideoView videoview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		videoview = (VideoView) findViewById(R.id.videoview);
		Uri uri = Uri.parse("android.resource://com.wilsonflying.playVideoByUri/"+R.raw.mingrisoft);
		
		videoview.setVideoURI(uri);
		videoview.requestFocus();
		videoview.start();
		
		videoview.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), "播放完毕", Toast.LENGTH_SHORT).show();
				/****************重播***************/
//				videoview.start();
				/****************重播***************/
				
				/****************另外一种播放方式***************/
				Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Movies/mingrisoft.mp4");
				Intent  intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(uri, "video/mp4");
				
				try {
					startActivity(intent);
					
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, "ACTION_VIEW 重播失败", Toast.LENGTH_SHORT).show();
					// TODO: handle exception
				}

				System.out.println("sdcard path:"+Environment.getExternalStorageDirectory().getPath());
				System.out.println("sdcard path:"+Environment.getExternalStorageDirectory());
				/****************另外一种播放方式***************/
				

			}
		});
	}
}
