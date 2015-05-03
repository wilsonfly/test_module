package net.sunniwell.app.test.base.network.eth;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.IPUtils;
import net.sunniwell.common.log.SWLogger;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.ethernet.EthernetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class DhcpPage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private final String PRE_PAGE = DhcpPage.class.getPackage().getName() + ".EthernetPage";
	private final static int CONFIG_DHCP = 0;
	private EthernetManager mEthernetManager;
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
		isResponse = true;
		View v = inflater.inflate(R.layout.eth_dhcp, null);
		v.requestFocus();
		mIp = (TextView) v.findViewById(R.id.ip);
		mMask = (TextView) v.findViewById(R.id.mask);
		mGateway = (TextView) v.findViewById(R.id.gateway);
		mDns = (TextView) v.findViewById(R.id.dns1);
		mDns2 = (TextView) v.findViewById(R.id.dns2);
		mTip = v.findViewById(R.id.dhcptip);
		mResult = (TextView) v.findViewById(R.id.dhcpresult);
		mTip.setVisibility(View.INVISIBLE);
		mResult.setVisibility(View.INVISIBLE);
		v.findViewById(R.id.retry).setOnClickListener(this);
		v.findViewById(R.id.back).setOnClickListener(this);
		mThread = new HandlerThread("dhcp");
		mThread.start();
		mDhcpHandler = new DhcpHandler(mThread.getLooper());
		mEthernetManager = (EthernetManager) getActivity().getSystemService(Context.ETHERNET_SERVICE);
		mTip.setVisibility(View.VISIBLE);
		mResult.setVisibility(View.INVISIBLE);
		initData(true);
		mDhcpHandler.sendEmptyMessage(CONFIG_DHCP);
		return v;
	}

	private void initData(boolean isFirst) {
		try {
			DhcpInfo di = mEthernetManager.getDhcpInfo();
			if (di != null && !isFirst) {
				mIp.setText(IPUtils.intToIp(di.ipAddress));
				mMask.setText(IPUtils.intToIp(di.netmask));
				mGateway.setText(IPUtils.intToIp(di.gateway));
				mDns.setText(IPUtils.intToIp(di.dns1));
				mDns2.setText(IPUtils.intToIp(di.dns2));
			} else {
				String tmp = "0.0.0.0";
				mIp.setText(tmp);
				mMask.setText(tmp);
				mGateway.setText(tmp);
				mDns.setText(tmp);
				mDns2.setText(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			String mode = mEthernetManager.getEthernetMode();
			if (mode != null && mode.equals(EthernetManager.ETHERNET_CONNECT_MODE_DHCP)) {
				String action = intent.getAction();
				log.d("action====" + action);
				if (action != null && action.equals(EthernetManager.ETHERNET_STATE_CHANGED_ACTION)) {
					int state = intent.getIntExtra(EthernetManager.EXTRA_ETHERNET_STATE, -1);
					switch (state) {
					case EthernetManager.EVENT_PHY_LINK_DOWN:
						log.d("......网线拔掉......");
						break;
					case EthernetManager.EVENT_PHY_LINK_UP:
						log.d("......网线插上......");
						break;
					case EthernetManager.EVENT_CONNECT_SUCCESSED:
						handleSuccess();
						break;
					case EthernetManager.EVENT_CONNECT_FAILED:
						handleFailed(intent.getIntExtra(EthernetManager.EXTRA_ETHERNET_FAILED_REASON, -1));
						break;
					case EthernetManager.EVENT_DISCONNECT_SUCCESSED:
						break;
					case EthernetManager.EVENT_DISCONNECT_FAILED:
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		try {
			mEthernetManager.setEthernetMode(EthernetManager.ETHERNET_CONNECT_MODE_DHCP, null);
			if (!mEthernetManager.isEthernetEnabled()) {
				mEthernetManager.setEthernetEnabled(true);
			} else {
				mEthernetManager.setEthernetState(EthernetManager.ETHERNET_STATE_DISABLED);
				mEthernetManager.setEthernetState(EthernetManager.ETHERNET_STATE_ENABLED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showPrePage() {
		try {
			Class<?> page = Class.forName(PRE_PAGE);
			Fragment fragment = (Fragment) page.newInstance();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.details, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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
