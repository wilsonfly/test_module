package net.sunniwell.app.test.base.network.wifi;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.common.log.SWLogger;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class StaticPage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private final String PRE_PAGE = StaticPage.class.getPackage().getName() + ".WifiPage";
	private final String POST_PAGE = StaticPage.class.getPackage().getName() + ".WifiConfigListPage";
	private EditText mIp;
	private EditText mMask;
	private EditText mGateway;
	private EditText mDns;
	private EditText mDns2;
	private View mTip;
	private TextView mResult;
	private View mMainView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		mMainView = inflater.inflate(R.layout.wifi_static, null);
		mMainView.requestFocus();
		mIp = (EditText) mMainView.findViewById(R.id.ip);
		mMask = (EditText) mMainView.findViewById(R.id.mask);
		mGateway = (EditText) mMainView.findViewById(R.id.gateway);
		mDns = (EditText) mMainView.findViewById(R.id.dns1);
		mDns2 = (EditText) mMainView.findViewById(R.id.dns2);
		mTip = mMainView.findViewById(R.id.tip);
		mResult = (TextView) mMainView.findViewById(R.id.result);
		mTip.setVisibility(View.INVISIBLE);
		mResult.setVisibility(View.INVISIBLE);
		mMainView.findViewById(R.id.ok).setOnClickListener(this);
		mMainView.findViewById(R.id.cancel).setOnClickListener(this);
		initData();
		return mMainView;
	}

	@Override
	public void onStop() {
		log.d("...onStop...");
		super.onStop();
	}

	private void initData() {
		ContentResolver cr = getActivity().getContentResolver();
		int defaultmode = Settings.Secure.getInt(cr, "default_wifi_mod", 0);
		String ip = Settings.Secure.getString(cr, "wifi_static_ip");
		String gateway = Settings.Secure.getString(cr, "wifi_static_gateway");
		String mask = Settings.Secure.getString(cr, "wifi_static_mask");
		String dns = Settings.Secure.getString(cr, "wifi_static_dns");
		String dns2 = Settings.Secure.getString(cr, "wifi_static_dns2");
		mIp.setText(ip);
		mMask.setText(mask);
		mGateway.setText(gateway);
		mDns.setText(dns);
		mDns2.setText(dns2);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showPrePage();
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ok:
			showPostPage();
			break;
		case R.id.cancel:
			showPrePage();
			break;
		default:
			break;
		}
	}

	private void showPostPage() {
		String ip = mIp.getEditableText().toString();
		String mask = mMask.getEditableText().toString();
		String gateway = mGateway.getEditableText().toString();
		String dns1 = mDns.getEditableText().toString();
		String dns2 = mDns2.getEditableText().toString();
		ip = ip == null ? "0.0.0.0" : ip;
		mask = mask == null ? "0.0.0.0" : mask;
		gateway = gateway == null ? "0.0.0.0" : gateway;
		dns1 = dns1 == null ? "0.0.0.0" : dns1;
		dns2 = dns2 == null ? "0.0.0.0" : dns2;
		ContentResolver cr = getActivity().getContentResolver();
		Settings.Secure.putString(cr, "wifi_static_ip", ip);
		Settings.Secure.putString(cr, "wifi_static_gateway", gateway);
		Settings.Secure.putString(cr, "wifi_static_mask", mask);
		Settings.Secure.putString(cr, "wifi_static_dns", dns1);
		Settings.Secure.putString(cr, "wifi_static_dns2", dns2);
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
			b.putString("prePage", StaticPage.class.getName());
			b.putInt("wifimode", 0);
			fragment.setArguments(b);
			ft.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
