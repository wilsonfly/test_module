package com.wilsonflying.widget;

import com.wilsonflying.testcustomedwidget.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LabelEditText extends LinearLayout {

	private String labelText;
	private TextView textView;
	private int labelFontSize;
	private String labelPosition;
	
	public LabelEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		//先尝试获取labelText的资源id
		int labelTextId = attrs.getAttributeResourceValue(null, "labelText", 0);
		if(labelTextId == 0){
			labelText = attrs.getAttributeValue(null, "labelText");//没有资源，从属性中读取
		}else{
			labelText = getResources().getString(labelTextId);//有资源，根据资源id读取
		}
		if( labelText == null ){
			throw new RuntimeException("需要设置labelText");
		}
		
		int labelFontSizeId = attrs.getAttributeResourceValue(null, "labelFontSize", 0);
		if(labelFontSizeId == 0){
			labelFontSize = attrs.getAttributeIntValue(null, "labelFontSize", 14);
		}else{
			labelFontSize = getResources().getInteger(labelFontSizeId);
		}
		
		int labelPositionId = attrs.getAttributeResourceValue(null, "labelPosition", 0);
		if(labelPositionId == 0){
			labelPosition = attrs.getAttributeValue(null, "labelPosition");
		}else{
			labelPosition = getResources().getString(labelPositionId);
		}
		if(labelPosition == null){
			labelPosition = "left";
		}
		
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout ll = null;
		if("left".equals(labelPosition)){
			ll = (LinearLayout) li.inflate(R.layout.labeledittext_h, this);
		}else if("top".equals(labelPosition)){
			ll = (LinearLayout) li.inflate(R.layout.labeledittext_v, this);
		}else{
			throw new RuntimeException("只支持labelPositon为left或者top");
		}
		
		textView = (TextView) ll.findViewById(R.id.tv);//ll这个布局中查找id
		textView.setTextSize(labelFontSize);
		textView.setText(labelText);
	}

}
