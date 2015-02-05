package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);	//获取布局文件中的帧布局管理器
		ll.addView(new MyView(this));	//将自定义视图添加到帧布局管理器中
		
		
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();							// 定义一个画笔
			paint.setAntiAlias(true);	//使用抗锯齿功能
			Bitmap bitmap_bg=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.background);
			canvas.drawBitmap(bitmap_bg, 0, 0, paint);	//绘制背景
			
			RectF rect=new RectF(0,0,280,180);
			canvas.translate(40, 20);		//将画面在X轴上平移40像素，在Y轴上平移20像素
			
//			//为图片添加描边
			paint.setStyle(Style.STROKE);		//设置填充样式为描边
			paint.setColor(Color.BLACK);		//设置颜色为黑色
			paint.setStrokeWidth(2);			//设置笔触宽度为2像素
			canvas.drawRoundRect(rect, 10, 10, paint);	//绘制一个描边的圆角矩形
			paint.setStyle(Style.FILL);		//设置填充样式为填充
			Bitmap bm=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.img02);
//			//创建一个在水平方向上重复，在垂直方向上镜像的BitmapShader对象
			BitmapShader bs= new BitmapShader(bm,TileMode.REPEAT,TileMode.MIRROR);	
			paint.setShader(bs);	//设置渲染对象
			canvas.drawRoundRect(rect, 10, 10, paint);	//绘制一个使用BitmapShader渲染的圆角矩形图片


			
			super.onDraw(canvas);
		}
	}
}
