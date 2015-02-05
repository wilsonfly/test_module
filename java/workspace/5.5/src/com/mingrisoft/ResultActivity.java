package com.mingrisoft;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);		//���ø�Activityʹ�õĲ���
		TextView sex=(TextView)findViewById(R.id.sex);	//��ȡ��ʾ�Ա���ı���
		TextView stature=(TextView)findViewById(R.id.stature);	//��ȡ��ʾ��ߵ��ı���
		TextView weight=(TextView)findViewById(R.id.weight);	//��ȡ��ʾ��׼���ص��ı���
		Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();	//��ȡ���ݵ����ݰ�
		Info info=(Info)bundle.getSerializable("info");	//��ȡһ�������л���info����
		sex.setText("����һλ"+info.getSex()+"ʿ");	//��ȡ�Ա���ʾ����Ӧ�ı�����
		stature.setText("���������"+info.getStature()+"����");	//��ȡ��߲���ʾ����Ӧ�ı�����
		
		weight.setText("���ı�׼������"+getWeight(info.getSex(),info.getStature())+"����");	//��ʾ�����ı�׼����
	}
	/**
	 * ���ܣ������׼����
	 * @param sex
	 * @param stature
	 * @return
	 */
	private String getWeight(String sex,float stature){
		String weight="";	//��������
		NumberFormat format=new DecimalFormat();
		
		if(sex.equals("��")){	//������ʿ��׼����
			weight=format.format((stature-80)*0.7);
		}else{		//����Ůʿ��׼����
			weight=format.format((stature-70)*0.6);
		}
		return weight;
	}
}
