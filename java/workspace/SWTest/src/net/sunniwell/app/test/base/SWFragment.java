package net.sunniwell.app.test.base;

import android.app.Fragment;
import android.content.Intent;
import android.view.KeyEvent;

public abstract class SWFragment extends Fragment {
	public abstract boolean onKeyDown(int keyCode, KeyEvent event);

	public void onNetworkStateChange(Intent intent) {};
}
