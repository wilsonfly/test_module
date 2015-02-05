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
		FrameLayout ll = (FrameLayout) findViewById(R.id.frameLayout1); // 获取布局文件中添加的帧布局管理器
		ll.addView(new MyView(this)); // 将自定义视图添加到帧布局管理器中
		iv = (ImageView) findViewById(R.id.imageView1); // 获取布局文件中添加的ImageView组件

	}

	@Override
	protected void onDestroy() {
		BitmapDrawable b = (BitmapDrawable) iv.getDrawable(); // 获取ImageView组件中使用的BitmapDrawabele资源
		if (b != null && !b.getBitmap().isRecycled()) {
			b.getBitmap().recycle(); // 回收资源
		}
		super.onDestroy();
	}

	public class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint = new Paint(); // 创建一个采用默认设置的画笔
			String path = "/sdcard/pictures/bccd/img01.png"; // 指定图片文件的路径
			Bitmap bm = BitmapFactory.decodeFile(path); // 获取图片文件对应的Bitmap对象
			canvas.drawBitmap(bm, 0, 30, paint); // 将获取的Bitmap对象绘制在画布的指定位置
			Rect src = new Rect(95, 150, 175, 240); // 设置挖取的区域
			Rect dst = new Rect(420, 30, 500, 120); // 设置绘制的区域
			canvas.drawBitmap(bm, src, dst, paint); // 绘制挖取到的图像

			Bitmap bitmap = Bitmap.createBitmap(new int[] { Color.RED,
					Color.GREEN, Color.BLUE, Color.MAGENTA }, 4, 1,
					Config.RGB_565); // 使用颜色数组创建一个Bitmap对象
			iv.setImageBitmap(bitmap);	//为ImageView指定要显示的位图

			super.onDraw(canvas);
		}

	}
}