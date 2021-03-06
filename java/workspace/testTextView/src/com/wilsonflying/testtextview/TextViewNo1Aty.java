package com.wilsonflying.testtextview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextViewNo1Aty extends Activity {

	private TextView[] tvs = new TextView[8];
	private int[] colors = { R.color.color_chi, R.color.color_cheng,
			R.color.color_huang, R.color.color_lv, R.color.color_qing,
			R.color.color_lan, R.color.color_zi, R.color.color_hei };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textviewno1);

		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

		for (int i = 0; i < tvs.length; i++) {
			tvs[i] = new TextView(this);
			tvs[i].setWidth(this.getResources().getDisplayMetrics().widthPixels);
//			tvs[i].setHeight(this.getResources().getDisplayMetrics().heightPixels / tvs.length);
			tvs[i].setHeight(this.getResources().getDisplayMetrics().heightPixels / (tvs.length + 1));// 计算有误差，最后显示不全
			tvs[i].setBackgroundColor(this.getResources().getColor(colors[i]));

			ll.addView(tvs[i]);
		}

	}

}
