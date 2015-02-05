package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll = (FrameLayout) findViewById(R.id.frameLayout1); // 获取布局文件中的帧布局管理器
		ll.addView(new MyView(this)); // 将自定义视图添加到帧布局管理器中

	}

	public class MyView extends View {
		private Bitmap bitmap; // 源图像，也就是背景图像
		private ShapeDrawable drawable;

		private final int RADIUS = 57; // 探照灯的半径

		private Matrix matrix = new Matrix();


		public MyView(Context context) {
			super(context);
			Bitmap bitmap_source = BitmapFactory.decodeResource(getResources(),
					R.drawable.source);	//获取要显示的源图像
			bitmap = bitmap_source;
			BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(
					bitmap_source, bitmap_source.getWidth(),
					bitmap_source.getHeight(), true), TileMode.CLAMP,
					TileMode.CLAMP);	//创建BitmapShader对象
			
			// 圆形的drawable
			drawable = new ShapeDrawable(new OvalShape());
			drawable.getPaint().setShader(shader);
			drawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2); // 设置圆的外切矩形
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Paint p=new Paint();
			p.setAlpha(50);
			canvas.drawBitmap(bitmap, 0,0, p); // 绘制背景图像
			drawable.draw(canvas); // 绘制探照灯照射的图像
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			final int x = (int) event.getX(); // 获取当前触摸点的X轴坐标
			final int y = (int) event.getY(); // 获取当前触摸点的Y轴坐标
			matrix.setTranslate(RADIUS - x , RADIUS - y ); // 平移到绘制shader的起始位置
			drawable.getPaint().getShader().setLocalMatrix(matrix);
			drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS); // 设置圆的外切矩形
			invalidate(); // 重绘画布
			return true;
		}
	}
}
