package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private RatingBar ratingbar;	//�Ǽ�������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ratingbar = (RatingBar) findViewById(R.id.ratingBar1);	//��ȡ�Ǽ�������
		

		Button button=(Button)findViewById(R.id.button1);		//��ȡ�ύ��ť
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int result=ratingbar.getProgress();	//��ȡ����
				float rating=ratingbar.getRating();	//��ȡ�ȼ�
				float step=ratingbar.getStepSize();	//��ȡÿ������Ҫ�ı���ٸ��Ǽ�
				Log.i("�Ǽ�������","step="+step+" result="+result+" rating="+rating);
				Toast.makeText(MainActivity.this, "��õ���"+rating+"����", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
}