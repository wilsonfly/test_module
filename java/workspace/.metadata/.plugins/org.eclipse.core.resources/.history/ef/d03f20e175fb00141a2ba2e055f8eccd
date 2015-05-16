package com.wilsonflying.scrawl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyDrawView extends View {

	private int view_width = 0;
	private int view_height = 0;
	private float preX;
	private float preY;
	private Path path;
	public Paint paint = null;
	Bitmap cacheBitmap = null;
	Canvas cacheCanvas = null;

	public MyDrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		view_width = context.getResources().getDisplayMetrics().widthPixels;
		view_height = context.getResources().getDisplayMetrics().heightPixels;
		Log.i("huasheng", "width*height:" + view_width + "*" + view_height);

		cacheBitmap = Bitmap.createBitmap(view_width, view_height, Config.ARGB_8888);
		cacheCanvas = new Canvas();
		path = new Path();
		cacheCanvas.setBitmap(cacheBitmap);

		paint = new Paint(Paint.DITHER_FLAG);// ??跟setDither作用是否一样
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);// 画笔的图形样式
		paint.setStrokeCap(Paint.Cap.ROUND);// 画笔转弯处的连接风格
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		paint.setDither(true);// 抖动

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(0xFFFFFFFF);
		Paint bmpPaint = new Paint();
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);
		canvas.drawPath(path, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();//save 又 restore??
		
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		return super.onTouchEvent(event);
		
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			float dx = Math.abs(x - preX);
			float dy = Math.abs(y - preY);
			if (dx >= 5 || dy >= 5) {
				path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
				preX = x;
				preY = y;
			}
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint);
			path.reset();
			break;
		}
		invalidate();
		return true;	
	}
	
	public void clear(){
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));//图形重叠时候清除，即实现橡皮擦功能
		paint.setStrokeWidth(10);
	}
	
	public void save() {
		try {
			saveBitmap("myPicture");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveBitmap(String fileName) throws IOException {
		File file = new File("/sdcard/Pictures/" + fileName + ".png");	
		file.createNewFile();
		FileOutputStream fileOS = new FileOutputStream(file);
		cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOS);
		fileOS.flush();
		fileOS.close();
	}
}
