package com.wilsonflying.testsurfaceview;

import android.app.Activity;
import android.os.Bundle;

public class ShowViewNo1Aty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MyViewNO1(this));
	}
}
