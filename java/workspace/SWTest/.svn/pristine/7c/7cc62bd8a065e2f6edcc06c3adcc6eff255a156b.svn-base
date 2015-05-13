package net.sunniwell.app.test.base.network.wifi;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.common.log.SWLogger;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class WifiPage extends SWFragment implements OnClickListener {
	private final String PACAGENAME = WifiPage.class.getPackage().getName();
	private final String[] POST_PAGES = new String[] { "StaticPage", "DhcpPage", "PppoePage" };
	private SWLogger log = SWLogger.getLogger(getClass());

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View v = inflater.inflate(R.layout.wifi, null);
		v.findViewById(R.id.wifistatic).setOnClickListener(this);
		v.findViewById(R.id.wifidhcp).setOnClickListener(this);
		v.findViewById(R.id.wifipppoe).setOnClickListener(this);
		try {
			Bundle b = getArguments();
			boolean requestFocus = b.getBoolean("requestFocus", false);
			if (requestFocus) {
				v.requestFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	@Override
	public void onClick(View v) {
		int index = 0;
		switch (v.getId()) {
		case R.id.wifistatic:
			index = 0;
			break;
		case R.id.wifidhcp:
			index = 1;
			break;
		case R.id.wifipppoe:
			index = 2;
			break;
		default:
			break;
		}
		showPostPage(index);
	}

	private void showPostPage(int index) {
		showPage(POST_PAGES[index]);
	}

	private void showPage(String simpleClassName) {
		try {
			StringBuilder className = new StringBuilder();
			className.append(PACAGENAME);
			className.append(".");
			className.append(simpleClassName);
			Class<?> page = Class.forName(className.toString());
			Fragment fragment = (Fragment) page.newInstance();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.details, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
