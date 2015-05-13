package net.sunniwell.app.test.base.power;

import java.lang.reflect.Method;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.common.log.SWLogger;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PowerPage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private Button mRealPowerOff;
	private Button mFalsePowerOff;
	private Button mShutdown;
	private Button mReboot;
	private PowerManager mPowerManager;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		mPowerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
		View v = inflater.inflate(R.layout.power, null);
		// v.requestFocus();
		mRealPowerOff = (Button) v.findViewById(R.id.realpoweroff);
		mFalsePowerOff = (Button) v.findViewById(R.id.falsepoweroff);
		// mShutdown = (Button) v.findViewById(R.id.shutdown);
		mReboot = (Button) v.findViewById(R.id.reboot);
		mRealPowerOff.setOnClickListener(this);
		mFalsePowerOff.setOnClickListener(this);
		// mShutdown.setOnClickListener(this);
		mReboot.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.realpoweroff:
			gotoSleep(true);
			break;
		case R.id.falsepoweroff:
			gotoSleep(false);
			break;
		// case R.id.shutdown:
		// shutdown();
		// break;
		case R.id.reboot:
			reboot();
			break;
		default:
			break;
		}
	}

	private void gotoSleep(boolean isRealPowerOffMode) {
		if (isRealPowerOffMode) {
			SystemProperties.set("sys.standby.mode", "1");
		} else {
			SystemProperties.set("sys.standby.mode", "0");
		}
		WakeLock lWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getActivity().getClass().getSimpleName());
		lWakeLock.acquire();
		log.d("......start sleep......");
		mPowerManager.goToSleep(SystemClock.uptimeMillis());
		lWakeLock.release();
		log.d("......end sleep......");
	}

	private void shutdown() {
		try {
			Class<?> ShutdownThread = Class.forName("com.android.server.power.ShutdownThread");
			Method shutdown = ShutdownThread.getDeclaredMethod("shutdown", Context.class, boolean.class);
			shutdown.invoke(ShutdownThread, getActivity(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reboot() {
		mPowerManager.reboot(null);
	}

}
