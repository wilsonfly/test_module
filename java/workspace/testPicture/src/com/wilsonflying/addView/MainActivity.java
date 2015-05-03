package com.wilsonflying.addView;

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
			Paint paint = new Paint();
			
			//线性渐变
			Shader shader = new LinearGradient(0, 0, 50, 50, Color.GREEN, Color.RED, Shader.TileMode.MIRROR);//两点组成直线方向
			paint.setShader(shader);
			canvas.drawRect(10, 70, 100, 150, paint);
			
			//径向渐变
			shader = new RadialGradient(160, 110, 30, Color.GREEN, Color.RED, Shader.TileMode.MIRROR);//以x,y为圆心，radius为半径
			paint.setShader(shader);
			canvas.drawRect(115, 70, 205, 150, paint);
			
			//角度渐变
			shader = new SweepGradient(265, 110, new int[]{Color.GREEN, Color.RED, Color.BLUE}, null);//以x,y为圆心
			paint.setShader(shader);
			canvas.drawRect(220, 70, 310, 150, paint);
			
			super.onDraw(canvas);
		}
	}
}
