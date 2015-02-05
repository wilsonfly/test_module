package com.mingrisoft;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {

	/**
	 * ���ܣ����췽��
	 */
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	/* 
	 * ���ܣ���дonDraw()����
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint=new Paint();	//����һ������Ĭ�����õĻ���
		paint.setColor(Color.RED);	//������ɫΪ��ɫ
		paint.setShadowLayer(2, 3, 3, Color.rgb(180, 180, 180));	//������Ӱ
		canvas.drawRect(40, 40, 200, 100, paint);		//���ƾ���	
		super.onDraw(canvas);
	}

}
