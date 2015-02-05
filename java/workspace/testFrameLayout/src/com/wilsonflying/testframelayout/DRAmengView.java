package com.wilsonflying.testframelayout;



import com.wilsonflying.testframelayout.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DRAmengView extends View {

	public float bitmapX;
	public float bitmapY;
	
	public DRAmengView(Context context){
		super(context);
		bitmapX = 100;
		bitmapY = 100;
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Paint paint = new Paint();
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img001);
		canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);
		if(bitmap.isRecycled()){
			bitmap.recycle();
		}
		
	}
}
