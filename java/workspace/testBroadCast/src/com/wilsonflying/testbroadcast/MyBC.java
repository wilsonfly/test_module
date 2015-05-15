package com.wilsonflying.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBC extends BroadcastReceiver {

	public static final String ACTION="com.wilsonflying.testbroadcast.MyBC";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("in onReceive");
		System.out.println("data:"+intent.getStringExtra("info"));
		
	}

}
