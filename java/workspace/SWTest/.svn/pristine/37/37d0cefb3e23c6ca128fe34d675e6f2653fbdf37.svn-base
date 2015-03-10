package net.sunniwell.app.test.base.network.eth;

import java.net.InetAddress;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.IPUtils;
import net.sunniwell.common.log.SWLogger;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.DhcpInfoInternal;
import android.net.RouteInfo;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StaticPage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private final String PRE_PAGE = StaticPage.class.getPackage().getName() + ".EthernetPage";
	private final static int CONFIG_STATIC = 0;
	private final static int TIP_SHOW = 0;
	private final static int TIP_HIDDEN = 1;
	private EditText mIp;
	private EditText mMask;
	private EditText mGateway;
	private EditText mDns;
	private EditText mDns2;
	private EthernetManager mEthernetManager;
	private HandlerThread mThread;
	private StaticHandler mStaticHandler;
	private View mTip;
	private TextView mResult;
	private boolean isResponse;
	private Handler mUiHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case TIP_SHOW:
				mTip.setVisibility(View.VISIBLE);
				mResult.setVisibility(View.INVISIBLE);
				break;
			case TIP_HIDDEN:
				mTip.setVisibility(View.INVISIBLE);
				mResult.setVisibility(View.INVISIBLE);
				break;
			default:
				break;
			}
		};
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		isResponse = true;
		View v = inflater.inflate(R.layout.eth_static, null);
		v.requestFocus();
		mIp = (EditText) v.findViewById(R.id.ip);
		mMask = (EditText) v.findViewById(R.id.mask);
		mGateway = (EditText) v.findViewById(R.id.gateway);
		mDns = (EditText) v.findViewById(R.id.dns1);
		mDns2 = (EditText) v.findViewById(R.id.dns2);
		mTip = v.findViewById(R.id.tip);
		mResult = (TextView) v.findViewById(R.id.result);
		mTip.setVisibility(View.INVISIBLE);
		mResult.setVisibility(View.INVISIBLE);
		v.findViewById(R.id.ok).setOnClickListener(this);
		v.findViewById(R.id.cancel).setOnClickListener(this);
		mThread = new HandlerThread("static");
		mThread.start();
		mStaticHandler = new StaticHandler(mThread.getLooper());
		mEthernetManager = (EthernetManager) getActivity().getSystemService(Context.ETHERNET_SERVICE);
		initData();
		return v;
	}

	@Override
	public void onStop() {
		log.d("...onStop...");
		try {
			if (mStaticHandler != null) {
				mThread.quit();
				mThread = null;
				mStaticHandler = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onStop();
	}

	private void initData() {
		try {
			DhcpInfo di = mEthernetManager.getSavedEthernetIpInfo();
			mIp.setText(IPUtils.intToIp(di.ipAddress));
			mMask.setText(IPUtils.intToIp(di.netmask));
			mGateway.setText(IPUtils.intToIp(di.gateway));
			mDns.setText(IPUtils.intToIp(di.dns1));
			mDns2.setText(IPUtils.intToIp(di.dns2));
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
		return false;
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
	public void onNetworkStateChange(Intent intent) {
		try {
			String action = intent.getAction();
			log.d("action====" + action);
			if (action != null && action.equals(EthernetManager.ETHERNET_STATE_CHANGED_ACTION)) {
				String mode = mEthernetManager.getEthernetMode();
				if (mode != null && mode.equals(EthernetManager.ETHERNET_CONNECT_MODE_MANUAL)) {
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

	private void handleSuccess() {
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

	@Override
	public void onClick(View v) {
		if (!isResponse) {
			return;
		}
		switch (v.getId()) {
		case R.id.ok:
			isResponse = false;
			mStaticHandler.sendEmptyMessage(CONFIG_STATIC);
			break;
		case R.id.cancel:
			showPrePage();
			break;
		default:
			break;
		}
	}

	private void configEthStatic() {
		try {
			String ip = mIp.getEditableText().toString();
			String mask = mMask.getEditableText().toString();
			String gateway = mGateway.getEditableText().toString();
			String dns1 = mDns.getEditableText().toString();
			String dns2 = mDns2.getEditableText().toString();
			if (IPUtils.check(ip, mask, gateway) && (IPUtils.isIP(dns1) || IPUtils.isIP(dns2))) {
				mUiHandler.sendEmptyMessage(TIP_SHOW);
				DhcpInfoInternal di = new DhcpInfoInternal();
				RouteInfo ri = new RouteInfo(InetAddress.getByName(gateway));
				di.ipAddress = ip;
				di.addRoute(ri);
				di.dns1 = dns1;
				di.dns2 = dns2;
				di.prefixLength = IPUtils.getNetmaskLength(mask);
				mEthernetManager.setEthernetMode(EthernetManager.ETHERNET_CONNECT_MODE_MANUAL, di.makeDhcpInfo());
				if (!mEthernetManager.isEthernetEnabled()) {
					mEthernetManager.setEthernetEnabled(true);
				} else {
					mEthernetManager.setEthernetState(EthernetManager.ETHERNET_STATE_DISABLED);
					mEthernetManager.setEthernetState(EthernetManager.ETHERNET_STATE_ENABLED);
				}
			} else {
				mUiHandler.sendEmptyMessage(TIP_HIDDEN);
				Toast.makeText(getActivity(), getActivity().getString(R.string.checkNetInfo), Toast.LENGTH_LONG).show();
				isResponse = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class StaticHandler extends Handler {
		public StaticHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			configEthStatic();
			super.handleMessage(msg);
		}
	}

}
