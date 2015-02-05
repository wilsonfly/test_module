package com.mingrisoft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll = (FrameLayout) findViewById(R.id.frameLayout1); // ��ȡ�����ļ�����ӵ�֡���ֹ�����
		ll.addView(new MyView(this)); // ���Զ�����ͼ��ӵ�֡���ֹ�������
		iv = (ImageView) findViewById(R.id.imageView1); // ��ȡ�����ļ�����ӵ�ImageView���

	}

	@Override
	protected void onDestroy() {
		BitmapDrawable b = (BitmapDrawable) iv.getDrawable(); // ��ȡImageView�����ʹ�õ�BitmapDrawabele��Դ
		if (b != null && !b.getBitmap().isRecycled()) {
			b.getBitmap().recycle(); // ������Դ
		}
		super.onDestroy();
	}

	public class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint = new Paint(); // ����һ������Ĭ�����õĻ���
			String path = "/sdcard/pictures/bccd/img01.png"; // ָ��ͼƬ�ļ���·��
			Bitmap bm = BitmapFactory.decodeFile(path); // ��ȡͼƬ�ļ���Ӧ��Bitmap����
			canvas.drawBitmap(bm, 0, 30, paint); // ����ȡ��Bitmap��������ڻ�����ָ��λ��
			Rect src = new Rect(95, 150, 175, 240); // ������ȡ������
			Rect dst = new Rect(420, 30, 500, 120); // ���û��Ƶ�����
			canvas.drawBitmap(bm, src, dst, paint); // ������ȡ����ͼ��

			Bitmap bitmap = Bitmap.createBitmap(new int[] { Color.RED,
					Color.GREEN, Color.BLUE, Color.MAGENTA }, 4, 1,
					Config.RGB_565); // ʹ����ɫ���鴴��һ��Bitmap����
			iv.setImageBitmap(bitmap);	//ΪImageViewָ��Ҫ��ʾ��λͼ

			super.onDraw(canvas);
		}

	}
}