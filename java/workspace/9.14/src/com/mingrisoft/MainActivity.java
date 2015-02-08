package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
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
			Paint paint=new Paint();							// ����һ������
			paint.setAntiAlias(true);	//ʹ�ÿ���ݹ���
			Bitmap bitmap_bg=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.background);
			canvas.drawBitmap(bitmap_bg, 0, 0, paint);	//���Ʊ���
			
			RectF rect=new RectF(0,0,280,180);
			canvas.translate(40, 20);		//��������X����ƽ��40���أ���Y����ƽ��20����
			
//			//ΪͼƬ������
			paint.setStyle(Style.STROKE);		//���������ʽΪ���
			paint.setColor(Color.BLACK);		//������ɫΪ��ɫ
			paint.setStrokeWidth(2);			//���ñʴ����Ϊ2����
			canvas.drawRoundRect(rect, 10, 10, paint);	//����һ����ߵ�Բ�Ǿ���
			paint.setStyle(Style.FILL);		//���������ʽΪ���
			Bitmap bm=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.img02);
//			//����һ����ˮƽ�������ظ����ڴ�ֱ�����Ͼ����BitmapShader����
			BitmapShader bs= new BitmapShader(bm,TileMode.REPEAT,TileMode.MIRROR);	
			paint.setShader(bs);	//������Ⱦ����
			canvas.drawRoundRect(rect, 10, 10, paint);	//����һ��ʹ��BitmapShader��Ⱦ��Բ�Ǿ���ͼƬ


			
			super.onDraw(canvas);
		}
	}
}
