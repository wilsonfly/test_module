package com.wilsonflying.testservice4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		StringBuilder serviceInfo = new StringBuilder();
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		
		List<RunningServiceInfo> services = manager.getRunningServices(20);
		
		for(Iterator<RunningServiceInfo> it = services.iterator(); it.hasNext(); ){
			RunningServiceInfo info = it.next();
			serviceInfo.append("activeSince:"+formatData(info.activeSince)+"\n");
            serviceInfo.append("clientCount: " + info.clientCount + "\n");
            serviceInfo.append("clientLabel: " + info.clientLabel + "\n");
            serviceInfo.append("clientPackage: " + info.clientPackage + "\n");
            serviceInfo.append("crashCount: " + info.crashCount + "\n");
            serviceInfo.append("flags: " + info.flags + "\n");
            serviceInfo.append("foreground: " + info.foreground + "\n");
            serviceInfo.append("lastActivityTime: " + formatData(info.lastActivityTime) + "\n");
            serviceInfo.append("pid: " + info.pid + "\n");
            serviceInfo.append("process: " + info.process + "\n");
            serviceInfo.append("restarting: " + formatData(info.restarting) + "\n");
            serviceInfo.append("service: " + info.service + "\n");
            serviceInfo.append("started: " + info.started + "\n");
            serviceInfo.append("uid: " + info.uid + "\n");
            serviceInfo.append("\n");
		}
		
		ScrollView scrollView = new ScrollView(this);
		TextView tv = new TextView(this);
		tv.setText(serviceInfo.toString());
		scrollView.addView(tv);
		
		setContentView(scrollView);
	}
	
	private String formatData(long data){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		return dateFormat.format(new Date(data));
	}
}
