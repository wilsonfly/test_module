package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        int[] tvID=new int[]{R.id.str1,R.id.str2,R.id.str3,
        		R.id.str4,R.id.str5,R.id.str6,R.id.str7};		//����TextView�����ID����
        int[] tvColor=new int[]{R.color.color1,R.color.color2,R.color.color3,	
        		R.color.color4,R.color.color5,R.color.color6,R.color.color7};   //ʹ����ɫ��Դ     
        for(int i=0;i<7;i++){
        	TextView tv=(TextView)findViewById(tvID[i]);	//����ID��ȡTextView���
        	tv.setGravity(Gravity.CENTER);		//�������־�����ʾ
        	tv.setBackgroundColor(getResources().getColor(tvColor[i]));	//ΪTextView������ñ�����ɫ
        	tv.setHeight((int)(getResources().getDimension(R.dimen.basic))*(i+2)/2);		//ΪTextView������ø߶�
        }

    }
}