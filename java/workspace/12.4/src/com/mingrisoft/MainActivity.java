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
	private ImageView iv;		//声明ImageView组件的对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.imageView1);	//获取布局管理器中添加的ImageView
		
		//创建一个新线程，用于从网络上获取图片
		 new Thread(new Runnable() {
			 public void run() {
				 final Bitmap bitmap=getPicture("http://192.168.1.66:8081/test/images/android.png");	//从网络上获取图片
				 try {
					Thread.sleep(2000);		//线程休眠2秒钟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//发送一个Runnable对象
				iv.post(new Runnable() {
					 public void run() {
						 	iv.setImageBitmap(bitmap);	//在ImageView中显示从网络上获取到的图片
						 }
					 });
				 }
				 
			 }).start();	//开启线程	
	}
	/**
	 * 功能：根据网址获取图片对应的Bitmap对象
	 * @param path
	 * @return
	 */
	public Bitmap getPicture(String path){
		Bitmap bm=null;
		 try {
				URL url=new URL(path);	//创建URL对象
				URLConnection conn=url.openConnection();	//获取URL对象对应的连接
				conn.connect();	//打开连接
				InputStream is=conn.getInputStream();	//获取输入流对象
				bm=BitmapFactory.decodeStream(is);	//根据输入流对象创建Bitmap对象
				
			} catch (MalformedURLException e1) {
				e1.printStackTrace();	//输出异常信息
			} catch (IOException e) {
				e.printStackTrace();	//输出异常信息
			}
		 return bm;
	}

}