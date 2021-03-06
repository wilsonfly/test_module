package com.wilsonflying.playaudio;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MediaPlayer player;
	private boolean isPause = false;
	private File file;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		textView = (TextView) findViewById(R.id.text);
		final Button button_play = (Button) findViewById(R.id.button_play);
		final Button button_pause = (Button) findViewById(R.id.button_pause);
		final Button button_stop = (Button) findViewById(R.id.button_stop);
		
		File sdcard = Environment.getExternalStorageDirectory();
		String filePath = sdcard.getAbsolutePath()+"/Music" +"/butterfly.mp3";
		Log.d("shs", "path:"+filePath);
		
//		file = new File("/sdcard/tmp.mp3");
		file = new File(filePath);
		if(file.exists()){
			player = MediaPlayer.create(getApplicationContext(), Uri.parse(file.getAbsolutePath()));
		}else {
			textView.setText("文件"+filePath+"不存在，无法播放");
			button_play.setEnabled(false);
			return;
		}
		
		player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				play();
				Toast.makeText(getApplicationContext(), "播放完一次，重新开始播放", Toast.LENGTH_SHORT).show();
			}
		});
		
		button_play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				play();
				
				if(isPause){
					isPause = false;
					button_pause.setText("暂停");
				}
				
				button_play.setEnabled(false);
				button_pause.setEnabled(true);
				button_stop.setEnabled(true);
			}
		});
		
		button_pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(player.isPlaying() && !isPause){
					player.pause();
					isPause = true;
					button_pause.setText("继续");
					button_play.setEnabled(true);
					textView.setText("暂停播放中。。。");
				}else{
					player.start();
					isPause = false;
					button_pause.setText("暂停");
					button_play.setEnabled(false);
					textView.setText("继续播放中。。。");
				}
			}
		});
		
		button_stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				player.stop();
				textView.setText("已停止播放");
				
				button_play.setEnabled(true);
				button_stop.setEnabled(false);
				button_pause.setEnabled(false);
			}
		});
	}
	
	private void play(){
		player.reset();
		try {
			player.setDataSource(file.getAbsolutePath());
			player.prepare();
			player.start();
			textView.setText("正在播放。。。");
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
