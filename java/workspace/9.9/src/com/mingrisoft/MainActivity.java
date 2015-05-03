package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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
			paint.setAntiAlias(true);
			Bitmap bitmap_bg=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.background);
			canvas.drawBitmap(bitmap_bg, 0, 0, paint);			//���Ʊ���ͼ��
			Bitmap bitmap_rabbit=BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.rabbit);
			canvas.drawBitmap(bitmap_rabbit, 0, 0, paint);		// ����ԭͼ
			//Ӧ��setRotate(float degrees)������תͼ��
			Matrix matrix=new Matrix();
			matrix.setRotate(30);						// �ԣ�0,0����Ϊ����ת��30��
			canvas.drawBitmap(bitmap_rabbit, matrix, paint);	// ����ͼ��Ӧ��matrix�ı任
			//Ӧ��setRotate(float degrees, float px, float py)������תͼ��
			Matrix m=new Matrix();
			m.setRotate(90,87,87);						// �ԣ�87,87����Ϊ����ת��90��
			canvas.drawBitmap(bitmap_rabbit, m, paint);		// ����ͼ��Ӧ��matrix�ı任

			
			super.onDraw(canvas);
		}
	}
}
