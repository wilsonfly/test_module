package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LooperThread thread=new LooperThread();	//����һ���߳�
		thread.start();	//�����߳�
	}


}