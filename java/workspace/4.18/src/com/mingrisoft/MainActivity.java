package com.mingrisoft;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	private int imageId[] = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04 }; // 保存要显示图片ID的数组
	private LinearLayout l;		//定义一个垂直线性布局管理器的对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // 显示水平进度条
		setContentView(R.layout.main);
		l = (LinearLayout) findViewById(R.id.linearlayout1); // 获取布局文件中添加的垂直布局管理器中
		new MyTack().execute(); // 执行自定义任务

	}

	/**
	 * 功能：创建异步任务，添加4张图片
	 * 
	 */
	class MyTack extends AsyncTask<Void, Integer, LinearLayout> {
		@Override
		protected void onPreExecute() {
			setProgressBarVisibility(true); // 执行任务前让进度条可见
			super.onPreExecute();
		}

		/*
		 * 功能：要执行的耗时任务
		 */
		@Override
		protected LinearLayout doInBackground(Void... params) {
			LinearLayout ll = new LinearLayout(MainActivity.this); // 创建一个水平线性布局管理器
			for (int i = 1; i < 5; i++) {
				ImageView iv = new ImageView(MainActivity.this);
				iv.setLayoutParams(new LayoutParams(245, 108));
				iv.setImageResource(imageId[i - 1]);
				ll.addView(iv); // 将ImageView添加到线性布局管理器中
				try {
					Thread.sleep(10); // 为了更好的看到效果，这里让线程休眠10毫秒
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i); // 触发onProgressUpdate(Progress...)方法更新进度
			}
			return ll;
		}

		/*
		 * 功能：更新进度
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			setProgress(values[0] * 2500); // 动态更新最新进度
			super.onProgressUpdate(values);
		}

		/*
		 * 功能：任务执行后
		 */
		@Override
		protected void onPostExecute(LinearLayout result) {
			System.out.println("任务执行后");
			setProgressBarIndeterminateVisibility(false);// 任务执行后让进度条隐藏
			l.addView(result); // 将水平线性布局管理器添加到布局文件中添加的垂直线性布局管理器中
			super.onPostExecute(result);
		}
	}
}