package com.mingrisoft;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);
		ll.addView(new MyView(this));
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();			//定义一个默认的画笔
			//线性渐变
			Shader shader=new LinearGradient(0, 0, 50, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
			paint.setShader(shader);	//为画笔设置渐变器	
			canvas.drawRect(10, 70, 100, 150, paint);	//绘制矩形
			//径向渐变
			shader=new RadialGradient(160, 110, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
			paint.setShader(shader);	//为画笔设置渐变器	
			canvas.drawRect(115,70,205,150, paint);	//绘制矩形
			//角度渐变
			shader=new SweepGradient(265,110,new int[]{Color.RED,Color.GREEN,Color.BLUE},null);
			paint.setShader(shader);
			canvas.drawRect(220, 70, 310, 150, paint);	//绘制矩形

			super.onDraw(canvas);
		}
		
		
	}
}