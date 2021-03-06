package com.wilsonflying.playvideo;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

	private SurfaceView surface;
	private MediaPlayer player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		player = new MediaPlayer();
		surface =(SurfaceView) findViewById(R.id.surfaceView);
		final Button button_play = (Button) findViewById(R.id.button_play);
		final Button button_pause = (Button) findViewById(R.id.button_pause);
		Button button_stop = (Button) findViewById(R.id.button_stop);

		final String filePath = Environment.getExternalStorageDirectory()+"/Movies/mingrisoft.mp4";
		
		button_play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				player.reset();
				try {
					player.setDataSource(filePath);
					player.setDisplay(surface.getHolder());
					player.prepare();
					player.start();
//					surface.setBackgroundResource(R.drawable.bg_playing);//会遮挡视频画面
					
					button_pause.setEnabled(true);
					button_pause.setText("暂停");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		button_pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(player.isPlaying()){
					player.pause();
					button_pause.setText("继续");
				}else{
					player.start();
					button_pause.setText("暂停");
				}
			}
		});
		
		button_stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(player.isPlaying()){
					player.stop();
					button_pause.setEnabled(false);
				}
			}
		});
		
		player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
//				surface.setBackgroundResource(R.drawable.mpbackground1);//TODO:下次播放事后，该backgroud会遮挡画面，待查解决办法
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(player.isPlaying()){
			player.stop();
		}
		player.release();
		super.onDestroy();
	}
}
