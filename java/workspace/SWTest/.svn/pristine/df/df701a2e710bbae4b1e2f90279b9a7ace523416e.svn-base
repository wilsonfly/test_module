package net.sunniwell.app.test.base.network.eth;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class EthernetPage extends SWFragment implements OnClickListener {
	private final String PACAGENAME = EthernetPage.class.getPackage().getName();
	private final String[] POST_PAGES = new String[] { "StaticPage", "DhcpPage", "PppoePage" };
	private final String PRE_PAGE = "SpecialNetworkChoicePage";
	private EthernetManager mEthernetManager;
	private PppoeManager mPppoeManager;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View v = inflater.inflate(R.layout.ethernet, null);
		v.findViewById(R.id.ethstatic).setOnClickListener(this);
		v.findViewById(R.id.ethdhcp).setOnClickListener(this);
		v.findViewById(R.id.ethpppoe).setOnClickListener(this);
		mEthernetManager = (EthernetManager) getActivity().getSystemService(Context.ETHERNET_SERVICE);
		mPppoeManager = (PppoeManager) getActivity().getSystemService(Context.PPPOE_SERVICE);
		v.requestFocus();
		return v;
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
		int index = 0;
		switch (v.getId()) {
		case R.id.ethstatic:
			index = 0;
			break;
		case R.id.ethdhcp:
			index = 1;
			break;
		case R.id.ethpppoe:
			index = 2;
			break;
		default:
			break;
		}
		showPostPage(index);
	}

	private void showPrePage() {
		if (mEthernetManager.isSpecialNetwork() || mPppoeManager.isSpecialNetwork()) {
			showPage("SpecialPage");
		} else {
			showPage(PRE_PAGE);
		}
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
