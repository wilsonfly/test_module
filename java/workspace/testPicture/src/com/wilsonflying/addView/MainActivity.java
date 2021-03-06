package com.wilsonflying.addView;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		fl.addView(new MyView(this));
	}
	
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			
			/*******************渐变颜色*******************/
			Paint paint = new Paint();
			//线性渐变
			Shader shader = new LinearGradient(0, 0, 50, 50, Color.GREEN, Color.RED, Shader.TileMode.MIRROR);//两点组成直线方向
			paint.setShader(shader);
			canvas.drawRect(10, 10, 100, 100, paint);
			
			//径向渐变
			shader = new RadialGradient(160, 55, 30, Color.GREEN, Color.RED, Shader.TileMode.MIRROR);//以x,y为圆心，radius为半径
			paint.setShader(shader);
			canvas.drawRect(115, 10, 205, 100, paint);
			
			//角度渐变
			shader = new SweepGradient(265, 55, new int[]{Color.GREEN, Color.RED, Color.BLUE}, null);//以x,y为圆心
			paint.setShader(shader);
			canvas.drawRect(220, 10, 310, 100, paint);
			/*******************渐变颜色*******************/
			
			/*******************五环*******************/
			paint.reset();
			paint.setAntiAlias(true);
			paint.setStrokeWidth(3);
			paint.setStyle(Style.STROKE);
			
			paint.setColor(Color.BLUE);
			canvas.drawCircle(50+400, 150-100, 30, paint);
			paint.setColor(Color.YELLOW);
			canvas.drawCircle(100+400, 150-100, 30, paint);
			paint.setColor(Color.BLACK);
			canvas.drawCircle(150+400, 150-100, 30, paint);
			paint.setColor(Color.GREEN);
			canvas.drawCircle(75+400, 190-100, 30, paint);
			paint.setColor(Color.RED);
			canvas.drawCircle(125+400, 190-100, 30, paint);
			/*******************五环*******************/

			/*******************drawText*******************/
			paint.reset();
			paint.setAntiAlias(true);
			paint.setColor(Color.BLUE);
			paint.setTextSize(20);
			paint.setTextAlign(Align.LEFT);
			
			canvas.drawText("麦小兜", 600, 20, paint);
			float[] pos = new float[]{725,20, 750,40, 775,60, 800,80};
			canvas.drawPosText("麦小兜！", pos, paint);
			/*******************drawText*******************/

			/*******************Path*******************/
			paint.reset();
			paint.setAntiAlias(true);
			paint.setStrokeWidth(3);
			paint.setTextSize(20);
			paint.setStyle(Style.STROKE);//描边，否则为填充
			paint.setColor(Color.DKGRAY);
			
			//画个圆圈
			Path path = new Path();
			path.addCircle(50, 180, 30, Direction.CW);
//			canvas.drawPath(path, paint);
			
			//几条折线
			path.reset();
			path.moveTo(100, 200);
			path.lineTo(130, 150);
			path.lineTo(160, 190);
			path.lineTo(200, 160);
//			path.close();
			canvas.drawPath(path, paint);
			
			//三角形
			path.reset();
			path.moveTo(220, 200);
			path.lineTo(250, 150);
			path.lineTo(300, 200);
			path.close();
			canvas.drawPath(path, paint);
			
			//环形文字
			paint.reset();
			path.reset();
			paint.setTextSize(15);
			paint.setAntiAlias(true);
			String str="大漠孤烟直，长河落日圆。";
			path.addCircle(400, 180, 45, Direction.CW);//半径太小则字显示不全
			paint.setStyle(Style.FILL);
			canvas.drawTextOnPath(str, path, 20, -18, paint);//hoffset起始位置偏移，voffset线上or线下	
			/*******************Path*******************/

			/*******************Bitmap*******************/
			paint.reset();
//			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.bluesee);

			canvas.drawBitmap(bm, 10, 250, paint);
			Rect src = new Rect(10, 250, 210, 450);
			Rect dst = new Rect(600, 250, 800, 450);
			canvas.drawBitmap(bm, src, dst, paint);//挖取部分，但是位置跟大小跟预期不一致？？
			/*******************Bitmap*******************/

			/*******************Matix,Rotate,Translate,Scale,Skew*******************/
			paint.reset();
			Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			canvas.drawBitmap(bm2, 0, 750, paint);
			Matrix matrix = new Matrix();
			matrix.setRotate(30);
//			matrix.setRotate(30,250,250);
			matrix.postTranslate(200, 750);
			canvas.drawBitmap(bm2, matrix, paint);
			
			//缩放
			matrix.setScale(1.5f, 1.5f);//需要加上f，否则默认为double则报错类型不匹配
			matrix.postTranslate(300, 750);
			canvas.drawBitmap(bm2, matrix, paint);
			
			//平移
			matrix.setTranslate(500, 750);
			canvas.drawBitmap(bm2, matrix, paint);
			
			//倾斜
			matrix.reset();
			matrix.setSkew(1f, 0);
			matrix.postTranslate(700, 750);
			canvas.drawBitmap(bm2, matrix, paint);
//			matrix.setSkew(1.5f, 0, 490, 250);

			/*******************Matix,Rotate,Translate,Scale,Skew*******************/
			
			/*******************BitmapShader*******************/
			paint.reset();
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(2);
			RectF rectf = new RectF(0, 1000, 250, 1200);
			canvas.drawRoundRect(rectf, 10, 10, paint);
			
			paint.reset();
			paint.setStyle(Style.FILL);
			Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			BitmapShader bs = new BitmapShader(bm3, TileMode.REPEAT, TileMode.MIRROR);
			paint.setShader(bs);
			canvas.drawRoundRect(rectf, 10, 10, paint);
			/*******************BitmapShader*******************/

			/*******************BitmapFactory.decodeStream*******************/
			InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inSampleSize = 2;
			Bitmap bm4 = BitmapFactory.decodeStream(is, null, opt);
			canvas.drawBitmap(bm4, 300, 1000, paint);
			
			int w = bm4.getWidth();
			int h = bm4.getHeight();
			int[] pixels = new int[w*h];
			bm4.getPixels(pixels, 0, w, 0, 0, w, h);
			Bitmap bm5 = Bitmap.createBitmap(pixels, w, h, Config.ARGB_8888);
//			Bitmap bm5 = Bitmap.createBitmap(pixels, w, h, Config.ARGB_4444);
			canvas.drawBitmap(bm5, 400, 1000, paint);
			/*******************BitmapFactory.decodeStream*******************/
	
			
			super.onDraw(canvas);
		}
	}
}
