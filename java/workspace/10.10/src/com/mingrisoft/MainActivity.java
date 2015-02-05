package com.mingrisoft;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private MediaPlayer mp;		//声明MediaPlayer对象
	private SurfaceView sv;	//声明SurfaceView对象
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mp=new MediaPlayer();		//实例化MediaPlayer对象
        sv=(SurfaceView)findViewById(R.id.surfaceView1);	//获取布局管理器中添加的SurfaceView组件
        Button play=(Button)findViewById(R.id.play);	//获取“播放”按钮
        final Button pause=(Button)findViewById(R.id.pause);	//获取“暂停/继续”按钮
        Button stop=(Button)findViewById(R.id.stop);		//获取“停止”按钮
        //为“播放”按钮添加单击事件监听器
        play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mp.reset();		//重置MediaPlayer对象
				try {
					mp.setDataSource("/sdcard/ccc.mp4");	//设置要播放的视频
					mp.setDisplay(sv.getHolder());	//设置将视频画面输出到SurfaceView
					mp.prepare();	//预加载视频
					mp.start();	//开始播放
					sv.setBackgroundResource(R.drawable.bg_playing);	//改变SurfaceView的背景图片
					pause.setText("暂停");
					pause.setEnabled(true);	//设置“暂停”按钮可用
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
        //为“停止”按钮添加单击事件监听器
        stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mp.isPlaying()){
					mp.stop();		//停止播放
					sv.setBackgroundResource(R.drawable.bg_finish);	//改变SurfaceView的背景图片
					pause.setEnabled(false);	//设置“暂停”按钮不可用
				}
				
			}
		});
        //为“暂停”按钮添加单击事件监听器
        pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mp.isPlaying()){
					mp.pause();		//暂停视频的播放
					((Button)v).setText("继续");
				}else{
					mp.start();		//继续视频的播放
					((Button)v).setText("暂停");
				}
				
			}
		});
        //为MediaPlayer对象添加完成事件监听器
        mp.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				sv.setBackgroundResource(R.drawable.bg_finish);	//改变SurfaceView的背景图片
				Toast.makeText(MainActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
			}
		});
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
        
    }
	@Override
	protected void onDestroy() {
		if(mp.isPlaying()){
			mp.stop();	//停止播放视频
		}
		mp.release();	//释放资源
		super.onDestroy();
	}
    
}