package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button1 = (Button) findViewById(R.id.button1); // ��ȡ�����ļ�����ӵ�button1
		// Ϊ��ť��ӵ����¼�����
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button b = (Button) v; // ��ȡ��ǰ��ť
				b.setEnabled(false); // �ð�ť��Ϊ������
				b.setText("���ǲ����ð�ť"); // �ı䰴ť����ʾ������
				Toast.makeText(MainActivity.this, "��ť��Ϊ������", Toast.LENGTH_SHORT)
						.show(); // ��ʾ��Ϣ��ʾ��
			}
		});
		Button button2 = (Button) findViewById(R.id.button2); // ��ȡ�����ļ�����ӵ�button2
		// Ϊ��ť��ӵ����¼�����
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				button1.setEnabled(true); // ��button1��Ϊ����
				button1.setText("���ǿ��ð�ť"); // �ı䰴ť����ʾ������
			}
		});
	}
}