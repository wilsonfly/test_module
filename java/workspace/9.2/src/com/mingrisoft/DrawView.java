package com.mingrisoft;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {

	/**
	 * 功能：构造方法
	 */
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	/* 
	 * 功能：重写onDraw()方法
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint=new Paint();	//定义一个采用默认设置的画笔
		paint.setColor(Color.RED);	//设置颜色为红色
		paint.setShadowLayer(2, 3, 3, Color.rgb(180, 180, 180));	//设置阴影
		canvas.drawRect(40, 40, 200, 100, paint);		//绘制矩形	
		super.onDraw(canvas);
	}

}
