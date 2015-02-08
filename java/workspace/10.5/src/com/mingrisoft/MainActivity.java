package com.mingrisoft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private MediaPlayer mediaPlayer; // ����MediaPlayer����
	private List<String> audioList = new ArrayList<String>(); // Ҫ���ŵ���Ƶ�б�
	private int currentItem = 0; // ��ǰ���Ÿ���������
	private Button pause; // ����һ������ͣ����ť����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mediaPlayer = new MediaPlayer(); // ʵ����һ��MediaPlayer����
		Button play = (Button) findViewById(R.id.play); // ��ȡ�����š���ť
		Button stop = (Button) findViewById(R.id.stop); // ��ȡ��ֹͣ����ť
		pause = (Button) findViewById(R.id.pause); // ��ȡ����ͣ/��������ť
		Button pre = (Button) findViewById(R.id.pre); // ��ȡ����һ�ס���ť
		Button next = (Button) findViewById(R.id.next); // ��ȡ����һ�ס���ť
		audioList(); // ʹ��ListView�����ʾSD���ϵ�ȫ����Ƶ�ļ�
		// ΪMediaPlayer�������¼�������
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				nextMusic(); // ������һ��
			}
		});
		// ֹͣ
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.stop(); // ֹͣ������Ƶ
				}
				pause.setEnabled(false); // ���á���ͣ����ť������
			}
		});
		// Ϊ�����š���ť��ӵ����¼�������
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				playMusic(audioList.get(currentItem)); // ����playMusic()������������

			}
		});
		// Ϊ����ͣ����ť��ӵ����¼�������
		pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.pause(); // ��ͣ��Ƶ�Ĳ���
					((Button) v).setText("����");
				} else {
					mediaPlayer.start();	//��������
					((Button) v).setText("��ͣ");
				}
			}
		});
		// Ϊ����һ�ס���ť��ӵ����¼�������
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextMusic(); // ������һ��
			}
		});
		// Ϊ����һ�ס���ť��ӵ����¼�������
		pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				preMusic(); // ������һ��
			}
		});
	}

	// ʹ��ListView�����ʾSD���ϵ�ȫ����Ƶ�ļ�
	private void audioList() {
		getFiles("/sdcard/"); // ��ȡSD���ϵ�ȫ����Ƶ�ļ�
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, audioList); // ����һ��������
		ListView listview = (ListView) findViewById(R.id.list); // ��ȡ���ֹ���������ӵ�ListView���
		listview.setAdapter(adapter); // ����������ListView����
		// �������б���ʱ��������
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				currentItem = position; // ����ǰ�б��������ֵ��ֵ��currentItem
				playMusic(audioList.get(currentItem)); // ����playMusic()������������
			}
		});
	}

	private void getFiles(String url) {
		File files = new File(url); // �����ļ�����
		File[] file = files.listFiles();
		try {
			for (File f : file) { // ͨ��forѭ��������ȡ�����ļ�����
				if (f.isDirectory()) { // �����Ŀ¼��Ҳ�����ļ���
					getFiles(f.getAbsolutePath()); // �ݹ����
				} else {
					if (isAudioFile(f.getPath())) { // �������Ƶ�ļ�
						audioList.add(f.getPath()); // ���ļ���·����ӵ�list������
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}

	private static String[] imageFormatSet = new String[] { "mp3", "wav", "3gp" }; // �Ϸ�����Ƶ�ļ���ʽ

	// �ж��Ƿ�Ϊ��Ƶ�ļ�
	private static boolean isAudioFile(String path) {
		for (String format : imageFormatSet) { // ��������
			if (path.contains(format)) { // �ж��Ƿ�Ϊ�кϷ�����Ƶ�ļ�
				return true;
			}
		}
		return false;
	}

	// ��������
	void playMusic(String path) {
		try {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop(); // ֹͣ��ǰ��Ƶ�Ĳ���
			}
			mediaPlayer.reset(); // ����MediaPlayer
			mediaPlayer.setDataSource(path); // ָ��Ҫ���ŵ���Ƶ�ļ�
			mediaPlayer.prepare(); // Ԥ������Ƶ�ļ�
			mediaPlayer.start(); // ������Ƶ
			pause.setText("��ͣ");
			pause.setEnabled(true); // ���á���ͣ����ť����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��һ��
	void nextMusic() {
		if (++currentItem >= audioList.size()) {// ����currentItem����+1�����������ֵ���ڵ�����Ƶ�ļ�������
			currentItem = 0;
		}
		playMusic(audioList.get(currentItem)); // ����playMusic()������������
	}

	// ��һ��
	void preMusic() {
		if (--currentItem >= 0) { // ����currentItem����-1�����������ֵ���ڵ���0
			if (currentItem >= audioList.size()) { // ���currentItem��ֵ���ڵ�����Ƶ�ļ�������
				currentItem = 0;
			}
		} else {
			currentItem = audioList.size() - 1; // currentItem��ֵ����Ϊ��Ƶ�ļ�����-1
		}
		playMusic(audioList.get(currentItem)); // ����playMusic()������������
	}

	@Override
	protected void onDestroy() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop(); // ֹͣ���ֵĲ���
		}
		mediaPlayer.release(); // �ͷ���Դ
		super.onDestroy();
	}

}