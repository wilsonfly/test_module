package com.mingrisoft;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DrawActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	}

	// ����ѡ��˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);//ʵ����һ��MenuInflater����
		inflator.inflate(R.menu.toolsmenu, menu);	//�����˵��ļ�
		return super.onCreateOptionsMenu(menu);
	}

	// ���˵��ѡ��ʱ��������Ӧ�Ĵ���
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		DrawView dv = (DrawView) findViewById(R.id.drawView1);	//��ȡ�Զ���Ļ�ͼ��ͼ
		dv.paint.setXfermode(null);		//ȡ������Ч��
		dv.paint.setStrokeWidth(1);		//��ʼ�����ʵĿ��
		switch (item.getItemId()) {
		case R.id.red:
			dv.paint.setColor(Color.RED);	//���û��ʵ���ɫΪ��ɫ
			item.setChecked(true);
			break;
		case R.id.green:
			dv.paint.setColor(Color.GREEN);	//���û��ʵ���ɫΪ��ɫ
			item.setChecked(true);
			break;
		case R.id.blue:
			dv.paint.setColor(Color.BLUE);	//���û��ʵ���ɫΪ��ɫ
			item.setChecked(true);
			break;
		case R.id.width_1:
			dv.paint.setStrokeWidth(1);	//���ñʴ��Ŀ��Ϊ1����
			break;
		case R.id.width_2:
			dv.paint.setStrokeWidth(5);	//���ñʴ��Ŀ��Ϊ5����
			break;
		case R.id.width_3:
			dv.paint.setStrokeWidth(10);//���ñʴ��Ŀ��Ϊ10����
			break;
		case R.id.clear:
			dv.clear();		//�����滭
			break;
		case R.id.save:
			dv.save();	//����滭
			break;
		}
		return true;
	}
}