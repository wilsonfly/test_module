package com.wilsonflying.playvideovidioview2;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private VideoView videoView;
	private File file;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		videoView = (VideoView) findViewById(R.id.video);
		
		String filePath = Environment.getExternalStorageDirectory()+"/Movies/mingrisoft.mp4";
		file = new File(filePath);
		
		MediaController mc = new MediaController(this);
		
		if(file.exists()){
			videoView.setVideoPath(file.getAbsolutePath());
			videoView.setMediaController(mc);
			videoView.requestFocus();
			videoView.start();
		}else{
			Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();
		}
		
		videoView.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
