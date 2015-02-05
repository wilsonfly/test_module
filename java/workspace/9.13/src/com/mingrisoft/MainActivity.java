package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private int view_width;
	private int view_height;
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
			view_width = context.getResources().getDisplayMetrics().widthPixels; 		// ��ȡ��Ļ�Ŀ��
			view_height = context.getResources().getDisplayMetrics().heightPixels;		// ��ȡ��Ļ�ĸ߶�
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();							// ����һ������
			paint.setAntiAlias(true);	//ʹ�ÿ���ݹ���
			Bitmap bitmap_bg=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.android);
			//����һ����ˮƽ�ʹ�ֱ�����ظ���BitmapShader����
			BitmapShader bitmapshader= new BitmapShader(bitmap_bg,TileMode.REPEAT,TileMode.REPEAT);
			paint.setShader(bitmapshader);	//������Ⱦ����
			canvas.drawRect(0, 0, view_width, view_height, paint);		//����һ��ʹ��BitmapShader��Ⱦ�ľ���	
			
			Bitmap bm=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.img02);
			//����һ����ˮƽ�������ظ����ڴ�ֱ�����Ͼ����BitmapShader����
			BitmapShader bs= new BitmapShader(bm,TileMode.REPEAT,TileMode.MIRROR);	
			paint.setShader(bs);	//������Ⱦ����
			RectF oval=new RectF(0,0,280,180);
			canvas.translate(40, 20);		//��������X����ƽ��40���أ���Y����ƽ��20����
			canvas.drawOval(oval, paint);	//����һ��ʹ��BitmapShader��Ⱦ����Բ��
			
			super.onDraw(canvas);
		}
	}
}
