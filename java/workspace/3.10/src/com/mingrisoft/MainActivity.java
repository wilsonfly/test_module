package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       addClick();
    }
    public void addClick(){
        //Ϊ�����롱��ť��ӵ����¼�����
        ImageView img0=(ImageView)findViewById(R.id.imageButton0);
        img0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "������Ϸ", Toast.LENGTH_SHORT).show();
			}
		});
        //Ϊ�����á���ť��ӵ����¼�����
        ImageView img1=(ImageView)findViewById(R.id.imageButton1);
        img1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "��Ϸ����", Toast.LENGTH_SHORT).show();
			}
		});
        //Ϊ���˳�����ť��ӵ����¼�����
        ImageView img2=(ImageView)findViewById(R.id.imageButton2);
        img2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "�˳���Ϸ", Toast.LENGTH_SHORT).show();
			}
		});
        //Ϊ����������ť��ӵ����¼�����
        ImageView img3=(ImageView)findViewById(R.id.imageButton3);
        img3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "����", Toast.LENGTH_SHORT).show();
			}
		});
        //Ϊ�����ư񡱰�ť��ӵ����¼�����
        ImageView img4=(ImageView)findViewById(R.id.imageButton4);
        img4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "���ư�", Toast.LENGTH_SHORT).show();
			}
		});     	
    }
}