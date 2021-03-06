package com.wilsonflying.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconText extends TextView {

	private final String namespace = "http://com.wilsonflying.icontext";
	Bitmap bitmap = null;
	
	public IconText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		int iconId = attrs.getAttributeResourceValue(namespace, "iconSrc", 0);
		if(iconId > 0 ){
			bitmap = BitmapFactory.decodeResource(getResources(), iconId);
		}else{
			throw new RuntimeException("iconSrc == null");
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Rect src = new Rect();
		Rect dst = new Rect();
		
		src.left = 0;
		src.top = 0;
		src.right = bitmap.getWidth();
		src.bottom = bitmap.getHeight();
		
		dst.left = 0;
		dst.top = (int) ((getHeight()- getTextSize())/2);
		dst.right = (int) (bitmap.getWidth()/bitmap.getHeight() * getTextSize());//宽高比例不变，保证图片不变形
		dst.bottom = (int) (dst.top + getTextSize());
		
		canvas.drawBitmap(bitmap, src, dst, getPaint());
		
		canvas.translate(dst.right+2, 0);//将画布位置向右移动到到图片右侧，文字再绘制就可以绘制到图片右侧
		super.onDraw(canvas);
		
	}

}
