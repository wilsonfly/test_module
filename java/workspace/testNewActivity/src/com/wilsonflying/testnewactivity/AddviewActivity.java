package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddviewActivity extends Activity {

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);

		//�μ�5.2����ʾ��activity��ʱ���С�����ܵ�С��������������
		LinearLayout ll = new LinearLayout(AddviewActivity.this);
		ll.setPadding(30, 20, 30, 20);

		TextView tv = new TextView(AddviewActivity.this);
		tv.setTextSize(25);
		tv.setText("��AddviewActivity����ʾ����Ϣ");
		ll.addView(tv);
		setContentView(ll);
	}
}
