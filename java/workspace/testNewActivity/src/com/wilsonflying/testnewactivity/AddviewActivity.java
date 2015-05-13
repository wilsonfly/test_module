package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddviewActivity extends Activity {

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);

		//参加5.2，显示该activity的时候大小怎样能调小，而不满屏？？
		LinearLayout ll = new LinearLayout(AddviewActivity.this);
		ll.setPadding(30, 20, 30, 20);

		TextView tv = new TextView(AddviewActivity.this);
		tv.setTextSize(25);
		tv.setText("在AddviewActivity中显示的信息");
		ll.addView(tv);
		setContentView(ll);
	}
}
