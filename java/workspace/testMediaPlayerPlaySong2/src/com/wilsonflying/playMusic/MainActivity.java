package com.wilsonflying.playMusic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MediaPlayer mediaPlayer;
	private List<String> audioList = new ArrayList<String>();
	private int currentIndex = 0;
	private final String EXTERNAL_PATH = Environment.getExternalStorageDirectory().toString();
	private String[] audioFmtSet = new String[]{".mp3", ".mav", ".3gp"};//不严谨的，起码需要判断后缀
	Button button_pause;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		button_pause = (Button) findViewById(R.id.button_pause);
		

		/*******************调节音量部分******************/
		final TextView textVolume = (TextView) findViewById(R.id.test_volume);
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);

		MainActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//设置当前调节的音量只针对媒体音乐
		final AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		seekBar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		seekBar.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
		textVolume.setText("当前音量："+am.getStreamVolume(AudioManager.STREAM_MUSIC));
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);
				textVolume.setText("当前音量："+progress);
			}
		});
		/*******************调节音量部分******************/

		mediaPlayer = new MediaPlayer();
		
		listAudio();//search audio files and add to listview
		
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				playMusic(audioList.get(currentIndex));
			}
		});
	}
	
	public void onClickBtn(View view){
		switch (view.getId()) {
		case R.id.button_pre:
			if(--currentIndex < 0){
				currentIndex = audioList.size()-1;
			}
			playMusic(audioList.get(currentIndex));
			break;
		case R.id.button_play:
			playMusic(audioList.get(currentIndex));
			break;
		case R.id.button_pause:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();
				button_pause.setText("继续");
			}else{
//				playMusic(audioList.get(currentIndex));
				mediaPlayer.start();
				button_pause.setText("暂停");
			}
			break;
		case R.id.button_stop:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
				
				button_pause.setEnabled(false);
			}
			break;
		case R.id.button_next:
			if(++currentIndex >= audioList.size()){
				currentIndex = 0;
			}
			playMusic(audioList.get(currentIndex));
			break;

		default:
			break;
		}
	}
	
	private void listAudio(){
		
		getFiles(EXTERNAL_PATH);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, audioList);
		ListView listview = (ListView) findViewById(R.id.list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currentIndex = position;
				playMusic(audioList.get(currentIndex));
			}
		
		});
	}
	
	private void getFiles(String path){
		File file = new File(path);
		File[] files = file.listFiles();
		
		if(files == null){
			Log.i("huasheng", "Find null dir:"+file);
			return;
		}
//		Log.i("huasheng", "Enter dir:"+file);

		for(File f:files){
			if(f.isDirectory()){
				getFiles(f.getAbsolutePath());
			}else{
				if(isAudioFile(f.getPath())){
					audioList.add(f.getPath());
				}
			}
		}
		
	}
	
	private boolean isAudioFile(String fileName){
		for(String fmt:audioFmtSet){
			if(fileName.endsWith(fmt)){
				return true;
			}
		}
		return false;
	}
	
	private void playMusic(String path){
		Log.i("huasheng", "path:"+path);
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		mediaPlayer.reset();
		try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			mediaPlayer.start();
			
			button_pause.setText("暂停");
			button_pause.setEnabled(true);
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
}
