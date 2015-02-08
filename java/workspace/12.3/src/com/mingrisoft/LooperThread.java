package com.mingrisoft;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


public class LooperThread extends Thread {
	 public Handler handler1;	//����һ��Handler����
	@Override
	public void run() {
		super.run();
		
		Looper.prepare();	//��ʼ��Looper����
		 // ʵ����һ��Handler���� 
		handler1 = new Handler() {
		public void handleMessage(Message msg) {
			Log.i("Looper",String.valueOf(msg.what));
			} 
		};
		
		Message m=handler1.obtainMessage();	//��ȡһ����Ϣ
		m.what=0x11;	//����Message��what���Ե�ֵ
		handler1.sendMessage(m);	//������Ϣ
		Looper.loop();	//����Looper
	}
}