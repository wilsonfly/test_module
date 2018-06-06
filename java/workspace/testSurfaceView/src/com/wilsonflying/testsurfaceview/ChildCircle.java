package com.wilsonflying.testsurfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ChildCircle extends Container{

	private Paint paint = null;
	
	public ChildCircle() {
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setColor(Color.GREEN);
	}
	
	@Override
	public void drawChildView(Canvas canvas) {
		// TODO Auto-generated method stub
		super.drawChildView(canvas);
		canvas.drawCircle(100, 100, 50, paint);
	}

}