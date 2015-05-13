package net.sunniwell.app.application;

import java.util.Arrays;
import java.util.List;

import net.sunniwell.app.shutdown.R;
import android.app.Application;

/**
 * 
 * @author zhangdaili
 *
 * @Date 2013-11-28
 */
public class SWApplication extends Application {
	private List<String> mUnStopApp;
	private boolean isShutDownShow;

	@Override
	public void onCreate() {
		super.onCreate();
		String[] apps = getResources().getStringArray(R.array.unStopApp);
		mUnStopApp = Arrays.asList(apps);
		isShutDownShow = false;
	}

	public List<String> getUnStopApp() {
		return mUnStopApp;
	}

	public boolean isShutDownShow() {
		return isShutDownShow;
	}

	public void setShutDownShow(boolean isShutDownShow) {
		this.isShutDownShow = isShutDownShow;
	}

}
