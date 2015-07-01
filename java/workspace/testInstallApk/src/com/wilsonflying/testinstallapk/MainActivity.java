package com.wilsonflying.testinstallapk;

import java.io.File;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
//import android.provider.SyncStateContract.Constants;
//import android.provider.SyncStateContract.Constants;

public class MainActivity extends Activity {

	private final String TAG = "testInstallApk-huasheng";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//============getRunningTask=============//
		//获取到调用者的包名，需要在onCreate中方能拿到
		try {
			ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
			List<RunningTaskInfo> tasksInfos = manager.getRunningTasks(2);
			if (tasksInfos.size() > 0) {
				for (RunningTaskInfo tasksInfo : tasksInfos) {
					Log.d(TAG, "pkgname:"+tasksInfo.topActivity.getPackageName());
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
			Log.d(TAG, "pkgname:"+str);
		}
		//============getCallingUid=============//

	}

	@SuppressLint("NewApi")
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

		case R.id.btnInstallSilent:
			int installFlags = 0;
			Uri mPackageURI = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/Android/test.apk"));
			PackageManager pm = getPackageManager();

//			String packageName = "com.dianlv.tv";//布丁电视apk
//			String packageName = "com.wilsonflying.testimageview";//布丁电视apk
//
//			try
//			{
//			    PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
//			    if(pi != null)
//			    {
////			        installFlags |= PackageManager.REPLACE_EXISTING_PACKAGE;
//			        installFlags |= PackageManager.INSTALL_REPLACE_EXISTING;
//			    	Log.w(TAG, "packageInfo != null  ...............................");
//			    }
//			}
//			catch (NameNotFoundException e){
//		    	Log.w(TAG, "NameNotFoundException  ...............................");
//			}

			try{
				IPackageInstallObserver observer = new PackageInstallObserver();
				
				pm.installPackage(mPackageURI, observer, PackageManager.INSTALL_REPLACE_EXISTING, Intent.EXTRA_INSTALLER_PACKAGE_NAME);
				
//				pm.installPackage(mPackageURI, observer, installFlags);
			}catch (Exception e) {
				// TODO: handle exception
		    	Log.w(TAG, "catch Exception, install failed  ...............................");
		    	e.printStackTrace();
			}

			break;
		case R.id.btnUninstall:
			Intent intent_uninstall = new Intent(Intent.ACTION_DELETE, Uri.parse("package:com.wilsonflying.testframelayout"));
			startActivity(intent_uninstall);
			break;
		case R.id.btnUninstallSilent:
			PackageDeleteObserver observer_delete = new PackageDeleteObserver();  
	        getPackageManager().deletePackage("com.wilsonflying.testframelayout", observer_delete, 0);  
			break;
		case R.id.btnGetCaller:
			int uid = Binder.getCallingUid();
			PackageManager pm2 = getPackageManager();
			String [] pkgNames = pm2.getPackagesForUid(uid);
			
			for (String str : pkgNames) {
				Log.d(TAG, "pkgname:"+str);
			}
			
			break;
			
		case R.id.btnGetStack:
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			for (StackTraceElement stackTraceElement : stack) {
				Log.d(TAG, "name:"+ stackTraceElement);
			}
			
			break;
			
		case R.id.btnGetRunning:
			int pidTarget = Binder.getCallingPid();
			ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			for (ActivityManager.RunningAppProcessInfo appInfo : am.getRunningAppProcesses()) {
				Log.d(TAG, "appName:"+appInfo.processName + " pid:"+appInfo.pid);
			}
			
			break;
		default:
			break;
		}

	}

	class PackageInstallObserver extends IPackageInstallObserver.Stub {

		@Override
		public void packageInstalled(String arg0, int arg1)
				throws RemoteException {
			// TODO Auto-generated method stub
//          Intent intent = new Intent(Constants.ACTION_INSTALLED);
//          intent.putExtra(Constants.EXTRA_INSTALL_RESULT, returnCode);
//          intent.putExtra(Constants.EXTRA_PACKAGE_NAME, packageName);
//          String apkName = map.get(packageName);
//          if( apkName != null )intent.putExtra(Constants.EXTRA_APK_NAME, apkName);
//          mContext.sendBroadcast(intent);
      	Log.d(TAG, "in packageInstalled");
		}  
    }
	
    private class PackageDeleteObserver extends IPackageDeleteObserver.Stub {  

		@Override
		public void packageDeleted(String arg0, int arg1)
				throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "in PackageDeleteObserver"); 
		}  
    }  
}
