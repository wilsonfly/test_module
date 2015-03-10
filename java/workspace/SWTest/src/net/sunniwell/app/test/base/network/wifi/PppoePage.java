package net.sunniwell.app.test.base.network.wifi;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.SWUtil;
import net.sunniwell.common.log.SWLogger;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkUtils;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class PppoePage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private final String PRE_PAGE = PppoePage.class.getPackage().getName() + ".WifiPage";
	private final static int CONFIG_PPPOE = 0;
	private final static int TIP_SHOW = 0;
	private final static int TIP_HIDDEN = 1;
	private final static int TIP_USERNAME = 2;
	private EthernetManager mEthernetManager;
	private PppoeManager mPppoeManager;
	private HandlerThread mThread;
	private PppoeHandler mPppoeHandler;
	private EditText mIp;
	private EditText mMask;
	private EditText mGateway;
	private EditText mDns;
	private EditText mDns2;
	private View mTip;
	private TextView mResult;
	private EditText mUserName;
	private EditText mPass;
	private boolean isResponse;
	private Activity mContext;
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
			case TIP_USERNAME:
				mTip.setVisibility(View.INVISIBLE);
				mResult.setVisibility(View.VISIBLE);
				mResult.setText("用户名和密码不能为空!");
				break;
			default:
				break;
			}
		};
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showPrePage();
			return true;
		}
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		isResponse = true;
		mContext = getActivity();
		View v = inflater.inflate(R.layout.eth_pppoe, null);
		v.requestFocus();
		mIp = (EditText) v.findViewById(R.id.ip);
		mMask = (EditText) v.findViewById(R.id.mask);
		mGateway = (EditText) v.findViewById(R.id.gateway);
		mDns = (EditText) v.findViewById(R.id.dns1);
		mDns2 = (EditText) v.findViewById(R.id.dns2);
		mUserName = (EditText) v.findViewById(R.id.name);
		mPass = (EditText) v.findViewById(R.id.pass);
		mTip = v.findViewById(R.id.dhcptip);
		mResult = (TextView) v.findViewById(R.id.dhcpresult);
		mTip.setVisibility(View.INVISIBLE);
		mResult.setVisibility(View.INVISIBLE);
		v.findViewById(R.id.retry).setOnClickListener(this);
		v.findViewById(R.id.back).setOnClickListener(this);
		mThread = new HandlerThread("pppoe");
		mThread.start();
		mPppoeHandler = new PppoeHandler(mThread.getLooper());
		mEthernetManager = (EthernetManager) getActivity().getSystemService(Context.ETHERNET_SERVICE);
		mPppoeManager = (PppoeManager) getActivity().getSystemService(Context.PPPOE_SERVICE);
		initData(true);
		return v;
	}

	private void initData(boolean isFirst) {
		String name = null;
		String pass = null;
		try {
			name = Secure.getString(mContext.getContentResolver(), Settings.Secure.PPPOE_USER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			pass = Secure.getString(mContext.getContentResolver(), Settings.Secure.PPPOE_USER_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (name != null) {
			mUserName.setText(name);
		}
		if (pass != null) {
			mPass.setText(pass);
		}
	}

	private void showNetworkInfo() {
		try {
			DhcpInfo di = mEthernetManager.getSavedEthernetIpInfo();
			mIp.setText(NetworkUtils.intToInetAddress(di.ipAddress).toString().replace("/", ""));
			mMask.setText(NetworkUtils.intToInetAddress(di.netmask).toString().replace("/", ""));
			mGateway.setText(NetworkUtils.intToInetAddress(di.gateway).toString().replace("/", ""));
			mDns.setText(NetworkUtils.intToInetAddress(di.dns1).toString().replace("/", ""));
			mDns2.setText(NetworkUtils.intToInetAddress(di.dns2).toString().replace("/", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onNetworkStateChange(Intent intent) {
		try {
			int mode = mPppoeManager.getPppoeType();
			if (mode == PppoeManager.DEVICE_TYPE_ETHERNET) {
				String action = intent.getAction();
				log.d("action====" + action);
				if (action != null && (action.equals(PppoeManager.PPPOE_STATE_CHANGED_ACTION) || action.equals(EthernetManager.ETHERNET_STATE_CHANGED_ACTION))) {
					int pppoeState = intent.getIntExtra(PppoeManager.EXTRA_PPPOE_STATE, -1);
					int ethState = intent.getIntExtra(EthernetManager.EXTRA_ETHERNET_STATE, -1);
					switch (ethState) {
					case EthernetManager.EVENT_PHY_LINK_DOWN:
						log.d("......网线拔掉......");
						break;
					case EthernetManager.EVENT_PHY_LINK_UP:
						log.d("......网线插上......");
						break;
					default:
						break;
					}
					switch (pppoeState) {
					case PppoeManager.EVENT_CONNECT_SUCCESSED:
						handleSuccess();
						break;
					case PppoeManager.EVENT_CONNECTING:
						break;
					case PppoeManager.EVENT_CONNECT_FAILED:
						handleFailed(intent.getIntExtra(PppoeManager.EXTRA_PPPOE_FAILED_REASON, -1));
						break;
					case PppoeManager.EVENT_DISCONNECT_SUCCESSED:
						break;
					case PppoeManager.EVENT_DISCONNECT_FAILED:
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
			if (mPppoeHandler != null) {
				mThread.quit();
				mThread = null;
				mPppoeHandler = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onStop();
	}

	private void handleSuccess() {
		showNetworkInfo();
		mResult.setText(getActivity().getString(R.string.resultOK));
		mResult.setVisibility(View.VISIBLE);
		mTip.setVisibility(View.INVISIBLE);
		isResponse = true;
	}

	private void handleFailed(int reason) {
		switch (reason) {
		case PppoeManager.FAILED_REASON_DISCONNECTED:
			mResult.setText(getActivity().getString(R.string.resultNOK));
			mResult.setVisibility(View.VISIBLE);
			mTip.setVisibility(View.INVISIBLE);
			break;
		case PppoeManager.FAILED_REASON_TIMEOUT:
			break;
		default:
			break;
		}
		showNetworkInfo();
		isResponse = true;

	}

	private void configPppoe() {
		try {
			mEthernetManager.setEthernetMode(EthernetManager.ETHERNET_CONNECT_MODE_NONE, null);
			if (!mEthernetManager.isEthernetEnabled()) {
				mEthernetManager.setEthernetEnabled(true);
			}
			mEthernetManager.setEthernetState(EthernetManager.ETHERNET_STATE_DISABLED);
			String name = mUserName.getText().toString();
			String pass = mPass.getText().toString();
			log.d(name + " ===" + pass);
			if (!SWUtil.isEmpty(name) && !SWUtil.isEmpty(pass)) {
				mUiHandler.sendEmptyMessage(TIP_SHOW);
				mPppoeManager.setConfig(name, pass, ConnectivityManager.TYPE_ETHERNET);
				mPppoeManager.setPppoeEnabled(true);
			} else {
				mUiHandler.sendEmptyMessage(TIP_USERNAME);
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
		switch (v.getId()) {
		case R.id.retry:
			mPppoeHandler.sendEmptyMessage(CONFIG_PPPOE);
			break;
		case R.id.back:
			showPrePage();
			break;
		default:
			break;
		}
	}

	private class PppoeHandler extends Handler {
		public PppoeHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			configPppoe();
			super.handleMessage(msg);
		}
	}

}
