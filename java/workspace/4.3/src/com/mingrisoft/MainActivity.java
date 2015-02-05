package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private SeekBar seekbar;	//�϶���	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final TextView result=(TextView)findViewById(R.id.textView1);
		seekbar = (SeekBar) findViewById(R.id.seekBar1);	//��ȡ�϶���
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(MainActivity.this, "��������", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Toast.makeText(MainActivity.this, "��ʼ����", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				result.setText("��ǰֵ��"+progress);				//�޸��ı���ͼ��ֵ
				
			}
		});
		
	}
}