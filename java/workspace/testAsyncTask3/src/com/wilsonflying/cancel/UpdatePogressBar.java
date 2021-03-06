package com.wilsonflying.cancel;

import com.wilsonflying.cancel.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

public class UpdatePogressBar extends Activity{

	private ProgressBar mProgressBar;
	private MyTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_progressbar);
		
        mProgressBar = (ProgressBar) findViewById(R.id.pg);
        mTask = new MyTask();
        mTask.execute();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		if(mTask != null && mTask.getStatus()==AsyncTask.Status.RUNNING){
			//cancel 方法只是将对应的task标记为cancel状态，并不是真正的取消一个相乘的执行
			mTask.cancel(true);//true：cancel掉之后线程是否完成其操作。
		}
	}

    public class MyTask extends AsyncTask<Void, Integer, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
				
				for(int i=0; i<100; i++){
					
					//如果已经标记为了cancel，立即结束此任务
					if(isCancelled()){
						break;
					}
					
					publishProgress(i);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			mProgressBar.setVisibility(ProgressBar.VISIBLE);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mProgressBar.setVisibility(ProgressBar.GONE);
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			
			if(isCancelled()){
				return;
			}
			mProgressBar.setProgress(values[0]);
		}
    	
    }
}
