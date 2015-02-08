package com.mingrisoft;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.linearLayout1);
		ll.addView(new MyView(this));
	
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();	//创建一个画笔
			paint.setAntiAlias(true);	//设置使用抗锯齿功能
			paint.setColor(0xFFFF6600);	//设置画笔颜色
			paint.setTextSize(18);	//设置文字大小
			paint.setStyle(Style.STROKE);	//设置填充方式为描边
			//绘制圆形路径
			Path pathCircle=new Path();//创建并实例化一个path对象
			pathCircle.addCircle(70, 70, 40, Path.Direction.CCW);	//添加逆时针的圆形路径
			canvas.drawPath(pathCircle, paint);	//绘制路径
			//绘制折线路径
			Path pathLine=new Path();		//创建并实例化一个Path对象
			pathLine.moveTo(150, 100);		//设置起始点
			pathLine.lineTo(200, 45);		//设置第一段直线的结束点
			pathLine.lineTo(250, 100);		//设置第二段直线的结束点
			pathLine.lineTo(300, 80);		//设置第3段直线的结束点
			canvas.drawPath(pathLine, paint);	//绘制路径
			//绘制三角形路径
			Path pathTr=new Path();	//创建并实例化一个path对象
			pathTr.moveTo(350,80);	//设置起始点
			pathTr.lineTo(400, 30);	//设置第一条边的结束点，也是第二条边的起始点
			pathTr.lineTo(450, 80);	//设置第二条边的结束点，也是第3条边的起始点
			pathTr.close();			//闭合路径
			canvas.drawPath(pathTr, paint);	//绘制路径
			//绘制绕路径的环形文字
			String str="风萧萧兮易水寒，壮士一去兮不复还";
			Path path=new Path();		//创建并实例化一个path对象
			path.addCircle(550, 100, 48, Path.Direction.CW);		//添加顺时针的圆形路径		
			paint.setStyle(Style.FILL);//设置画笔的填充方式
			canvas.drawTextOnPath(str, path,0, -18, paint);	//绘制绕路径文字
			super.onDraw(canvas);
		}
		
		
	}
}