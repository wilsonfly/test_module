package com.mingrisoft;
import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp = null;	//����һ��MediaPlayer����

	public static void play(Context context, int resource) {
		stop(context);
		if (SettingsActivity.getBgSound(context)) {//�ж��Ƿ񲥷ű�������
			mp = MediaPlayer.create(context, resource);
			mp.setLooping(true); // �Ƿ�ѭ������
			mp.start(); // ��ʼ����
		}
	}

	public static void stop(Context context) {
		if (mp != null) {
			mp.stop();		//ֹͣ����
			mp.release();	//�ͷ���Դ
			mp = null;
		}

	}
}