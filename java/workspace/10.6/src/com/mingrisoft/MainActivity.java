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
	private MediaPlayer player; // MediaPlayer����
	private boolean isPause = false; // �Ƿ���ͣ
	private File file; // Ҫ���ŵ���Ƶ�ļ�
	private TextView hint; // ������ʾ��ʾ��Ϣ���ı���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button1 = (Button) findViewById(R.id.button1); // ��ȡ���Ű�ť
		final Button button2 = (Button) findViewById(R.id.button2); // ��ȡ����ͣ/��������ť
		final Button button3 = (Button) findViewById(R.id.button3); // ��ȡ��ֹͣ����ť
		hint = (TextView) findViewById(R.id.hint); // ��ȡ�û���ʾ��ʾ��Ϣ���ı���		

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
		//Ϊ�϶����������OnSeekBarChangeListener������
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
		

		file = new File("/sdcard/ninan.mp3"); // ��ȡҪ���ŵ��ļ�
		if (file.exists()) { // ����ļ�����
			player = MediaPlayer
					.create(this, Uri.parse(file.getAbsolutePath())); // ����MediaPlayer����
		} else {
			hint.setText("Ҫ���ŵ���Ƶ�ļ������ڣ�");
			button1.setEnabled(false);
			return;
		}
		// ΪMediaPlayer������������¼�������
		player.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				play(); // ���¿�ʼ����
			}
		});
		// Ϊ�����š���ť���ӵ����¼�������
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				play();//��ʼ��������
				if (isPause) {
					button2.setText("��ͣ");
					isPause = false;		//������ͣ��Ǳ�����ֵΪfalse
				}
				button2.setEnabled(true); // ����ͣ/��������ť����
				button3.setEnabled(true); // ��ֹͣ����ť����
				button1.setEnabled(false); // �����š���ť������
			}
		});
		// Ϊ����ͣ/��������ť���ӵ����¼�������
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (player.isPlaying() && !isPause) {
					player.pause(); // ��ͣ����;
					isPause = true;
					((Button) v).setText("����");
					hint.setText("��ͣ������Ƶ...");
					button1.setEnabled(true); // �����š���ť����
				} else {
					player.start(); // ��������
					((Button) v).setText("��ͣ");
					hint.setText("����������Ƶ...");
					isPause = false;
					button1.setEnabled(false); // �����š���ť������
				}
			}
		});
		// Ϊ��ֹͣ����ť���ӵ����¼�������
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				player.stop(); // ֹͣ����;
				hint.setText("ֹͣ������Ƶ...");
				button2.setEnabled(false); // ����ͣ/��������ť������
				button3.setEnabled(false); // ��ֹͣ����ť������
				button1.setEnabled(true); // �����š���ť����
			}
		});
	}

	// �������ֵķ���
	private void play() {
		try {
			player.reset();
			player.setDataSource(file.getAbsolutePath()); // ��������Ҫ���ŵ���Ƶ
			player.prepare(); // Ԥ������Ƶ
			player.start(); // ��ʼ����
			hint.setText("���ڲ�����Ƶ...");
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}

	@Override
	protected void onDestroy() {
		if(player.isPlaying()){
			player.stop();	//ֹͣ��Ƶ�Ĳ���
		}
		player.release();	//�ͷ���Դ
		super.onDestroy();
	}
	
}