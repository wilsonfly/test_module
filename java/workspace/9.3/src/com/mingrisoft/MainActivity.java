package com.mingrisoft;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);	//��ȡ�����ļ�����ӵ�֡���ֹ�����
		ll.addView(new MyView(this));	//���Զ����MyView��ͼ��ӵ�֡���ֹ�������
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.WHITE);
			Paint paint=new Paint();		//��������Ĭ�����õĻ���
			paint.setAntiAlias(true);		//ʹ�ÿ���ݹ���
			paint.setStrokeWidth(3);		//���ñʴ��Ŀ��
			paint.setStyle(Style.STROKE);	//���������ʽΪ���
			paint.setColor(Color.BLUE);
			canvas.drawCircle(50, 50, 30, paint);	//������ɫ��Բ��
			paint.setColor(Color.YELLOW);
			canvas.drawCircle(100, 50, 30, paint);	//���ƻ�ɫ��Բ��
			paint.setColor(Color.BLACK);
			canvas.drawCircle(150, 50, 30, paint);	//���ƺ�ɫ��Բ��
			paint.setColor(Color.GREEN);
			canvas.drawCircle(75, 90, 30, paint);	//������ɫ��Բ��
			paint.setColor(Color.RED);
			canvas.drawCircle(125, 90, 30, paint);	//���ƺ�ɫ��Բ��
			
			
			/**************���´���Ϊ��9.2��ʹ�õĴ���*************************/
			//���ƻ�
//			RectF rectf=new RectF(10, 20, 100, 110);
//			canvas.drawArc(rectf, 0, 60, true, paint);
//			RectF rectf1=new RectF(110, 20, 200, 110);
//			canvas.drawArc(rectf1, 0, 60, false, paint);
			//����Բ��
//			canvas.drawCircle(50, 50, 15, paint);
			//����һ����
//			paint.setStyle(Style.FILL);
//			canvas.drawLine(100, 10, 150, 10, paint);
			//���ƶ�����
//			canvas.drawLines(new float[]{10,10, 30,10, 30,10, 15,30, 15,30, 10,10},  paint);
			//����һ����
//			canvas.drawPoint(10, 10, paint);
			//������Բ
//			RectF rectf=new RectF(40, 20, 80, 40);
//			canvas.drawOval(rectf,paint);
			//���ƶ����
//			canvas.drawPoints(new float[]{10,10, 15,10, 20,15, 25,10, 30,10}, paint);
			//���ƾ���
//			canvas.drawRect(10, 10, 40, 30, paint);
//			paint.setXfermode(new PixelXorXfermode(Color.BLUE));
			
			//����Բ�Ǿ���
//			RectF rectf=new RectF(40, 20, 80, 40);
//			canvas.drawRoundRect(rectf, 6, 6, paint);
//			paint.setStyle(Style.FILL);

			super.onDraw(canvas);
		}
		
		
	}
}