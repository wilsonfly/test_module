package com.wilsonflying.testsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MyViewNO1 extends SurfaceView implements Callback{

	private Paint paint = null;
	
	public MyViewNO1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setColor(Color.GREEN);
		
		getHolder().addCallback(this);
	}

	public void MyDraw(){
		Canvas canvas = getHolder().lockCanvas();
		
		canvas.drawColor(Color.WHITE);
		
		canvas.save();
		canvas.drawCircle(200, 200, 50, paint);
		canvas.drawLine(0, getHeight()/2, getWidth(), 0, paint);
		canvas.restore();
		
		getHolder().unlockCanvasAndPost(canvas);
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		MyDraw();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
