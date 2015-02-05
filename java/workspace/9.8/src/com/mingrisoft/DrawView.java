package com.mingrisoft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	private int view_width = 0;	//��Ļ�Ŀ��
	private int view_height = 0;	//��Ļ�ĸ߶�
	private float preX;	//��ʼ���x����ֵ
	private float preY;//��ʼ���y����ֵ
	private Path path;	//·��
	public Paint paint = null;	//����
	Bitmap cacheBitmap = null;// ����һ���ڴ��е�ͼƬ����ͼƬ����Ϊ������
	Canvas cacheCanvas = null;// ����cacheBitmap�ϵ�Canvas����

	public DrawView(Context context, AttributeSet set) {
		super(context, set);
		view_width = context.getResources().getDisplayMetrics().widthPixels; // ��ȡ��Ļ�Ŀ��
		view_height = context.getResources().getDisplayMetrics().heightPixels; // ��ȡ��Ļ�ĸ߶�
		System.out.println(view_width + "*" + view_height);
		// ����һ�����View��ͬ��С�Ļ�����
		cacheBitmap = Bitmap.createBitmap(view_width, view_height,
				Config.ARGB_8888);
		cacheCanvas = new Canvas();
		path = new Path();
		cacheCanvas.setBitmap(cacheBitmap);// ��cacheCanvas�ϻ���cacheBitmap
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setColor(Color.RED); // ����Ĭ�ϵĻ�����ɫ
		// ���û��ʷ��
		paint.setStyle(Paint.Style.STROKE);	//������䷽ʽΪ���
		paint.setStrokeJoin(Paint.Join.ROUND);		//���ñ�ˢ��ͼ����ʽ
		paint.setStrokeCap(Paint.Cap.ROUND);	//���û���ת�䴦�����ӷ��
		paint.setStrokeWidth(1); // ����Ĭ�ϱʴ��Ŀ��Ϊ1����
		paint.setAntiAlias(true); // ʹ�ÿ���ݹ���
		paint.setDither(true); // ʹ�ö���Ч��
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(0xFFFFFFFF);	//���ñ�����ɫ
		Paint bmpPaint = new Paint();	//����Ĭ�����ô���һ������
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint); //����cacheBitmap
		canvas.drawPath(path, paint);	//����·��
		canvas.save(Canvas.ALL_SAVE_FLAG);	//����canvas��״̬
		canvas.restore();	//�ָ�canvas֮ǰ�����״̬����ֹ������canvasִ�еĲ����Ժ����Ļ�����Ӱ��	
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ��ȡ�����¼��ķ���λ��
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y); // ����ͼ����ʼ���Ƶ���x,y��������λ��
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			float dx = Math.abs(x - preX);
			float dy = Math.abs(y - preY);
			if (dx >= 5 || dy >= 5) { // �ж��Ƿ�������ķ�Χ��
				path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
				preX = x;
				preY = y;
			}
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint); //����·��
			path.reset();
			break;
		}
		invalidate();
		return true;		// ����true�����������Ѿ�������¼�
	}


	public void clear() {
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		paint.setStrokeWidth(50);	//���ñʴ��Ŀ��
	}

	public void save() {
		try {
			saveBitmap("myPicture");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ������ƺõ�λͼ
	public void saveBitmap(String fileName) throws IOException {
		File file = new File("/sdcard/pictures/" + fileName + ".png");	//�����ļ�����
		file.createNewFile();	//����һ�����ļ�
		FileOutputStream fileOS = new FileOutputStream(file);	//����һ���ļ����������
		cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOS);	//����ͼ����ѹ��ΪPNG��ʽ����������������
		fileOS.flush();	//���������е�����ȫ��д�����������
		fileOS.close();	//�ر��ļ����������

	}

}
