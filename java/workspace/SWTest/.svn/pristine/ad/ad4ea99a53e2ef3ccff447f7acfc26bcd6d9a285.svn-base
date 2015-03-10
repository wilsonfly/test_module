package net.sunniwell.app.test.base.network.eth;

import java.util.ArrayList;
import java.util.List;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.IPUtils;
import net.sunniwell.common.log.SWLogger;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.DhcpInfoInternal;
import android.net.NetworkUtils;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridLayout;

public class SpecialPage extends SWFragment implements OnClickListener, OnCheckedChangeListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private CheckBox mEthSpecial;
	private CheckBox mPppoeSpecial;
	private GridLayout mEthSpecialInfo;
	private GridLayout mPppoeSpecialInfo;
	private EthernetManager mEthernetManager;
	private PppoeManager mPppoeManager;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		log.d("keycode ==" + keyCode);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showPrePage();
			return true;
		}
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.eth_special_config, null);
		mEthSpecial = (CheckBox) v.findViewById(R.id.ethspecial);
		mPppoeSpecial = (CheckBox) v.findViewById(R.id.pppoespecial);
		mEthSpecial.setOnCheckedChangeListener(this);
		mPppoeSpecial.setOnCheckedChangeListener(this);
		mEthSpecialInfo = (GridLayout) v.findViewById(R.id.ethspecialinfo);
		mPppoeSpecialInfo = (GridLayout) v.findViewById(R.id.pppoespecialinfo);
		v.findViewById(R.id.ok).setOnClickListener(this);
		v.findViewById(R.id.back).setOnClickListener(this);
		mEthernetManager = (EthernetManager) getActivity().getSystemService(Context.ETHERNET_SERVICE);
		mPppoeManager = (PppoeManager) getActivity().getSystemService(Context.PPPOE_SERVICE);
		initData();
		v.requestFocus();
		return v;
	}

	private void initData() {
		try {
			mEthSpecial.setChecked(mEthernetManager.isSpecialNetwork());
			mPppoeSpecial.setChecked(mPppoeManager.isSpecialNetwork());
			if (mEthSpecial.isChecked()) {
				List<DhcpInfo> dhcpinfos = mEthernetManager.getSpecialList();
				int index = 3;
				for (DhcpInfo di : dhcpinfos) {
					if (index < mEthSpecialInfo.getChildCount()) {
						EditText route = (EditText) mEthSpecialInfo.getChildAt(index);
						route.setEnabled(true);
						route.setText(NetworkUtils.intToInetAddress(di.ipAddress).toString().replace("/", ""));
						index += 2;
						EditText mask = (EditText) mEthSpecialInfo.getChildAt(index);
						mask.setEnabled(true);
						mask.setText(NetworkUtils.intToInetAddress(di.netmask).toString().replace("/", ""));
						index += 2;
					}
				}
			} else {
				for (int index = 3; index < mEthSpecialInfo.getChildCount(); index = index + 2) {
					EditText tmp = (EditText) mEthSpecialInfo.getChildAt(index);
					tmp.setEnabled(false);
				}
			}

			if (mPppoeSpecial.isChecked()) {
				List<DhcpInfo> dhcpinfos = mPppoeManager.getSpecialList();
				int index = 3;
				for (DhcpInfo di : dhcpinfos) {
					if (index < mPppoeSpecialInfo.getChildCount()) {
						EditText route = (EditText) mPppoeSpecialInfo.getChildAt(index);
						route.setEnabled(true);
						route.setFocusable(true);
						route.setText(NetworkUtils.intToInetAddress(di.ipAddress).toString().replace("/", ""));
						index += 2;
						EditText mask = (EditText) mPppoeSpecialInfo.getChildAt(index);
						mask.setEnabled(true);
						mask.setFocusable(true);
						mask.setText(NetworkUtils.intToInetAddress(di.netmask).toString().replace("/", ""));
						index += 2;
					}
				}
			} else {
				for (int index = 3; index < mPppoeSpecialInfo.getChildCount(); index = index + 2) {
					EditText tmp = (EditText) mPppoeSpecialInfo.getChildAt(index);
					tmp.setEnabled(false);
					tmp.setFocusable(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ok:
			configSpecialInfo();
			showPostPage();
			break;
		case R.id.back:
			showPrePage();
			break;
		default:
			break;
		}
	}

	private void showPrePage() {
		showPage("SpecialNetworkChoicePage");
	}

	private void showPostPage() {
		showPage("EthernetPage");
	}

	private void showPage(String simpleClassName) {
		try {
			StringBuilder className = new StringBuilder();
			className.append(SpecialPage.class.getPackage().getName());
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

	private void configSpecialInfo() {
		if (mEthSpecial.isChecked()) {
			mEthernetManager.setSpecialNetwork(true);
			mEthernetManager.setSpecialRoutesIsManual(true);
			List<DhcpInfo> dhcpinfos = new ArrayList<DhcpInfo>();
			for (int index = 3; index < mEthSpecialInfo.getChildCount();) {
				DhcpInfoInternal di = new DhcpInfoInternal();
				EditText route = (EditText) mEthSpecialInfo.getChildAt(index);
				String routeValue = route.getText().toString();
				log.d("ip:" + routeValue);
				boolean isAdd = false;
				if (IPUtils.checkIP(routeValue)) {
					log.d("ip:" + routeValue);
					di.ipAddress = routeValue;
					isAdd = true;
				} else {
					isAdd = false;
				}
				index += 2;
				EditText mask = (EditText) mEthSpecialInfo.getChildAt(index);
				String maskValue = mask.getText().toString();
				if (IPUtils.checkMask(maskValue)) {
					di.prefixLength = IPUtils.getNetmaskLength(maskValue);
					if (isAdd) {
						isAdd = true;
					}
				} else {
					isAdd = false;
				}
				log.d("dhcpinfo===== ipAddress:" + di.makeDhcpInfo().ipAddress);
				dhcpinfos.add(di.makeDhcpInfo());
				index += 2;
			}
			if (dhcpinfos.size() > 0) {
				mEthernetManager.setSpecialList(dhcpinfos);
			}
		}
		if (mPppoeSpecial.isChecked()) {
			mPppoeManager.setSpecialNetwork(true);
			mPppoeManager.setSpecialRoutesIsManual(true);
			List<DhcpInfo> dhcpinfos = new ArrayList<DhcpInfo>();
			for (int index = 3; index < mPppoeSpecialInfo.getChildCount();) {
				DhcpInfoInternal di = new DhcpInfoInternal();
				EditText route = (EditText) mPppoeSpecialInfo.getChildAt(index);
				String routeValue = route.getText().toString();
				boolean isAdd = false;
				if (IPUtils.checkIP(routeValue)) {
					di.ipAddress = routeValue;
					isAdd = true;
				} else {
					isAdd = false;
				}
				index += 2;
				EditText mask = (EditText) mPppoeSpecialInfo.getChildAt(index);
				String maskValue = mask.getText().toString();
				if (IPUtils.checkMask(maskValue)) {
					di.prefixLength = IPUtils.getNetmaskLength(maskValue);
					if (isAdd) {
						isAdd = true;
					}
				} else {
					isAdd = false;
				}
				dhcpinfos.add(di.makeDhcpInfo());
				index += 2;
			}
			if (dhcpinfos.size() > 0) {
				mPppoeManager.setSpecialList(dhcpinfos);
			}
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (mEthSpecial.isChecked()) {
			mEthernetManager.setSpecialNetwork(true);
			mEthernetManager.setSpecialRoutesIsManual(true);
		} else {
			mEthernetManager.setSpecialNetwork(false);
			mEthernetManager.setSpecialRoutesIsManual(false);
		}
		for (int index = 3; index < mEthSpecialInfo.getChildCount(); index = index + 2) {
			EditText tmp = (EditText) mEthSpecialInfo.getChildAt(index);
			tmp.setEnabled(mEthSpecial.isChecked());
			tmp.setFocusable(mEthSpecial.isChecked());
		}
		if (mPppoeSpecial.isChecked()) {
			mPppoeManager.setSpecialNetwork(true);
			mPppoeManager.setSpecialRoutesIsManual(true);
		} else {
			mPppoeManager.setSpecialNetwork(false);
			mPppoeManager.setSpecialRoutesIsManual(false);
		}
		for (int index = 3; index < mPppoeSpecialInfo.getChildCount(); index = index + 2) {
			EditText tmp = (EditText) mPppoeSpecialInfo.getChildAt(index);
			tmp.setEnabled(mPppoeSpecial.isChecked());
			tmp.setFocusable(mPppoeSpecial.isChecked());
		}

	}

}
