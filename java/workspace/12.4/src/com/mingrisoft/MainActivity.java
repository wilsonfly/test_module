package com.mingrisoft;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {	
	private ImageView iv;		//����ImageView����Ķ���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.imageView1);	//��ȡ���ֹ���������ӵ�ImageView
		
		//����һ�����̣߳����ڴ������ϻ�ȡͼƬ
		 new Thread(new Runnable() {
			 public void run() {
				 final Bitmap bitmap=getPicture("http://192.168.1.66:8081/test/images/android.png");	//�������ϻ�ȡͼƬ
				 try {
					Thread.sleep(2000);		//�߳�����2����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//����һ��Runnable����
				iv.post(new Runnable() {
					 public void run() {
						 	iv.setImageBitmap(bitmap);	//��ImageView����ʾ�������ϻ�ȡ����ͼƬ
						 }
					 });
				 }
				 
			 }).start();	//�����߳�	
	}
	/**
	 * ���ܣ�������ַ��ȡͼƬ��Ӧ��Bitmap����
	 * @param path
	 * @return
	 */
	public Bitmap getPicture(String path){
		Bitmap bm=null;
		 try {
				URL url=new URL(path);	//����URL����
				URLConnection conn=url.openConnection();	//��ȡURL�����Ӧ������
				conn.connect();	//������
				InputStream is=conn.getInputStream();	//��ȡ����������
				bm=BitmapFactory.decodeStream(is);	//�������������󴴽�Bitmap����
				
			} catch (MalformedURLException e1) {
				e1.printStackTrace();	//����쳣��Ϣ
			} catch (IOException e) {
				e.printStackTrace();	//����쳣��Ϣ
			}
		 return bm;
	}

}