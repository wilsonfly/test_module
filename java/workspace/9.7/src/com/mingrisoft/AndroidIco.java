package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class AndroidIco extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.frameLayout1);	//��ȡ�����ļ��е�֡���ֹ�����
		ll.addView(new MyView(this));	//���Զ�����ͼ��ӵ�֡���ֹ�������
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();	//����Ĭ�����ô���һ������
			paint.setAntiAlias(true);	//ʹ�ÿ���ݹ���
			paint.setColor(0xFFA4C739);	//���û��ʵ���ɫΪ��ɫ
			//���ƻ����˵�ͷ
			RectF rectf_head=new RectF(10, 10, 100, 100);
			rectf_head.offset(100, 20);
			canvas.drawArc(rectf_head, -10, -160, false, paint);	//���ƻ�
			//�����۾�
			paint.setColor(Color.WHITE);	//���û��ʵ���ɫΪ��ɫ
			canvas.drawCircle(135, 53, 4, paint);	//����Բ
			canvas.drawCircle(175, 53, 4, paint);	//����Բ
			paint.setColor(0xFFA4C739);	//���û��ʵ���ɫΪ��ɫ
			//��������
			paint.setStrokeWidth(2);	//���ñʴ��Ŀ��
			canvas.drawLine(120, 15, 135, 35, paint);	//������
			canvas.drawLine(190, 15, 175, 35, paint);	//������
			//��������
			canvas.drawRect(110, 75, 200, 150, paint);	//���ƾ���
			RectF rectf_body=new RectF(110,140,200,160);
			canvas.drawRoundRect(rectf_body, 10, 10, paint);	//����Բ�Ǿ���
			//���Ƹ첲
			RectF rectf_arm=new RectF(85,75,105,140);
			canvas.drawRoundRect(rectf_arm, 10, 10, paint);	//�������ĸ첲
			rectf_arm.offset(120, 0);							//������X����ƫ��120����
			canvas.drawRoundRect(rectf_arm, 10, 10, paint);	//�����Ҳ�ĸ첲	
			//������
			RectF rectf_leg=new RectF(125,150,145,200);
			canvas.drawRoundRect(rectf_leg, 10, 10, paint);	//����������
			rectf_leg.offset(40, 0);							//������X����ƫ��40����
			canvas.drawRoundRect(rectf_leg, 10, 10, paint);	//�����Ҳ����				
			super.onDraw(canvas);
		}
	}
}
