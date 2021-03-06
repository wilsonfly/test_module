package com.wilsonflying.drawRobot;

import com.wilsonflying.drawRobot.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		FrameLayout fl = (FrameLayout) findViewById(R.id.framelayout);
		fl.addView(new MyView(this));
	}
	
	public class MyView extends  View{

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(0xffa4c739);
			
			//脑袋
			RectF head = new RectF(10,10,100,100);
			head.offset(100, 20);
			canvas.drawArc(head, -10, -160, false, paint);//需要RectF类型的参数
			
			// test drawArc
			RectF rhead = new RectF(100, 100, 300, 300);
			rhead.offset(150, 180);
			canvas.drawRect(rhead, paint);
			paint.setColor(0xff00ffff);
			canvas.drawArc(rhead, 0, 300, true, paint);
			
			//眼睛
			paint.setColor(Color.WHITE);
			canvas.drawCircle(135, 53, 4, paint);
			canvas.drawCircle(175, 53, 4, paint);
			
			//天线
			paint.setColor(0xffa4c739);
			canvas.drawLine(120, 15, 135, 35, paint);
			canvas.drawLine(190, 15, 175, 35, paint);
			
			//身体
			canvas.drawRect(110, 75, 200, 150, paint);
			RectF body = new RectF(110, 140, 200, 160);
			canvas.drawRoundRect(body, 10, 10, paint);//上面矩形的下边沿是此圆角矩形的分割线，圆角矩形上半部分与矩形重叠，只显示下半部分出来
			
			//胳膊
			RectF arm = new RectF(85,75,105,140);
			canvas.drawRoundRect(arm, 10, 10, paint);
			arm.offset(120, 0);
			canvas.drawRoundRect(arm, 10, 10, paint);
			
			//腿
			RectF leg=new RectF(125,150,145,200);
			canvas.drawRoundRect(leg, 10, 10, paint);
			leg.offset(40, 0);
			canvas.drawRoundRect(leg, 10, 10, paint);	
			
			
			super.onDraw(canvas);
		}
	}
}
