package com.wilsonflying.DefineMyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyRotatingView extends View {

	private float degrees = 0;
	
	public MyRotatingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
//		paint.setShadowLayer(40, 50, 50, Color.rgb(180, 180, 180));
		paint.setShadowLayer(2, 30, 3, Color.rgb(180, 180, 180));
		canvas.translate(200, 200);
		canvas.rotate(degrees, 50, 50);
		canvas.drawRect(10, 10, 80, 80, paint);
		
//		super.onDraw(canvas);
		degrees++;
		
		invalidate();
	}
}
