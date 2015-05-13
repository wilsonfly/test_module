package net.sunniwell.app.test.base.network.wifi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.SWUtil;
import net.sunniwell.app.test.base.util.StringUtils;
import net.sunniwell.common.log.SWLogger;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.Status;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class WifiConfigListPage extends SWFragment implements OnItemClickListener {
	private SWLogger log = SWLogger.getLogger(WifiConfigListPage.class);
	private final static int OPEN_WIFI = 0;
	private final static int CLOSE_WIFI = 1;
	private final static int WIFI_STATIC = 0;
	private final static int WIFI_DHCP = 1;
	private final static int WIFI_PPPOE = 2;
	private ListView mWifiList;
	private WifiManager mWifiManager;
	private SWifiManager mSWifiManager;
	private WifiAdapter mWifiAdapter;
	private List<AccessPoint> mAccessPoints;
	private AtomicBoolean mConnected = new AtomicBoolean(false);
	private Scanner mScanner;
	private HandlerThread mWifiHandlerThread;
	private WifiHandler mWifiHandler;
	private WifiInfo mLastInfo;
	private DetailedState mLastState;
	private AccessPoint mAccessPoint;
	private String mPrePage;
	private int mWifiMode;
	private View lastView;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showPage(mPrePage);
			return true;
		}
		return false;
	}

	@Override
	public void onNetworkStateChange(Intent intent) {
		String action = intent.getAction();
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
			updateWifiState(intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN));
		} else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action) || WifiManager.CONFIGURED_NETWORKS_CHANGED_ACTION.equals(action) || WifiManager.LINK_CONFIGURATION_CHANGED_ACTION
				.equals(action)) {
			updateAccessPoints();
		} else if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {
			updateConnectionInfo(intent.hasExtra(WifiManager.EXTRA_SUPPLICANT_ERROR), intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1));
			if (!mConnected.get()) {
				updateConnectionState(WifiInfo.getDetailedStateOf((SupplicantState) intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE)));
			}
		} else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
			NetworkInfo info = (NetworkInfo) intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			mConnected.set(info.isConnected());
			updateAccessPoints();
			updateConnectionState(info.getDetailedState());
		} else if (WifiManager.RSSI_CHANGED_ACTION.equals(action)) {
			updateConnectionState(null);
		}
	}

	private void updateWifiState(int state) {
		log.d("state=" + state);
		if (state == WifiManager.WIFI_STATE_ENABLED) {
			mScanner.resume();
			updateAccessPoints();
		} else {
			mScanner.pause();
			mAccessPoints.clear();
			mWifiAdapter.notifyDataSetChanged();
		}
		if (state == WifiManager.WIFI_STATE_DISABLED || state == WifiManager.WIFI_STATE_ENABLED) {
		}
	}

	private void updateAccessPoints() {
		final int wifiState = mWifiManager.getWifiState();
		switch (wifiState) {
		case WifiManager.WIFI_STATE_ENABLED:
			// AccessPoints are automatically sorted with TreeSet.
			final Collection<AccessPoint> accessPoints = constructAccessPoints();
			mAccessPoints.clear();
			mAccessPoints.addAll(accessPoints);
			break;
		case WifiManager.WIFI_STATE_DISABLING:
			break;
		case WifiManager.WIFI_STATE_ENABLING:
		case WifiManager.WIFI_STATE_DISABLED:
			mAccessPoints.clear();
			break;
		}
		log.d("===mAccessPoints===" + mAccessPoints.size());
		mWifiAdapter.notifyDataSetChanged();
	}

	/** Returns sorted list of access points */
	private List<AccessPoint> constructAccessPoints() {
		ArrayList<AccessPoint> accessPoints = new ArrayList<AccessPoint>();
		/**
		 * Lookup table to more quickly update AccessPoints by only considering
		 * objects with the correct SSID. Maps SSID -> List of AccessPoints with
		 * the given SSID.
		 */
		Multimap<String, AccessPoint> apMap = new Multimap<String, AccessPoint>();
		final List<WifiConfiguration> configs = mWifiManager.getConfiguredNetworks();
		if (configs != null) {
			for (WifiConfiguration config : configs) {
				if (config.status == Status.DISABLED)
					config.status = Status.CURRENT;
				AccessPoint accessPoint = new AccessPoint(getActivity(), config);
				accessPoint.update(mLastInfo, mLastState);
				accessPoints.add(accessPoint);
				apMap.put(accessPoint.getSsid(), accessPoint);
			}
		}
		final List<ScanResult> results = mWifiManager.getScanResults();
		if (results != null) {
			for (ScanResult result : results) {
				// Ignore hidden and ad-hoc networks.
				if (result.SSID == null || result.SSID.length() == 0 || result.capabilities.contains("[IBSS]")) {
					continue;
				}
				boolean found = false;
				for (AccessPoint accessPoint : apMap.getAll(result.SSID)) {
					if (accessPoint.update(result))
						found = true;
				}
				if (!found) {
					AccessPoint accessPoint = new AccessPoint(getActivity(), result);
					accessPoints.add(accessPoint);
					apMap.put(accessPoint.getSsid(), accessPoint);
				}
			}
		}
		// Pre-sort accessPoints to speed preference insertion
		Collections.sort(accessPoints);
		return accessPoints;
	}

	/** A restricted multimap for use in constructAccessPoints */
	private class Multimap<K, V> {
		private HashMap<K, List<V>> store = new HashMap<K, List<V>>();

		/** retrieve a non-null list of values with key K */
		List<V> getAll(K key) {
			List<V> values = store.get(key);
			return values != null ? values : Collections.<V> emptyList();
		}

		void put(K key, V val) {
			List<V> curVals = store.get(key);
			if (curVals == null) {
				curVals = new ArrayList<V>(3);
				store.put(key, curVals);
			}
			curVals.add(val);
		}
	}

	private void updateConnectionState(DetailedState state) {
		log.d("state=" + state);
		/* sticky broadcasts can call this when wifi is disabled */
		if (!mWifiManager.isWifiEnabled()) {
			mScanner.pause();
			return;
		}

		if (state == DetailedState.OBTAINING_IPADDR) {
			mScanner.pause();
		} else {
			mScanner.resume();
		}
		if (state == DetailedState.CONNECTING || state == DetailedState.CONNECTED) {
			mWifiAdapter.setTemp(0);
			mWifiList.setSelection(0);
		}
		mLastInfo = mWifiManager.getConnectionInfo();
		if (state != null) {
			mLastState = state;
		}
		for (int i = mAccessPoints.size() - 1; i >= 0; --i) {
			// Maybe there's a WifiConfigPreference
			final AccessPoint accessPoint = mAccessPoints.get(i);
			accessPoint.update(mLastInfo, mLastState);
		}
		Collections.sort(mAccessPoints);
		mWifiAdapter.notifyDataSetChanged();
	}

	private void updateConnectionInfo(boolean hasError, int error) {
		log.d("hasError=" + hasError + ",error=" + error);
		if (hasError) {
			String mesg = "";
			if (error == WifiManager.ERROR_AUTHENTICATING) {
				mesg = getActivity().getString(R.string.wifi_disabled_password_failure);
				if (mAccessPoint != null) {
					showMessage(getActivity());
				}
			}
			if (!mesg.equals("")) {
				Message msg = new Message();
				msg.what = SWifiManager.SEND_MSG_TO_STATUSBAR;
				msg.obj = mesg;
				SWifiManager.getInstance(getActivity()).sendMsg(msg);
			}
			log.d("remove networkid = " + mSWifiManager.getmCurrentNetworkId());
			int networkId = SWifiManager.getInstance(getActivity()).getmCurrentNetworkId();
			mWifiManager.forget(networkId, null);
			mWifiManager.saveConfiguration();
			WifiInfo info = mWifiManager.getConnectionInfo();
			AccessPoint reConnectAp = null;
			final List<ScanResult> results = mWifiManager.getScanResults();
			if (results != null) {
				for (ScanResult result : results) {
					// Ignore hidden and ad-hoc networks.
					if (result.SSID == null || result.SSID.length() == 0 || result.capabilities.contains("[IBSS]")) {
						continue;
					}
					if (info.getSSID() == null) {
						if (result.BSSID != null && result.BSSID.equals(info.getBSSID())) {
							reConnectAp = new AccessPoint(getActivity(), result);
							break;
						}
					} else if (result.BSSID.equals(AccessPoint.removeDoubleQuotes((info.getSSID())))) {
						reConnectAp = new AccessPoint(getActivity(), result);
						break;
					}
				}
			}

			if (reConnectAp != null) {
				// gotoWifiConnPage(reConnectAp);
			}
		}
	};

	public void showMessage(Context mContext) {
		try {
			String ssid = mAccessPoint.getSsid();
			if (!TextUtils.isEmpty(ssid)) {
				ToastHelper.showMessage(getActivity(), ssid + mContext.getString(R.string.network_wifi_failed), false, Toast.LENGTH_SHORT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		mWifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
		View v = inflater.inflate(R.layout.wifilist, null);
		mWifiList = (ListView) v.findViewById(R.id.wifilist);
		mAccessPoints = new ArrayList<AccessPoint>();
		mWifiAdapter = new WifiAdapter(getActivity(), mAccessPoints);
		mWifiList.setAdapter(mWifiAdapter);
		mWifiList.setOnItemClickListener(this);
		mScanner = new Scanner();
		mSWifiManager = SWifiManager.getInstance(getActivity());
		mWifiHandlerThread = new HandlerThread("wifi");
		mWifiHandlerThread.start();
		mWifiHandler = new WifiHandler(mWifiHandlerThread.getLooper());
		mWifiHandler.sendEmptyMessage(OPEN_WIFI);
		try {
			Bundle b = getArguments();
			mPrePage = b.getString("prePage", null);
			mWifiMode = b.getInt("wifimode", -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
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
			fragment.setArguments(b);
			ft.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		try {
			if (lastView != null) {
				RadioButton rb = (RadioButton) lastView.findViewById(R.id.wifi_check);
				rb.setChecked(false);
			}
			if (position == mAccessPoints.size()) {
				return;
			}
			lastView = v;
			mWifiAdapter.setTemp(position);
			RadioButton rb = (RadioButton) lastView.findViewById(R.id.wifi_check);
			rb.setChecked(true);
			mAccessPoint = mAccessPoints.get(position);
			log.d("ssid===" + mAccessPoint.getSsid());
			boolean isConnected = false;
			try {
				ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (mWifi.isConnected()) {
					log.d("...wifi is connected.....");
					WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
					String current = StringUtils.removeDoubleQuotes(wifiInfo.getSSID());
					isConnected = mAccessPoint.getSsid().equals(current);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isConnected) {
				return;
			}
			boolean saved = (mAccessPoint.getNetworkId() != -1);
			boolean isSecurity = true;
			log.d("isConnected==" + isConnected + " saved==" + saved + "" + (mAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE));
			if (!isConnected && !saved && mAccessPoint.getSecurity() == AccessPoint.SECURITY_NONE) {
				isSecurity = false;
			}
			gotoConnectWifi(isSecurity);
		} catch (Exception e) {

		}
	}

	private void gotoConnectWifi(boolean isSecurity) {
		if (isSecurity) {
			showPasswordDialog();
		} else {
			connectWifi();
		}

	}

	private void connectWifi() {
		if (mSWifiManager.setSSID(mAccessPoint.getSsid())) {
			switch (mWifiMode) {
			case WIFI_STATIC:
				WifiNetworkBean wifiBean = new WifiNetworkBean();
				String ipadd = Settings.Secure.getString(getActivity().getContentResolver(), "wifi_static_ip");
				String gwadd = Settings.Secure.getString(getActivity().getContentResolver(), "wifi_static_gateway");
				String maskadd = Settings.Secure.getString(getActivity().getContentResolver(), "wifi_static_mask");
				String dnsadd = Settings.Secure.getString(getActivity().getContentResolver(), "wifi_static_dns");
				String dns2add = Settings.Secure.getString(getActivity().getContentResolver(), "wifi_static_dns2");
				wifiBean.setIp(ipadd == null ? "0.0.0.0" : ipadd);
				wifiBean.setMask(maskadd == null ? "0.0.0.0" : maskadd);
				wifiBean.setGateway(gwadd == null ? "0.0.0.0" : gwadd);
				wifiBean.setDns(dnsadd == null ? "0.0.0.0" : dnsadd);
				wifiBean.setDns2(dns2add == null ? "0.0.0.0" : dns2add);
				mSWifiManager.setNetWork(wifiBean);
				mSWifiManager.setMode("static");
				log.d(".............^^^^^^..........connect button click... setNetwork ");
				break;
			case WIFI_DHCP:
				mSWifiManager.setMode("dhcp");
				break;
			case WIFI_PPPOE:
				break;
			}
			log.d("ssid===" + mAccessPoint.getSsid());
			final List<WifiConfiguration> configs = mWifiManager.getConfiguredNetworks();
			if (configs != null) {
				for (WifiConfiguration config : configs) {
					if (config.SSID != null && AccessPoint.removeDoubleQuotes(config.SSID).equals(mAccessPoint.getSsid())) {
						mWifiManager.forget(config.networkId, null);
					}
				}
			}
			mSWifiManager.connectWifi(mAccessPoint.getSsid());
		}
	}

	private void showPasswordDialog() {
		final LinearLayout paLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.passdialog, null);
		String title = "请输入" + mAccessPoint.getSsid() + "的密码";
		new AlertDialog.Builder(getActivity()).setTitle(title).setView(paLayout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText passEditText = (EditText) paLayout.findViewById(R.id.pass);
				String pass = passEditText.getText().toString();
				mSWifiManager.setPassword(pass);
				connectWifi();
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				log.d("cancel connect");
			}
		}).show();
	}

	private class Scanner extends Handler {
		private int mRetry = 0;

		void resume() {
			if (!hasMessages(0)) {
				sendEmptyMessage(0);
			}
		}

		void pause() {
			mRetry = 0;
			removeMessages(0);
		}

		@Override
		public void handleMessage(Message message) {
			if (mWifiManager.startScanActive()) {
				mRetry = 0;
			} else if (++mRetry >= 3) {
				mRetry = 0;
				return;
			}
			sendEmptyMessageDelayed(0, 6000);
		}
	}

	private class WifiHandler extends Handler {
		public WifiHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == OPEN_WIFI) {
				mSWifiManager.setWifiEnabled();
			} else if (msg.what == CLOSE_WIFI) {
				mSWifiManager.setWifiUnEnabled();
			}
		}
	}

}
