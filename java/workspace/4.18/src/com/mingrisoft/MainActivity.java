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
			R.drawable.img03, R.drawable.img04 }; // ����Ҫ��ʾͼƬID������
	private LinearLayout l;		//����һ����ֱ���Բ��ֹ������Ķ���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // ��ʾˮƽ������
		setContentView(R.layout.main);
		l = (LinearLayout) findViewById(R.id.linearlayout1); // ��ȡ�����ļ�����ӵĴ�ֱ���ֹ�������
		new MyTack().execute(); // ִ���Զ�������

	}

	/**
	 * ���ܣ������첽�������4��ͼƬ
	 * 
	 */
	class MyTack extends AsyncTask<Void, Integer, LinearLayout> {
		@Override
		protected void onPreExecute() {
			setProgressBarVisibility(true); // ִ������ǰ�ý������ɼ�
			super.onPreExecute();
		}

		/*
		 * ���ܣ�Ҫִ�еĺ�ʱ����
		 */
		@Override
		protected LinearLayout doInBackground(Void... params) {
			LinearLayout ll = new LinearLayout(MainActivity.this); // ����һ��ˮƽ���Բ��ֹ�����
			for (int i = 1; i < 5; i++) {
				ImageView iv = new ImageView(MainActivity.this);
				iv.setLayoutParams(new LayoutParams(245, 108));
				iv.setImageResource(imageId[i - 1]);
				ll.addView(iv); // ��ImageView��ӵ����Բ��ֹ�������
				try {
					Thread.sleep(10); // Ϊ�˸��õĿ���Ч�����������߳�����10����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i); // ����onProgressUpdate(Progress...)�������½���
			}
			return ll;
		}

		/*
		 * ���ܣ����½���
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			setProgress(values[0] * 2500); // ��̬�������½���
			super.onProgressUpdate(values);
		}

		/*
		 * ���ܣ�����ִ�к�
		 */
		@Override
		protected void onPostExecute(LinearLayout result) {
			System.out.println("����ִ�к�");
			setProgressBarIndeterminateVisibility(false);// ����ִ�к��ý���������
			l.addView(result); // ��ˮƽ���Բ��ֹ�������ӵ������ļ�����ӵĴ�ֱ���Բ��ֹ�������
			super.onPostExecute(result);
		}
	}
}