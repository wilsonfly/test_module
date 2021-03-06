package com.wilsonflying.responser;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private final String TAG = "testInstallApk";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//============getRunningTask=============//
		//获取到调用者的包名，需要在onCreate中方能拿到
		try {
			ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
			List<RunningTaskInfo> tasksInfos = manager.getRunningTasks(3);
			if (tasksInfos.size() > 0) {
				for (RunningTaskInfo tasksInfo : tasksInfos) {
					Log.d(TAG, "getRunning pkgname:"+tasksInfo.topActivity.getPackageName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//============getRunningTask=============//

		
		//============getCallingUid=============//
		int uid = Binder.getCallingUid();
		PackageManager pm = getPackageManager();
		String [] pkgNames = pm.getPackagesForUid(uid);
		
		for (String str : pkgNames) {
			Log.d(TAG, "getCallingUid pkgname:"+str);
		}
		//============getCallingUid=============//

	}

	public void onClickBtn(View view){
		switch (view.getId()) {
		case R.id.btnInstall:
			File fileSd = Environment.getExternalStorageDirectory();
			String strApk = fileSd.toString()+"/Android/test.apk";
			
			Intent intent = new Intent(Intent.ACTION_VIEW); //普通安装方式
//			Intent intent = new Intent("android.intent.action.VIEW.HIDE"); //静默安装方式
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
		//	intent.setDataAndType(Uri.parse(strApk), "application/vnd.android.package-archive");
			intent.setDataAndType(Uri.fromFile(new File(strApk)), "application/vnd.android.package-archive");
			startActivity(intent);
			break;

		case R.id.btnGetCaller:
			int uid = Binder.getCallingUid();
			PackageManager pm = getPackageManager();
			String [] pkgNames = pm.getPackagesForUid(uid);
			
			for (String str : pkgNames) {
				Log.d(TAG, "pkgname:"+str);
			}
			Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>");

			break;
			
		case R.id.btnGetStack:
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			for (StackTraceElement stackTraceElement : stack) {
				Log.d(TAG, "name:"+ stackTraceElement);
			}
			Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>");

			break;
			
		case R.id.btnGetRunning:
			int pidTarget = Binder.getCallingPid();
			ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			for (ActivityManager.RunningAppProcessInfo appInfo : am.getRunningAppProcesses()) {
				Log.d(TAG, "appName:"+appInfo.processName + " pid:"+appInfo.pid);
			}
			Log.d(TAG, ">>>>>>>>>>>>>>>>>>>>>>");

			break;
		default:
			break;
		}

		
	}

}
