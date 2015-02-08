package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	//����ѡ��˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=new MenuInflater(this);		//ʵ����һ��MenuInflater����
		inflater.inflate(R.menu.optionmenu, menu);		//�����˵��ļ�
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getGroupId()==R.id.setting){	//�ж��Ƿ�ѡ���˲������ò˵���
			if(item.isChecked()){				//���˵����Ѿ���ѡ��
				item.setChecked(false);			//���ò˵����ѡ��
			}else{
				item.setChecked(true);			//���ò˵��ѡ��
			}
		}
		if(item.getItemId()!=R.id.item2){
			//������Ϣ��ʾ����ʾѡ��Ĳ˵���ı���
			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
		}
		return true;
	}
	
}
