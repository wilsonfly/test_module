package com.wilsonflying.testhttp2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String TAG = "testHttp2";
	private Handler mHander  = new Handler();
	private String mPicAddr = "http://ww4.sinaimg.cn/bmiddle/786013a5jw1e7akotp4bcj20c80i3aao.jpg";
	private ImageView iv;
	private TextView tv;
	private ProgressDialog progress;
	private final int UPDATE_PICTURE = 66;
	private final int DOWNLOAD_PICTURE_FAILED = 77;
	
	//TODO 使用了带进度条和不带进度条的两种progress，使用了其中一种后再使用另外一种会受影响，样式不能正确显示
	
	private Handler mHander2 = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_PICTURE:
				tv.setText("handler sendmessage 方式更新图片");
				iv.setImageBitmap((Bitmap) msg.obj);
				progress.dismiss();

				break;
			case DOWNLOAD_PICTURE_FAILED:
				progress.dismiss();
				Toast.makeText(MainActivity.this, "下载图片失败！！！", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv = (ImageView) findViewById(R.id.ivToShow);
		tv = (TextView) findViewById(R.id.tvMsg);
		progress = new ProgressDialog(this);
	}

	public void onClickGetPic(View view){
		switch (view.getId()) {
		case R.id.btnGetPicPost:
			progress.setTitle("提示");
			progress.setMessage("正在下载图片，请稍后。。。");
			progress.setCancelable(false);
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.show();
			new Thread(new MyRunnable()).start();
			
			break;
		case R.id.btnGetPicMsg:
			progress.setTitle("提示");
			progress.setMessage("正在下载图片，请稍后。。。");
			progress.setCancelable(false);
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.show();
			new MyThread().start();

			break;
		case R.id.btnAsyncTask:
			new MyAsyncTask().execute(mPicAddr);
			
			break;
		case R.id.btnAsyncTaskProgress:
			new MyAsyncTaskProgress().execute(mPicAddr);
			
			break;
		default:
			break;
		}
	}
	
	public class MyRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(mPicAddr);
			try {
				HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//HttpStatus.SC_OK==200
					byte[] data = EntityUtils.toByteArray(response.getEntity());
					final Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
					
					if(bm != null){
						mHander.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								iv.setImageBitmap(bm);
								tv.setText("Handler post方式设置图片");
							}
						});
						progress.dismiss();
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class MyThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			super.run();
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(mPicAddr);
			try {
				HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//HttpStatus.SC_OK==200
					byte[] data = EntityUtils.toByteArray(response.getEntity());
					final Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
					
					if(bm != null){
						Message msg = Message.obtain();
						msg.what = UPDATE_PICTURE;
						msg.obj = bm;
						mHander2.sendMessage(msg);
					}else{
						mHander2.sendEmptyMessageDelayed(DOWNLOAD_PICTURE_FAILED, 1000);
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap bm = null;
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			try {
				HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//HttpStatus.SC_OK==200
					byte[] data = EntityUtils.toByteArray(response.getEntity());
					bm = BitmapFactory.decodeByteArray(data, 0, data.length);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bm;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			progress.setTitle("提示");
			progress.setMessage("正在下载图片，请稍后。。。");
			progress.setCancelable(false);
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.show();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			progress.dismiss();
			
			if(result != null){
				iv.setImageBitmap(result);
				tv.setText("AsyncTask 获取并设置的图片");
			}else{
				Toast.makeText(MainActivity.this, "下载图片失败！！！", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			
		}
		
	}

	public class MyAsyncTaskProgress extends AsyncTask<String, Integer, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap bm = null;
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			try {
				HttpResponse response = client.execute(get);
				HttpEntity entity = response.getEntity();
				InputStream is = null;
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int progress = 0;
				
				if( entity!=null && response.getStatusLine().getStatusCode()==HttpStatus.SC_OK ){
					int file_lenth = (int) entity.getContentLength();
					int total_length = 0;
					int length = 0;
					is = entity.getContent();
					while( (length=is.read(buf)) >= 0){
//						bos.write(buf, 0, buf.length);//使用buf.length下载的图片有错位的异常
						bos.write(buf, 0, length);//读到多少，写入多少
						
						total_length += length;
//						progress = total_length/file_lenth * 100;
						progress = (int) (total_length/(float)file_lenth * 100);//不转float的话两个int值相除结果小于1就给缩减为0了！

						publishProgress(progress);

						Thread.sleep(100);
					}
					
					bm = BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.toByteArray().length);
				}else{
					Log.d(TAG, "获取数据失败。。。");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bm;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			progress.setTitle("提示");
			progress.setMessage("正在下载图片，请稍后。。。");
			progress.setCancelable(false);
			progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progress.show();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			progress.dismiss();
			
			if(result != null){
				iv.setImageBitmap(result);
				tv.setText("AsyncTask 获取并设置的图片");
			}else{
				Toast.makeText(MainActivity.this, "下载图片失败！！！", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			Log.d(TAG, "progress:::::::::::::::::::"+values[0]);
			progress.setProgress(values[0]);
		}
		
	}
	
}
