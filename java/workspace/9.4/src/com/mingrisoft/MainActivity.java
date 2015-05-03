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
			Paint paintText=new Paint();		//����һ������Ĭ�����õĻ���
			paintText.setColor(0xFFFF6600);		//���û�����ɫ
			paintText.setTextAlign(Align.LEFT);	//�������������
			paintText.setTextSize(24);		//�������ִ�С
			paintText.setAntiAlias(true);	//ʹ�ÿ���ݹ���
			canvas.drawText("�����Ҳ���ȥ��", 520,75, paintText);	//ͨ��drawText()������������
			float[] pos= new float[]{400,260, 425,260, 450,260, 475,260,
					363,290, 388,290, 413,290, 438,290, 463,290, 488,290, 513,290};	//�����������λ�õ�����
			canvas.drawPosText("�������һ��ȥ̽����", pos, paintText);			//ͨ��drawPosText()������������
			super.onDraw(canvas);
		}
		
		
	}
}