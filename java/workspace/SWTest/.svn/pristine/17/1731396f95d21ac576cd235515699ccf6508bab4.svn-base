package net.sunniwell.app.test.base;

import net.sunniwell.app.test.base.network.eth.EthernetPage;
import net.sunniwell.common.log.SWLogger;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

public class Main extends Activity {
	private SWLogger log = SWLogger.getLogger(getClass());
	private BroadcastReceiver mNetworkStateReciver;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getWindow().getDecorView().setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
		registeNetworkReciver();
	}

	private void registeNetworkReciver() {
		mNetworkStateReciver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				log.d("action==" + intent.getAction());
				SWFragment fragment = (SWFragment) getFragmentManager().findFragmentById(R.id.details);
				if (fragment != null) {
					fragment.onNetworkStateChange(intent);
				}
			}

		};
		IntentFilter filter = new IntentFilter();
		filter.addAction(EthernetManager.ETHERNET_STATE_CHANGED_ACTION);
		filter.addAction(PppoeManager.PPPOE_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		filter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
		filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.CONFIGURED_NETWORKS_CHANGED_ACTION);
		filter.addAction(WifiManager.LINK_CONFIGURATION_CHANGED_ACTION);
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		registerReceiver(mNetworkStateReciver, filter);
	}

	@Override
	protected void onDestroy() {
		if (mNetworkStateReciver != null) {
			unregisterReceiver(mNetworkStateReciver);
			mNetworkStateReciver = null;
		}
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		SWFragment fragment = (SWFragment) getFragmentManager().findFragmentById(R.id.details);
		if (!fragment.onKeyDown(keyCode, event)) {
			return super.onKeyDown(keyCode, event);
		} else {
			return true;
		}
	}

	public class DetailsActivity extends Activity {
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			if (savedInstanceState == null) {
				EthernetPage df = new EthernetPage();
				df.setArguments(getIntent().getExtras());
				getFragmentManager().beginTransaction().add(android.R.id.content, df);
			}
		}
	}

	private class SWHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	}
}
