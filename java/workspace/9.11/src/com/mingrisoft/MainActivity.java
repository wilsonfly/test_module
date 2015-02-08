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
			paint.setAntiAlias(true);
			Bitmap bitmap_bg=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.background);
			canvas.drawBitmap(bitmap_bg, 0, 0, paint);		// 绘制背景

			Bitmap bitmap_rabbit=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.rabbit);
			//应用setSkew(float sx, float sy)方法倾斜图像
			Matrix matrix=new Matrix();
			matrix.setSkew(2f, 1f);			// 以（0,0）点为轴心将图像在X轴上倾斜2，在Y轴上倾斜1
			canvas.drawBitmap(bitmap_rabbit, matrix, paint);	// 绘制图像并应用matrix的变换
			//应用setSkew(float sx, float sy, float px, float py) 方法倾斜图像
			Matrix m=new Matrix();
			m.setSkew(-0.5f, 0f,78,69);			// 以（78,69）点为轴心将图像在X轴上倾斜-0.5
			canvas.drawBitmap(bitmap_rabbit, m, paint);		// 绘制图像并应用matrix的变换
			canvas.drawBitmap(bitmap_rabbit, 0, 0, paint);		// 绘制原图

			
			super.onDraw(canvas);
		}
	}
}
