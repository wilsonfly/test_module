package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		EditText et=(EditText)findViewById(R.id.editText1);	//��ȡ�༭�����
		registerForContextMenu(et);		//Ϊ�༭��ע�������Ĳ˵�
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Toast.makeText(this,item.getTitle(), Toast.LENGTH_SHORT).show();	//��ʾѡ��Ĳ˵���
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflator=new MenuInflater(this); 		//ʵ����һ��MenuInflater����
		inflator.inflate(R.menu.contextmenu, menu); 		//�����˵��ļ�
	}
	
}
