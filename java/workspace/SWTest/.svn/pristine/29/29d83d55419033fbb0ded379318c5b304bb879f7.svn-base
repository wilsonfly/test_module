package net.sunniwell.app.test.base.key;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 
 * @author zhangdaili
 * 
 * @date 2014-1-18
 */
public class KeyManagerPage extends SWFragment implements OnClickListener {
	private Button mHome;
	private Button mPower;
	private Button mSetting;
	private Button mThreeKeys;
	private Button mAllKeys;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View v = inflater.inflate(R.layout.keymanager, null);
		mHome = (Button) v.findViewById(R.id.homekey);
		mPower = (Button) v.findViewById(R.id.powerkey);
		mSetting = (Button) v.findViewById(R.id.settingkey);
		mThreeKeys = (Button) v.findViewById(R.id.threekey);
		mAllKeys = (Button) v.findViewById(R.id.lockallkey);
		mHome.setOnClickListener(this);
		mPower.setOnClickListener(this);
		mSetting.setOnClickListener(this);
		mThreeKeys.setOnClickListener(this);
		mAllKeys.setOnClickListener(this);
		return v;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:

			break;
		default:
			break;
		}
		return false;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.homekey:
			lockHomeKey();
			break;
		case R.id.powerkey:
			lockPowerKey();
			break;
		case R.id.settingkey:
			lockSettingKey();
			break;
		case R.id.threekey:
			lockThreeKeys();
			break;
		case R.id.lockallkey:
			lockAllKey();
			break;
		default:
			break;
		}
	}

	private void lockHomeKey() {
		SystemProperties.set("sys.isHandleHomeKey", "0");
		SystemProperties.set("sys.isHandlePowerKey", "1");
		SystemProperties.set("sys.isHandleSettingKey", "1");
		SystemProperties.set("sys.isHandleSystemKey", "1");
		SystemProperties.set("sys.isLockedAllKeysy", "0");
	}

	private void lockPowerKey() {
		SystemProperties.set("sys.isHandleHomeKey", "1");
		SystemProperties.set("sys.isHandlePowerKey", "0");
		SystemProperties.set("sys.isHandleSettingKey", "1");
		SystemProperties.set("sys.isHandleSystemKey", "1");
		SystemProperties.set("sys.isLockedAllKeysy", "0");
	}

	private void lockSettingKey() {
		SystemProperties.set("sys.isHandleHomeKey", "1");
		SystemProperties.set("sys.isHandlePowerKey", "1");
		SystemProperties.set("sys.isHandleSettingKey", "0");
		SystemProperties.set("sys.isHandleSystemKey", "1");
		SystemProperties.set("sys.isLockedAllKeysy", "0");
	}

	private void lockThreeKeys() {
		SystemProperties.set("sys.isHandleHomeKey", "1");
		SystemProperties.set("sys.isHandlePowerKey", "1");
		SystemProperties.set("sys.isHandleSettingKey", "1");
		SystemProperties.set("sys.isHandleSystemKey", "0");
		SystemProperties.set("sys.isLockedAllKeysy", "0");
	}

	private void lockAllKey() {
		SystemProperties.set("sys.isHandleHomeKey", "1");
		SystemProperties.set("sys.isHandlePowerKey", "1");
		SystemProperties.set("sys.isHandleSettingKey", "1");
		SystemProperties.set("sys.isHandleSystemKey", "1");
		SystemProperties.set("sys.isLockedAllKeys", "1");
	}

}
