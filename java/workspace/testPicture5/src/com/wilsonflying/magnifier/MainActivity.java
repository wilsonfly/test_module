package com.wilsonflying.magnifier;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
	
	public class MyView extends View{

		private Bitmap bitmap_source;
		private Bitmap bitmap_magnifier;
		private int FACTOR = 2;
		private int RADIUS = 60;
		private ShapeDrawable drawable;
		private int m_left = 0;
		private int m_top = 0;
		private Matrix matrix = new Matrix();
		
		
		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
//			bitmap_source = new BitmapFactory().decodeResource(getResources(), R.drawable.pic_code);
			bitmap_source = new BitmapFactory().decodeResource(getResources(), R.drawable.source);
			bitmap_magnifier = new BitmapFactory().decodeResource(getResources(), R.drawable.magnifier);
			
			Bitmap bitmap = Bitmap.createScaledBitmap(bitmap_source, bitmap_source.getWidth()*FACTOR, bitmap_source.getHeight()*FACTOR, true);
			BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
			
			drawable = new ShapeDrawable(new OvalShape());
			drawable.getPaint().setShader(shader);
			drawable.setBounds(0, 0, RADIUS*2, RADIUS*2);
			m_left = -(bitmap_magnifier.getWidth()/2 - RADIUS);//以放大镜的圆圈在左上角为基础，计算放大镜左边位置，上边位置
			m_top = -(bitmap_magnifier.getHeight()/2 - RADIUS);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			canvas.drawBitmap(bitmap_source, 0, 0, null);
			canvas.drawBitmap(bitmap_magnifier, m_left, m_top, null);
			drawable.draw(canvas); 
			
			super.onDraw(canvas);
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
//			return super.onTouchEvent(event);
			int x = (int) event.getX();
			int y = (int) event.getY();
			Log.i("huasheng", "x:"+x+" RADIUS-x*FACTORY:"+(RADIUS-x*FACTOR));
			matrix.setTranslate( -(x*FACTOR-RADIUS), -(y*FACTOR-RADIUS));//matrix图片已经放大FACTOR倍，其x、y也相应放大，将选中图像移动回现有视野范围
			drawable.getPaint().getShader().setLocalMatrix(matrix);
			drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);//显示图像的位置
			m_left = x - bitmap_magnifier.getWidth() / 2;
			m_top = y - bitmap_magnifier.getHeight() / 2;
			invalidate();
			return true;
		}
	}
	
}
