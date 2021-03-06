package com.wilsonflying.widget;

import com.wilsonflying.icontext_ext.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconTextExt extends TextView {

//	private final String namespace = "http://com.wilsonflying.icontext";
	private Bitmap bitmap = null;
	private int iconPosition = 0;
	
	public IconTextExt(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
//		int iconId = attrs.getAttributeResourceValue(namespace, "iconSrc", 0);
//		if(iconId > 0 ){
//			bitmap = BitmapFactory.decodeResource(getResources(), iconId);
//		}else{
//			throw new RuntimeException("iconSrc == null");
//		}
		
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IconTextView);
		
		int iconId = array.getResourceId(R.styleable.IconTextView_iconSrc, 0);
		if(iconId > 0){
			bitmap = BitmapFactory.decodeResource(getResources(), iconId);
		}else{
			throw new RuntimeException("iconSrc == null");
		}
		
//		iconPostion = array.getResourceId(R.styleable.IconTextView_iconPosition, 0);
		iconPosition = array.getInt(R.styleable.IconTextView_iconPosition, 0);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Rect src = new Rect();
		Rect dst = new Rect();
		int leftOffset = 0;
		
		src.left = 0;
		src.top = 0;
		src.right = bitmap.getWidth();
		src.bottom = bitmap.getHeight();
		
		//如果是图片在右侧，计算文字宽度作为偏移
		if(iconPosition == 1){
			leftOffset = (int) getPaint().measureText(getText().toString()) + 2;
		}
		System.out.println("iconposition:"+iconPosition);
		System.out.println("leftoffset:"+leftOffset);
		
		dst.left = leftOffset;
		dst.top = (int) ((getHeight()- getTextSize())/2);
		dst.right = (int) (bitmap.getWidth()/bitmap.getHeight() * getTextSize()) + leftOffset;//宽高比例不变，保证图片不变形
		dst.bottom = (int) (dst.top + getTextSize());
		
		canvas.drawBitmap(bitmap, src, dst, getPaint());
		
		//如果图片在左侧，将画布向右偏移一下
		if(iconPosition == 0){
			canvas.translate(dst.right+2, 0);//将画布位置向右移动到到图片右侧，文字再绘制就可以绘制到图片右侧
		}
		super.onDraw(canvas);
		
	}

}
