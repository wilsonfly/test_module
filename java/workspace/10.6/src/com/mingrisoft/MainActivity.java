package com.mingrisoft;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private MediaPlayer player; // MediaPlayer对象
	private boolean isPause = false; // 是否暂停
	private File file; // 要播放的音频文件
	private TextView hint; // 声明显示提示信息的文本框

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button1 = (Button) findViewById(R.id.button1); // 获取播放按钮
		final Button button2 = (Button) findViewById(R.id.button2); // 获取“暂停/继续”按钮
		final Button button3 = (Button) findViewById(R.id.button3); // 获取“停止”按钮
		hint = (TextView) findViewById(R.id.hint); // 获取用户显示提示信息的文本框		

		/**************************调整音量*****************************************/
		final AudioManager am = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);	//获取音频管理类的对象
		//设置当前调整音量大小只是针对媒体音乐
		MainActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar1);					//获取拖动条
		seekbar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));//设置拖动条的最大值
		int progress=am.getStreamVolume(AudioManager.STREAM_MUSIC);	//获取当前的音量
		seekbar.setProgress(progress);	//设置拖动条的默认值为当前音量
		final TextView tv=(TextView)findViewById(R.id.volume);	//获取显示当前音量的TextView组件
		tv.setText("当前音量："+progress);						//显示当前音量
		//为拖动条组件添加OnSeekBarChangeListener监听器
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				tv.setText("当前音量："+progress);						//显示改变后的音量
				am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);	//设置改变后的音量
			}
		});
		/***********************************************************************************/	
		

		file = new File("/sdcard/ninan.mp3"); // 获取要播放的文件
		if (file.exists()) { // 如果文件存在
			player = MediaPlayer
					.create(this, Uri.parse(file.getAbsolutePath())); // 创建MediaPlayer对象
		} else {
			hint.setText("要播放的音频文件不存在！");
			button1.setEnabled(false);
			return;
		}
		// 为MediaPlayer对象添加完成事件监听器
		player.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				play(); // 重新开始播放
			}
		});
		// 为“播放”按钮添加单击事件监听器
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				play();//开始播放音乐
				if (isPause) {
					button2.setText("暂停");
					isPause = false;		//设置暂停标记变量的值为false
				}
				button2.setEnabled(true); // “暂停/继续”按钮可用
				button3.setEnabled(true); // “停止”按钮可用
				button1.setEnabled(false); // “播放”按钮不可用
			}
		});
		// 为“暂停/继续”按钮添加单击事件监听器
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (player.isPlaying() && !isPause) {
					player.pause(); // 暂停播放;
					isPause = true;
					((Button) v).setText("继续");
					hint.setText("暂停播放音频...");
					button1.setEnabled(true); // “播放”按钮可用
				} else {
					player.start(); // 继续播放
					((Button) v).setText("暂停");
					hint.setText("继续播放音频...");
					isPause = false;
					button1.setEnabled(false); // “播放”按钮不可用
				}
			}
		});
		// 为“停止”按钮添加单击事件监听器
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				player.stop(); // 停止播放;
				hint.setText("停止播放音频...");
				button2.setEnabled(false); // “暂停/继续”按钮不可用
				button3.setEnabled(false); // “停止”按钮不可用
				button1.setEnabled(true); // “播放”按钮可用
			}
		});
	}

	// 播放音乐的方法
	private void play() {
		try {
			player.reset();
			player.setDataSource(file.getAbsolutePath()); // 重新设置要播放的音频
			player.prepare(); // 预加载音频
			player.start(); // 开始播放
			hint.setText("正在播放音频...");
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}

	@Override
	protected void onDestroy() {
		if(player.isPlaying()){
			player.stop();	//停止音频的播放
		}
		player.release();	//释放资源
		super.onDestroy();
	}
	
}