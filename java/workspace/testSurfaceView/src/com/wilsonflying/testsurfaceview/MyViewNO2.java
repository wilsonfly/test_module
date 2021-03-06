package com.wilsonflying.testsurfaceview;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MyViewNO2 extends SurfaceView implements Callback{

	private Container container;
	private ChildCircle circle;
	private ChildRect rect;
	
	public MyViewNO2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		container = new Container();
		circle = new ChildCircle();
		rect = new ChildRect();
		
//		container.add(circle);
		rect.add(circle);
		container.add(rect);
		
		rect.setY(rect.getY() + 3);
//		container.setY(container.getY()+1);

		getHolder().addCallback(this);
	}

	public void draw(){
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.WHITE);
		
		rect.setY(rect.getY() + 3);//方法二
//		container.setY(container.getY()+1);//方法三

		container.MyDraw(canvas);
		
		getHolder().unlockCanvasAndPost(canvas);
	}
	
	private Timer timer = null;
	private TimerTask task = null;
	public void StartTimer(){
		if(timer == null){
			timer = new Timer();
		}
		if(task == null){
			task = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					draw();
				}
			};
		}
		
		timer.schedule(task, 1000, 500);
	}
	
	public void StopTimer(){
		if(timer != null){
			timer.cancel();
			timer = null;
		}
		if(task != null){
			task.cancel();
			task = null;
		}
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		StartTimer();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		StopTimer();
	}

}
