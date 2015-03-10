package net.sunniwell.app.test.base.network.eth;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class SpecialNetworkChoicePage extends SWFragment implements OnClickListener {
	private final String PACAGENAME = SpecialNetworkChoicePage.class.getPackage().getName();
	private final String[] POST_PAGES = new String[] { "SpecialPage", "EthernetPage" };

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View v = inflater.inflate(R.layout.eth_special_choice, null);
		v.findViewById(R.id.special).setOnClickListener(this);
		v.findViewById(R.id.nospecial).setOnClickListener(this);
		// v.requestFocus();
		return v;
	}

	@Override
	public void onClick(View v) {
		int index = 0;
		switch (v.getId()) {
		case R.id.special:
			index = 0;
			break;
		case R.id.nospecial:
			index = 1;
			break;
		default:
			break;
		}
		showPostPage(index);
	}

	private void showPostPage(int index) {
		try {
			StringBuilder className = new StringBuilder();
			className.append(PACAGENAME);
			className.append(".");
			className.append(POST_PAGES[index]);
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
