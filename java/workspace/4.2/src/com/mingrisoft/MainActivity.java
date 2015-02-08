package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressBar horizonP;	//ˮƽ������
	private ProgressBar circleP;	//Բ�ν�����	
	private int mProgressStatus = 0;		//��ɽ�����ʼֵ
	private Handler mHandler;		//����һ�����ڴ�����Ϣ��Handler��Ķ���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		horizonP = (ProgressBar) findViewById(R.id.progressBar1);	//��ȡˮƽ������
		circleP=(ProgressBar)findViewById(R.id.progressBar2);		//��ȡԲ�ν�����
		circleP.incrementProgressBy(-10);
		circleP.setVisibility(View.VISIBLE);
		mHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==0x111){
					horizonP.setProgress(mProgressStatus);	//���½���
					circleP.setProgress(100-mProgressStatus);
				}else{
					Toast.makeText(MainActivity.this, "��ʱ�����Ѿ����", Toast.LENGTH_SHORT).show();
					horizonP.setVisibility(View.GONE);	//���ý���������ʾ�����Ҳ�ռ�ÿռ�
					circleP.setVisibility(View.GONE);//���ý���������ʾ�����Ҳ�ռ�ÿռ�
				}
			}
			
		};
		 new Thread(new Runnable() {
			public void run() {
				while (true) {
					mProgressStatus = doWork();	//��ȡ��ʱ������ɵİٷֱ�
					
					Message m=new Message();
					if(mProgressStatus<100){
						m.what=0x111;
						mHandler.sendMessage(m);	//������Ϣ
					}else{
						m.what=0x110;
						mHandler.sendMessage(m);	//������Ϣ
						break;
					}
				}

			}
			//ģ��һ����ʱ����
			private int doWork() {
				mProgressStatus+=Math.random()*10;	//�ı���ɽ���
					try {
						Thread.sleep(200);		//�߳�����200����
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				return mProgressStatus;	//�����µĽ���
			}
		}).start();	//����һ���߳�

	}
}