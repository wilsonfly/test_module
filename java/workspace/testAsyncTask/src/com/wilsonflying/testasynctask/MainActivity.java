package com.wilsonflying.testasynctask;


import com.wilsonflying.testasynctask.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	private LinearLayout linearlayout;
	private int imageId[] = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04 };

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		requestWindowFeature(Window.FEATURE_PROGRESS);

		setContentView(R.layout.my_layout);
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		new myTask().execute();
	}

	class myTask extends AsyncTask<Void, Integer, LinearLayout> {

		@Override
		protected void onPreExecute() {
			setProgressBarVisibility(true);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(LinearLayout result) {
			setProgressBarVisibility(false);
			linearlayout.addView(result);
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			setProgress(values[0] * 2500);//Де10, 4*2500
			super.onProgressUpdate(values);
		}

		@Override
		protected LinearLayout doInBackground(Void... params) {
			LinearLayout ll = new LinearLayout(MainActivity.this);
			for (int i = 1; i < 5; i++) {
				ImageView iv = new ImageView(MainActivity.this);
				iv.setLayoutParams(new LayoutParams(200, 100));
				iv.setImageResource(imageId[i-1]);
				ll.addView(iv);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return ll;
		}

	}
}
