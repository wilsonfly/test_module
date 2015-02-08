package com.mingrisoft;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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