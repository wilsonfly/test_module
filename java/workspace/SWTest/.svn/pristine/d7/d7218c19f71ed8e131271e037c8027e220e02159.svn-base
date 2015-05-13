package net.sunniwell.app.test.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitleFragment extends ListFragment {
	public static final String[] TITLES = { "有线网络", "无线网络", "屏幕管理", "电源管理", "按键管理", "SWDB参数","RootService" };
	private static final String GOTO_ROOT_SERVICE = "net.suniwell.action.ROOTSERVICE_TEST";
	private String[] mDetailsClass;
	private boolean mDualPane;
	private int mCurCheckPosition;

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curPosition", mCurCheckPosition);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mDetailsClass = getResources().getStringArray(R.array.detailclass);
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, TITLES));
		View detailsFrame = getActivity().findViewById(R.id.details);
		mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
		if (savedInstanceState != null) {
			mCurCheckPosition = savedInstanceState.getInt("curPosition", 0);
		}
		if (mDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(mCurCheckPosition);
		}
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	private void showDetails(int position) {
		try {
			int index = position;
			if (mDualPane) {
				getListView().setItemChecked(index, true);
				Fragment df = getFragmentManager().findFragmentById(R.id.details);
				String className = mDetailsClass[index];
				if (className != null && GOTO_ROOT_SERVICE.equals(className)) {
					Intent rootservie = new Intent();
					rootservie.setAction(GOTO_ROOT_SERVICE);
					rootservie.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(rootservie);
					return;
				}
				if (className != null && !className.equals("")) {
					Class<?> clazz = Class.forName(className);
					df = (Fragment) clazz.newInstance();
				}
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.details, df);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
