package com.wilsonflying.PangMouse;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView img;
	private Handler handler;
	private int count=0;
	private final int TO_MASTER = 100;
	public int[][] position = new int[][] { { 231, 325 }, { 424, 349 },
			{ 521, 256 }, { 543, 296 }, { 719, 245 }, { 832, 292 },
			{ 772, 358 } };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		img = (ImageView) findViewById(R.id.img);
		img.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				count++;
				img.setVisibility(View.INVISIBLE);
				Toast.makeText(getApplicationContext(), "succeeded "+count+" times !", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				int index = 0;
				if(msg.what == TO_MASTER){
					index = msg.arg1;
					img.setX(position[index][0]);
					img.setY(position[index][1]);
					img.setVisibility(View.VISIBLE);
				}
			}
		};
		
		new Thread(){
			public void run() {
				int index = 0;
				while(!Thread.currentThread().isInterrupted()){
					index = new Random().nextInt(position.length);
					
					Message msg = handler.obtainMessage();
					msg.what = TO_MASTER;
					msg.arg1 = index;
					handler.sendMessage(msg);
					
					try {
						Thread.sleep(new Random().nextInt(500)+800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		
	}

	

}
