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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		VideoView videoview = (VideoView) findViewById(R.id.videoview);
		Uri uri = Uri.parse("android.resource://com.wilsonflying.playVideoByUri/"+R.raw.mingrisoft);
		
		videoview.setVideoURI(uri);
		videoview.requestFocus();
		videoview.start();
		
		videoview.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "播放完毕", Toast.LENGTH_SHORT).show();
				/****************另外一种播放方式***************/
				Intent  intent = new Intent();
				Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Movies/mingrisoft.mp4");

				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(uri, "video/mp4");
				
				startActivity(intent);
				/****************另外一种播放方式***************/

			}
		});
	}
}