package com.mingrisoft;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
	private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv=(TextView)findViewById(R.id.show);
		registerForContextMenu(tv);		//Ϊ�ı���ע�������Ĳ˵�

	}
	//���������Ĳ˵�
	/************************************************************/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflator=new MenuInflater(this); 	//ʵ����һ��MenuInflater����
		inflator.inflate(R.menu.contextmenu, menu); 	//�����˵��ļ�
		menu.setHeaderIcon(R.drawable.ic_launcher);		//Ϊ�˵�ͷ����ͼ��
		menu.setHeaderTitle("��ѡ��������ɫ��");			//Ϊ�˵�ͷ���ñ���

	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.color1:		//��ѡ�����ɫʱ
				tv.setTextColor(Color.rgb(255, 0, 0));
				break;
			case R.id.color2:		//��ѡ������ɫʱ
				tv.setTextColor(Color.rgb(0, 255, 0));
				break;
			case R.id.color3:		//��ѡ������ɫʱ
				tv.setTextColor(Color.rgb(0, 0, 255));
				break;
			case R.id.color4:		//��ѡ���ɫʱ
				tv.setTextColor(Color.rgb(255, 180, 0));
				break;
			default:
				tv.setTextColor(Color.rgb(255, 255, 255));
		}
		return true;
	}

	
}
