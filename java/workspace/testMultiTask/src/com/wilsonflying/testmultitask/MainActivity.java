package com.wilsonflying.testmultitask;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import com.wilsonflying.testmultitask.R.drawable;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ImageView img = (ImageView) findViewById(msg.arg1);
			img.setImageDrawable((Drawable) msg.obj);

		};
	};


	private ExecutorService fixedThreadPool;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		UseMultiThread();
//		UseThreadPool();
		UseThreadPoolAndCache();

	}


	public void UseMultiThread() {
		LoadImageByThread("http://www.baidu.com/img/baidu_logo.gif",R.id.imageView1);
		LoadImageByThread("http://cache.soso.com/30d/img/web/logo.gif",R.id.imageView2);
		LoadImageByThread("http://csdnimg.cn/www/images/csdnindex_logo.gif",R.id.imageView3);
		LoadImageByThread("http://images.cnblogs.com/logo_small.gif",R.id.imageView4);
		LoadImageByThread("http://www.chinatelecom.com.cn/images/logo_new.gif",R.id.imageView5);
	}
	
	public void UseThreadPool(){
		fixedThreadPool = Executors.newFixedThreadPool(2);
		
		LoadImageByThreadPoolAndCached("http://www.baidu.com/img/baidu_logo.gif",R.id.imageView1);
		LoadImageByThreadPoolAndCached("http://cache.soso.com/30d/img/web/logo.gif",R.id.imageView2);
		LoadImageByThreadPoolAndCached("http://csdnimg.cn/www/images/csdnindex_logo.gif",R.id.imageView3);
		LoadImageByThreadPoolAndCached("http://images.cnblogs.com/logo_small.gif",R.id.imageView4);
		LoadImageByThreadPoolAndCached("http://www.chinatelecom.com.cn/images/logo_new.gif",R.id.imageView5);
	}


	private void UseThreadPoolAndCache() {
		fixedThreadPool = Executors.newFixedThreadPool(2);
		
		LoadImageByThreadPool("http://www.baidu.com/img/baidu_logo.gif",R.id.imageView1);
		LoadImageByThreadPool("http://cache.soso.com/30d/img/web/logo.gif",R.id.imageView2);
		LoadImageByThreadPool("http://csdnimg.cn/www/images/csdnindex_logo.gif",R.id.imageView3);
		LoadImageByThreadPool("http://images.cnblogs.com/logo_small.gif",R.id.imageView4);
		LoadImageByThreadPool("http://www.chinatelecom.com.cn/images/logo_new.gif",R.id.imageView5);
		
		//展示重复内容
		LoadImageByThreadPool("http://csdnimg.cn/www/images/csdnindex_logo.gif",R.id.imageView1);
		LoadImageByThreadPool("http://images.cnblogs.com/logo_small.gif",R.id.imageView2);
	}
	
	public void LoadImageByThread(final String url, final int id) {
		new Thread() {
			public void run() {
				try {
					Drawable drawable = Drawable.createFromStream(
							new URL(url).openStream(), "image.png");
					
					Thread.sleep(2000);
					
					sendMessageToHandler(id, drawable);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

	public void LoadImageByThreadPool(final String url, final int id){
		fixedThreadPool.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Drawable drawable = Drawable.createFromStream(new URL(url).openStream(), "image");
					
					Thread.sleep(2000);
					
					sendMessageToHandler(id, drawable);
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void LoadImageByThreadPoolAndCached(String url, int id) {
		Map<String, SoftReference<Drawable>> imgCache = new HashMap<String, SoftReference<Drawable>>();
		if(imgCache.containsKey(url)){
			SoftReference<Drawable> d = imgCache.get(url);
			//如果还能get到说明没有被回收掉，可以继续使用
			if(d.get() != null){
				sendMessageToHandler(id, d.get());
				return;
			}
		}
		
		try {
			Drawable drawable = Drawable.createFromStream(new URL(url).openStream(), "image");
//			imgCache.put(url, drawable);
			imgCache.put(url, new SoftReference<Drawable>(drawable));//需要转化一下，然后放入缓存中
			
			Thread.sleep(2000);
			
			sendMessageToHandler(id, drawable);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
	public void sendMessageToHandler(int id, Drawable drawable){
		Message msg = Message.obtain();
		msg.arg1 = id;
		msg.obj = drawable;
		handler.sendMessage(msg);
	}
	
}
