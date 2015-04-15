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
	private MediaPlayer mediaPlayer; // 声明MediaPlayer对象
	private List<String> audioList = new ArrayList<String>(); // 要播放的音频列表
	private int currentItem = 0; // 当前播放歌曲的索引
	private Button pause; // 声明一个“暂停”按钮对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mediaPlayer = new MediaPlayer(); // 实例化一个MediaPlayer对象
		Button play = (Button) findViewById(R.id.play); // 获取“播放”按钮
		Button stop = (Button) findViewById(R.id.stop); // 获取“停止”按钮
		pause = (Button) findViewById(R.id.pause); // 获取“暂停/继续”按钮
		Button pre = (Button) findViewById(R.id.pre); // 获取“上一首”按钮
		Button next = (Button) findViewById(R.id.next); // 获取“下一首”按钮
		audioList(); // 使用ListView组件显示SD卡上的全部音频文件
		// 为MediaPlayer添加完成事件监听器
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				nextMusic(); // 播放下一首
			}
		});
		// 停止
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.stop(); // 停止播放音频
				}
				pause.setEnabled(false); // 设置“暂停”按钮不可用
			}
		});
		// 为“播放”按钮添加单击事件监听器
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				playMusic(audioList.get(currentItem)); // 调用playMusic()方法播放音乐

			}
		});
		// 为“暂停”按钮添加单击事件监听器
		pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.pause(); // 暂停视频的播放
					((Button) v).setText("继续");
				} else {
					mediaPlayer.start();	//继续播放
					((Button) v).setText("暂停");
				}
			}
		});
		// 为“下一首”按钮添加单击事件监听器
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextMusic(); // 播放下一首
			}
		});
		// 为“上一首”按钮添加单击事件监听器
		pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				preMusic(); // 播放上一首
			}
		});
	}

	// 使用ListView组件显示SD卡上的全部音频文件
	private void audioList() {
		getFiles("/sdcard/"); // 获取SD卡上的全部音频文件
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, audioList); // 创建一个适配器
		ListView listview = (ListView) findViewById(R.id.list); // 获取布局管理器中添加的ListView组件
		listview.setAdapter(adapter); // 将适配器与ListView关联
		// 当单击列表项时播放音乐
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				currentItem = position; // 将当前列表项的索引值赋值给currentItem
				playMusic(audioList.get(currentItem)); // 调用playMusic()方法播放音乐
			}
		});
	}

	private void getFiles(String url) {
		File files = new File(url); // 创建文件对象
		File[] file = files.listFiles();
		try {
			for (File f : file) { // 通过for循环遍历获取到的文件数组
				if (f.isDirectory()) { // 如果是目录，也就是文件夹
					getFiles(f.getAbsolutePath()); // 递归调用
				} else {
					if (isAudioFile(f.getPath())) { // 如果是音频文件
						audioList.add(f.getPath()); // 将文件的路径添加到list集合中
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}

	private static String[] imageFormatSet = new String[] { "mp3", "wav", "3gp" }; // 合法的音频文件格式

	// 判断是否为音频文件
	private static boolean isAudioFile(String path) {
		for (String format : imageFormatSet) { // 遍历数组
			if (path.contains(format)) { // 判断是否为有合法的音频文件
				return true;
			}
		}
		return false;
	}

	// 播放音乐
	void playMusic(String path) {
		try {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop(); // 停止当前音频的播放
			}
			mediaPlayer.reset(); // 重置MediaPlayer
			mediaPlayer.setDataSource(path); // 指定要播放的音频文件
			mediaPlayer.prepare(); // 预加载音频文件
			mediaPlayer.start(); // 播放音频
			pause.setText("暂停");
			pause.setEnabled(true); // 设置“暂停”按钮可用
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下一首
	void nextMusic() {
		if (++currentItem >= audioList.size()) {// 当对currentItem进行+1操作后，如果其值大于等于音频文件的总数
			currentItem = 0;
		}
		playMusic(audioList.get(currentItem)); // 调用playMusic()方法播放音乐
	}

	// 上一首
	void preMusic() {
		if (--currentItem >= 0) { // 当对currentItem进行-1操作后，如果其值大于等于0
			if (currentItem >= audioList.size()) { // 如果currentItem的值大于等于音频文件的总数
				currentItem = 0;
			}
		} else {
			currentItem = audioList.size() - 1; // currentItem的值设置为音频文件总数-1
		}
		playMusic(audioList.get(currentItem)); // 调用playMusic()方法播放音乐
	}

	@Override
	protected void onDestroy() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop(); // 停止音乐的播放
		}
		mediaPlayer.release(); // 释放资源
		super.onDestroy();
	}

}