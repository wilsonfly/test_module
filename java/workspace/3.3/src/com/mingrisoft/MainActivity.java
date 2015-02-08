package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
    private  ImageView[] img=new ImageView[4];		//����һ������ImageView���������
    private int[] imagePath=new int[]{
    	R.drawable.img01,R.drawable.img04,R.drawable.img03,R.drawable.img02
    };	//��������ʼ��һ���������ͼƬ������
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout layout=(LinearLayout)findViewById(R.id.layout);	//��ȡXML�ļ��ж�������Բ��ֹ�����
        for(int i=0;i<imagePath.length;i++){
        	img[i]=new ImageView(this);	//����һ��ImageView���
        	img[i].setImageResource(imagePath[i]);		//ΪImageView���ָ��Ҫ��ʾ��ͼƬ
        	img[i].setPadding(5, 5, 5, 5);	//����ImageView������ڱ߾�
        	LayoutParams params=new LayoutParams(253,148);		//����ͼƬ�Ŀ�Ⱥ͸߶�
        	img[i].setLayoutParams(params);	//ΪImageView������ò��ֲ���
        	layout.addView(img[i]);	//��ImageView�����ӵ����ֹ�������
        }
    }
}