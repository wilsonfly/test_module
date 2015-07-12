package com.wilsonflying.testsurfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ChildRect extends Container{

	private Paint paint = null;
	
	public ChildRect() {
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setColor(Color.BLUE);
	}
	
	@Override
	public void drawChildView(Canvas canvas) {
		// TODO Auto-generated method stub
		super.drawChildView(canvas);
		canvas.drawRect(0, 0, 200, 200, paint);
		
//		this.setY(getY() + 2);//方法一，移动动作放这太别扭了
	}

}
