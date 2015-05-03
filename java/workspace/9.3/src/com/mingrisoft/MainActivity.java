package com.mingrisoft;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);	//获取布局文件中添加的帧布局管理器
		ll.addView(new MyView(this));	//将自定义的MyView视图添加到帧布局管理器中
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.WHITE);
			Paint paint=new Paint();		//创建采用默认设置的画笔
			paint.setAntiAlias(true);		//使用抗锯齿功能
			paint.setStrokeWidth(3);		//设置笔触的宽度
			paint.setStyle(Style.STROKE);	//设置填充样式为描边
			paint.setColor(Color.BLUE);
			canvas.drawCircle(50, 50, 30, paint);	//绘制蓝色的圆形
			paint.setColor(Color.YELLOW);
			canvas.drawCircle(100, 50, 30, paint);	//绘制黄色的圆形
			paint.setColor(Color.BLACK);
			canvas.drawCircle(150, 50, 30, paint);	//绘制黑色的圆形
			paint.setColor(Color.GREEN);
			canvas.drawCircle(75, 90, 30, paint);	//绘制绿色的圆形
			paint.setColor(Color.RED);
			canvas.drawCircle(125, 90, 30, paint);	//绘制红色的圆形
			
			
			/**************以下代码为表9.2中使用的代码*************************/
			//绘制弧
//			RectF rectf=new RectF(10, 20, 100, 110);
//			canvas.drawArc(rectf, 0, 60, true, paint);
//			RectF rectf1=new RectF(110, 20, 200, 110);
//			canvas.drawArc(rectf1, 0, 60, false, paint);
			//绘制圆形
//			canvas.drawCircle(50, 50, 15, paint);
			//绘制一条线
//			paint.setStyle(Style.FILL);
//			canvas.drawLine(100, 10, 150, 10, paint);
			//绘制多条线
//			canvas.drawLines(new float[]{10,10, 30,10, 30,10, 15,30, 15,30, 10,10},  paint);
			//绘制一个点
//			canvas.drawPoint(10, 10, paint);
			//绘制椭圆
//			RectF rectf=new RectF(40, 20, 80, 40);
//			canvas.drawOval(rectf,paint);
			//绘制多个点
//			canvas.drawPoints(new float[]{10,10, 15,10, 20,15, 25,10, 30,10}, paint);
			//绘制矩形
//			canvas.drawRect(10, 10, 40, 30, paint);
//			paint.setXfermode(new PixelXorXfermode(Color.BLUE));
			
			//绘制圆角矩形
//			RectF rectf=new RectF(40, 20, 80, 40);
//			canvas.drawRoundRect(rectf, 6, 6, paint);
//			paint.setStyle(Style.FILL);

			super.onDraw(canvas);
		}
		
		
	}
}