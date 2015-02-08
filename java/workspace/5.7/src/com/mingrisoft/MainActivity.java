package com.mingrisoft;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // ����ʹ�õĲ�������

	}

	// ����һ���̳�Activity���ڲ��࣬�������ֻ������У�ͨ��Activity��ʾ��ϸ����
	public static class DetailActivity extends Activity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// �ж��Ƿ�Ϊ���������Ϊ�������������ǰActivity��׼��ʹ��Fragment��ʾ��ϸ����
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				finish(); // ������ǰActivity
				return;
			}

			if (savedInstanceState == null) { //
				// �ڳ�ʼ��ʱ����һ����ʾ��ϸ���ݵ�Fragment
				DetailFragment details = new DetailFragment();// ʵ����DetailFragment�Ķ���
				details.setArguments(getIntent().getExtras()); // ����Ҫ���ݵĲ���
				getFragmentManager().beginTransaction()
						.add(android.R.id.content, details).commit(); // ���һ����ʾ��ϸ���ݵ�Fragment
			}
		}
	}

}