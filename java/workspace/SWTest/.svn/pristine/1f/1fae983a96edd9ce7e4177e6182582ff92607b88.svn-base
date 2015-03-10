package net.sunniwell.app.test.base.network.wifi;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.IPUtils;
import net.sunniwell.common.log.SWLogger;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.ethernet.EthernetManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class DhcpPage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private final String PRE_PAGE = StaticPage.class.getPackage().getName() + ".WifiPage";
	private final String POST_PAGE = StaticPage.class.getPackage().getName() + ".WifiConfigListPage";
	private final static int CONFIG_DHCP = 0;
	private WifiManager mWifiManager;
	private HandlerThread mThread;
	private DhcpHandler mDhcpHandler;
	private View mTip;
	private TextView mResult;
	private TextView mIp;
	private TextView mMask;
	private TextView mGateway;
	private TextView mDns;
	private TextView mDns2;
	private boolean isResponse;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		showPostPage();
		return null;
	}

	private void initData(boolean isFirst) {

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showPrePage();
			return true;
		}
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_DOWN:
		case KeyEvent.KEYCODE_DPAD_UP:
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onNetworkStateChange(Intent intent) {
		super.onNetworkStateChange(intent);
	}

	@Override
	public void onStop() {
		log.d("...onStop...");
		try {
			if (mDhcpHandler != null) {
				mThread.quit();
				mThread = null;
				mDhcpHandler = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onStop();
	}

	private void handleSuccess() {
		initData(false);
		mResult.setText(getActivity().getString(R.string.resultOK));
		mResult.setVisibility(View.VISIBLE);
		mTip.setVisibility(View.INVISIBLE);
		isResponse = true;
	}

	private void handleFailed(int reason) {
		switch (reason) {
		case EthernetManager.FAILED_REASON_DISCONNECTED:
			mResult.setText(getActivity().getString(R.string.resultNOK));
			mResult.setVisibility(View.VISIBLE);
			mTip.setVisibility(View.INVISIBLE);
			break;
		case EthernetManager.FAILED_REASON_TIMEOUT:
			break;
		case EthernetManager.FAILED_REASON_MANUAL_IP_CONFLICT:
			mResult.setText(getActivity().getString(R.string.ipConfict));
			mResult.setVisibility(View.VISIBLE);
			mTip.setVisibility(View.INVISIBLE);
			break;
		default:
			break;
		}
		isResponse = true;

	}

	private void configDhcp() {

	}

	private void showPostPage() {
		showPage(POST_PAGE);
	}

	private void showPrePage() {
		showPage(PRE_PAGE);
	}

	private void showPage(String pageName) {
		try {
			Class<?> page = Class.forName(pageName);
			Fragment fragment = (Fragment) page.newInstance();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.details, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			Bundle b = new Bundle();
			b.putBoolean("requestFocus", true);
			b.putString("prePage", DhcpPage.class.getName());
			b.putInt("wifimode", 1);
			fragment.setArguments(b);
			ft.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		if (!isResponse) {
			return;
		}
		switch (v.getId()) {
		case R.id.retry:
			isResponse = false;
			mTip.setVisibility(View.VISIBLE);
			mResult.setVisibility(View.INVISIBLE);
			mDhcpHandler.sendEmptyMessage(CONFIG_DHCP);
			break;
		case R.id.back:
			showPrePage();
			break;
		default:
			break;
		}
	}

	private class DhcpHandler extends Handler {
		public DhcpHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			configDhcp();
			super.handleMessage(msg);
		}
	}

}
