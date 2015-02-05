package com.mingrisoft;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
	private VideoView video;	//声明VideoView对象
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        video=(VideoView) findViewById(R.id.video);	//获取VideoView组件
//      video.setVideoPath("/data/mp4/happy.mp4");	//播放系统上的文件
        File file=new File("/sdcard/bell.mp4");	//获取SD卡上要播放的文件
        MediaController mc=new MediaController(MainActivity.this);
        if(file.exists()){	//判断要播放的视频文件是否存在
        	video.setVideoPath(file.getAbsolutePath());	//指定要播放的视频
        	video.setMediaController(mc);	//设置VideoView与MediaController相关联
        	video.requestFocus();	//让VideoView获得焦点
        	try {
				video.start();	//开始播放视频
			} catch (Exception e) {
				e.printStackTrace();	//输出异常信息
			}
        	//为VideoView添加完成事件监听器
        	video.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					Toast.makeText(MainActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();	//弹出消息提示框显示播放完毕
				}
			});
        }else{
        	Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();	//弹出消息提示框提示文件不存在
        }
    }
    
}