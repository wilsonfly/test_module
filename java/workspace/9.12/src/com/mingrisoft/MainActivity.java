package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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
			canvas.drawBitmap(bitmap_bg, 0, 0, paint);		// 绘制背景

			Bitmap bitmap_rabbit=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.rabbit);
			canvas.drawBitmap(bitmap_rabbit, 0, 0, paint);		// 绘制原图
			Matrix matrix=new Matrix();				// 创建一个Matrix的对象
			matrix.setRotate(30);					// 将matrix旋转30度
			matrix.postTranslate(100,50);			// 将matrix平移到（100,50）的位置
			canvas.drawBitmap(bitmap_rabbit, matrix, paint);	// 绘制图像并应用matrix的变换

			
			super.onDraw(canvas);
		}
	}
}
