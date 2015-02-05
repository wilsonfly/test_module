package com.mingrisoft;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
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
			Paint paintText=new Paint();		//创建一个采用默认设置的画笔
			paintText.setColor(0xFFFF6600);		//设置画笔颜色
			paintText.setTextAlign(Align.LEFT);	//设置文字左对齐
			paintText.setTextSize(24);		//设置文字大小
			paintText.setAntiAlias(true);	//使用抗锯齿功能
			canvas.drawText("不，我不想去！", 520,75, paintText);	//通过drawText()方法绘制文字
			float[] pos= new float[]{400,260, 425,260, 450,260, 475,260,
					363,290, 388,290, 413,290, 438,290, 463,290, 488,290, 513,290};	//定义代表文字位置的数组
			canvas.drawPosText("你想和我一起去探险吗？", pos, paintText);			//通过drawPosText()方法绘制文字
			super.onDraw(canvas);
		}
		
		
	}
}