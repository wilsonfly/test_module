package com.mingrisoft;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Handler handler;	//����Handler����
	private static LinearLayout linearLayout; // ���岼��
	public static TextView[] tv = new TextView[14]; // TextView����
	int[] bgColor=new int[]{R.color.color1,R.color.color2,R.color.color3,	
			R.color.color4,R.color.color5,R.color.color6,R.color.color7};  		//ʹ����ɫ��Դ     
	private int index=0;	//��ǰ��ɫֵ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		linearLayout=(LinearLayout)findViewById(R.id.ll);	//��ȡ���Բ��ֹ�����
		int height=this.getResources().getDisplayMetrics().heightPixels;	//��ȡ��Ļ�ĸ߶�
	    for(int i=0;i<tv.length;i++){
        	tv[i]=new TextView(this);	//����һ���ı������
        	tv[i].setWidth(this.getResources().getDisplayMetrics().widthPixels);	//�����ı���Ŀ��
        	tv[i].setHeight(height/tv.length);	//Ϊ�����ı���ĸ߶�
        	linearLayout.addView(tv[i]);	//��TextView�����ӵ����Բ��ֹ�������
        }

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int temp=0;	//��ʱ����
				if (msg.what == 0x101) {
					for(int i=0;i<tv.length;i++){
						temp=new Random().nextInt(bgColor.length);	//����һ�������
						//ȥ���ظ��Ĳ������ڵ���ɫ
						if(index==temp){
							temp++;
							if(temp==bgColor.length){
								temp=0;
							}
						}
						index=temp;
						tv[i].setBackgroundColor(getResources().getColor(bgColor[index]));	//Ϊ�ı������ñ���
					}
				}
				super.handleMessage(msg);
			}

		};
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					Message m = handler.obtainMessage();	//��ȡһ��Message
					m.what=0x101;	//������Ϣ��ʶ
					handler.sendMessage(m);	//������Ϣ
					try {
						Thread.sleep(new Random().nextInt(1000));	//����1����
					} catch (InterruptedException e) {
						e.printStackTrace();		//����쳣��Ϣ
					}

				}
				
			}
			
		});
		t.start(); // �����߳�

	}

}