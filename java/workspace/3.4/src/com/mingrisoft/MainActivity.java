package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mylayout); // ��ȡ֡���ֹ�����
		final RabbitView rabbit = new RabbitView(MainActivity.this); // ������ʵ����RabbitView��
		// ΪС������Ӵ����¼�����
		rabbit.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				rabbit.bitmapX = event.getX(); // ����С������ʾλ�õ�X����
				rabbit.bitmapY = event.getY(); // ����С������ʾλ�õ�Y����
				rabbit.invalidate(); // �ػ�rabbit���
				return true;
			}
		});
		frameLayout.addView(rabbit); // ��rabbit��ӵ����ֹ�������

	}
}