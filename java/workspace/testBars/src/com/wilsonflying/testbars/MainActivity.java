package com.wilsonflying.testbars;

import com.wilsonflying.testbars.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony.Mms.Rate;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ProgressBar progressH;
	private ProgressBar progressC;
	private Handler mHandler;
	private final int CHANGE_PROGRESS = 100;
	private final int FULL_PROGRESS = 200;
	private int mProgress;
	private SeekBar seekbar;
	private TextView tv_seekbar;
	private RatingBar ratingbar;

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

		progressH = (ProgressBar) findViewById(R.id.progressbar_horizon);
		progressC = (ProgressBar) findViewById(R.id.progressbar_circle);
		seekbar = (SeekBar) findViewById(R.id.seekbar);
		tv_seekbar = (TextView) findViewById(R.id.textview_showseekbar);
		ratingbar = (RatingBar) findViewById(R.id.ratingbar);
				
		progressH.setVisibility(View.VISIBLE);

		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == CHANGE_PROGRESS) {
					progressH.setProgress(mProgress);
					progressC.setProgress(mProgress);
				} else {
					progressH.setVisibility(View.GONE);
					progressC.setVisibility(View.GONE);
					Toast.makeText(MainActivity.this, "This's it,100% now!",
							Toast.LENGTH_LONG).show();
				}
			}
		};

		new Thread() {
			public void run() {
				while (true) {
					mProgress = doSomething();
					Message msg = new Message();
					if (mProgress < 100) {
						msg.what = CHANGE_PROGRESS;
						mHandler.sendMessage(msg);
					} else {
						msg.what = FULL_PROGRESS;
						mHandler.sendMessage(msg);
						break;
					}
				}
			}

			private int doSomething() {
				mProgress += Math.random() * 10;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return mProgress;
			}
		}.start();
		
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "结束调节seekbar", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "开始调节seekbar", Toast.LENGTH_SHORT).show();

			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				tv_seekbar.setText("seekbar当前位置："+progress);
			}
		});
		
		
		ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "评价"+rating+"颗星", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
