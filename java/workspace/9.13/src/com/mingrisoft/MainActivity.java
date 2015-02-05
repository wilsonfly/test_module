package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private int view_width;
	private int view_height;
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
			view_width = context.getResources().getDisplayMetrics().widthPixels; 		// 获取屏幕的宽度
			view_height = context.getResources().getDisplayMetrics().heightPixels;		// 获取屏幕的高度
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();							// 定义一个画笔
			paint.setAntiAlias(true);	//使用抗锯齿功能
			Bitmap bitmap_bg=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.android);
			//创建一个在水平和垂直方向都重复的BitmapShader对象
			BitmapShader bitmapshader= new BitmapShader(bitmap_bg,TileMode.REPEAT,TileMode.REPEAT);
			paint.setShader(bitmapshader);	//设置渲染对象
			canvas.drawRect(0, 0, view_width, view_height, paint);		//绘制一个使用BitmapShader渲染的矩形	
			
			Bitmap bm=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.img02);
			//创建一个在水平方向上重复，在垂直方向上镜像的BitmapShader对象
			BitmapShader bs= new BitmapShader(bm,TileMode.REPEAT,TileMode.MIRROR);	
			paint.setShader(bs);	//设置渲染对象
			RectF oval=new RectF(0,0,280,180);
			canvas.translate(40, 20);		//将画面在X轴上平移40像素，在Y轴上平移20像素
			canvas.drawOval(oval, paint);	//绘制一个使用BitmapShader渲染的椭圆形
			
			super.onDraw(canvas);
		}
	}
}
