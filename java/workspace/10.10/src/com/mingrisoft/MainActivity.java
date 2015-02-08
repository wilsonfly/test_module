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
	private MediaPlayer mp;		//����MediaPlayer����
	private SurfaceView sv;	//����SurfaceView����
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mp=new MediaPlayer();		//ʵ����MediaPlayer����
        sv=(SurfaceView)findViewById(R.id.surfaceView1);	//��ȡ���ֹ���������ӵ�SurfaceView���
        Button play=(Button)findViewById(R.id.play);	//��ȡ�����š���ť
        final Button pause=(Button)findViewById(R.id.pause);	//��ȡ����ͣ/��������ť
        Button stop=(Button)findViewById(R.id.stop);		//��ȡ��ֹͣ����ť
        //Ϊ�����š���ť��ӵ����¼�������
        play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mp.reset();		//����MediaPlayer����
				try {
					mp.setDataSource("/sdcard/ccc.mp4");	//����Ҫ���ŵ���Ƶ
					mp.setDisplay(sv.getHolder());	//���ý���Ƶ���������SurfaceView
					mp.prepare();	//Ԥ������Ƶ
					mp.start();	//��ʼ����
					sv.setBackgroundResource(R.drawable.bg_playing);	//�ı�SurfaceView�ı���ͼƬ
					pause.setText("��ͣ");
					pause.setEnabled(true);	//���á���ͣ����ť����
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
        //Ϊ��ֹͣ����ť��ӵ����¼�������
        stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mp.isPlaying()){
					mp.stop();		//ֹͣ����
					sv.setBackgroundResource(R.drawable.bg_finish);	//�ı�SurfaceView�ı���ͼƬ
					pause.setEnabled(false);	//���á���ͣ����ť������
				}
				
			}
		});
        //Ϊ����ͣ����ť��ӵ����¼�������
        pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mp.isPlaying()){
					mp.pause();		//��ͣ��Ƶ�Ĳ���
					((Button)v).setText("����");
				}else{
					mp.start();		//������Ƶ�Ĳ���
					((Button)v).setText("��ͣ");
				}
				
			}
		});
        //ΪMediaPlayer�����������¼�������
        mp.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				sv.setBackgroundResource(R.drawable.bg_finish);	//�ı�SurfaceView�ı���ͼƬ
				Toast.makeText(MainActivity.this, "��Ƶ������ϣ�", Toast.LENGTH_SHORT).show();
			}
		});
		/**************************��������*****************************************/
		final AudioManager am = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);	//��ȡ��Ƶ������Ķ���
		//���õ�ǰ����������Сֻ�����ý������
		MainActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar1);					//��ȡ�϶���
		seekbar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));//�����϶��������ֵ
		int progress=am.getStreamVolume(AudioManager.STREAM_MUSIC);	//��ȡ��ǰ������
		seekbar.setProgress(progress);	//�����϶�����Ĭ��ֵΪ��ǰ����
		final TextView tv=(TextView)findViewById(R.id.volume);	//��ȡ��ʾ��ǰ������TextView���
		tv.setText("��ǰ������"+progress);						//��ʾ��ǰ����
		//Ϊ�϶���������OnSeekBarChangeListener������
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				tv.setText("��ǰ������"+progress);						//��ʾ�ı�������
				am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);	//���øı�������
			}
		});
		/***********************************************************************************/	
        
    }
	@Override
	protected void onDestroy() {
		if(mp.isPlaying()){
			mp.stop();	//ֹͣ������Ƶ
		}
		mp.release();	//�ͷ���Դ
		super.onDestroy();
	}
    
}