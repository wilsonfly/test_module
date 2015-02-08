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
	private VideoView video;	//����VideoView����
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        video=(VideoView) findViewById(R.id.video);	//��ȡVideoView���
//      video.setVideoPath("/data/mp4/happy.mp4");	//����ϵͳ�ϵ��ļ�
        File file=new File("/sdcard/bell.mp4");	//��ȡSD����Ҫ���ŵ��ļ�
        MediaController mc=new MediaController(MainActivity.this);
        if(file.exists()){	//�ж�Ҫ���ŵ���Ƶ�ļ��Ƿ����
        	video.setVideoPath(file.getAbsolutePath());	//ָ��Ҫ���ŵ���Ƶ
        	video.setMediaController(mc);	//����VideoView��MediaController�����
        	video.requestFocus();	//��VideoView��ý���
        	try {
				video.start();	//��ʼ������Ƶ
			} catch (Exception e) {
				e.printStackTrace();	//����쳣��Ϣ
			}
        	//ΪVideoView�������¼�������
        	video.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					Toast.makeText(MainActivity.this, "��Ƶ������ϣ�", Toast.LENGTH_SHORT).show();	//������Ϣ��ʾ����ʾ�������
				}
			});
        }else{
        	Toast.makeText(this, "Ҫ���ŵ���Ƶ�ļ�������", Toast.LENGTH_SHORT).show();	//������Ϣ��ʾ����ʾ�ļ�������
        }
    }
    
}