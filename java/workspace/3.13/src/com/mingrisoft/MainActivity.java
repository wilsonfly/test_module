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
        Button login=(Button)findViewById(R.id.login);		//ͨ��ID��ȡ�����ļ�����ӵİ�ť
        login.setOnClickListener(new OnClickListener() {	//Ϊ��ť��ӵ����¼�����
			
			@Override
			public void onClick(View v) {
				Toast toast=Toast.makeText(MainActivity.this, "����������ͨ��ť", Toast.LENGTH_SHORT);
				toast.show();
				
			}
		});
    }
    public void myClick(View view){
    	Toast toast=Toast.makeText(MainActivity.this, "��������ͼƬ��ť", Toast.LENGTH_SHORT);
		toast.show();
    }
}