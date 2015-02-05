package com.mingrisoft;
import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp = null;	//声明一个MediaPlayer对象

	public static void play(Context context, int resource) {
		stop(context);
		if (SettingsActivity.getBgSound(context)) {//判断是否播放背景音乐
			mp = MediaPlayer.create(context, resource);
			mp.setLooping(true); // 是否循环播放
			mp.start(); // 开始播放
		}
	}

	public static void stop(Context context) {
		if (mp != null) {
			mp.stop();		//停止播放
			mp.release();	//释放资源
			mp = null;
		}

	}
}