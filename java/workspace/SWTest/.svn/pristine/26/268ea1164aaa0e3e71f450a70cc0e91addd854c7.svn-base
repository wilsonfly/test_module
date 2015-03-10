package net.sunniwell.app.test.base.screen;

import java.util.Arrays;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.view.SWPopupMenu;
import net.sunniwell.app.test.base.view.SWPopupMenu.OnMenuItemClickListener;
import net.sunniwell.common.log.SWLogger;
import android.content.Context;
import android.os.Bundle;
import android.os.display.DisplayManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author zhangdaili
 * 
 * @date 2014-1-18
 */
public class ScreenPage extends SWFragment implements OnSeekBarChangeListener, OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private SWPopupMenu mPopupMenu;
	private RelativeLayout mMain;
	private Button mStandard;
	private Button mOK;
	private Button mCancel;
	private TextView mTopValue;
	private TextView mBottomValue;
	private TextView mLeftValue;
	private TextView mRightValue;
	private DisplayManager mDisplayManager;
	private String[] mStandardValues;
	private SeekBar mTop;
	private SeekBar mBottom;
	private SeekBar mLeft;
	private SeekBar mRight;
	private int[] mOldScreenMargins;
	private int mOldStandard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		mStandardValues = getResources().getStringArray(R.array.standardValues);
		View v = inflater.inflate(R.layout.screen, null);
		// v.requestFocus();
		mMain = (RelativeLayout) v.findViewById(R.id.screen_main);
		mStandard = (Button) v.findViewById(R.id.standardValue);
		mOK = (Button) v.findViewById(R.id.ok);
		mCancel = (Button) v.findViewById(R.id.cancel);
		mTopValue = (TextView) v.findViewById(R.id.top_value);
		mBottomValue = (TextView) v.findViewById(R.id.bottom_value);
		mLeftValue = (TextView) v.findViewById(R.id.left_value);
		mRightValue = (TextView) v.findViewById(R.id.right_value);
		mTop = (SeekBar) v.findViewById(R.id.top);
		mBottom = (SeekBar) v.findViewById(R.id.bottom);
		mLeft = (SeekBar) v.findViewById(R.id.left);
		mRight = (SeekBar) v.findViewById(R.id.right);
		mStandard.setOnClickListener(this);
		mOK.setOnClickListener(this);
		mCancel.setOnClickListener(this);
		mTop.setOnSeekBarChangeListener(this);
		mBottom.setOnSeekBarChangeListener(this);
		mLeft.setOnSeekBarChangeListener(this);
		mRight.setOnSeekBarChangeListener(this);
		mDisplayManager = (DisplayManager) getActivity().getSystemService(Context.DISPLAY_MANAGER_SERVICE);
		mPopupMenu = new SWPopupMenu(getActivity(), mStandardValues);
		mOldStandard = mDisplayManager.getCurrentStandard();
		mOldScreenMargins = mDisplayManager.getScreenMargin();
		initData();
		return v;
	}

	private void initData() {
		int standard = -1;
		standard = mDisplayManager.getCurrentStandard();
		log.d("standard===" + standard);
		if (standard > 12) {
			standard = standard - 13;
		} else if (standard <= 12) {
			standard = standard + 4;
		}
		log.d("standard===" + standard);
		standard = standard == -1 ? 0 : standard;
		int[] margins = mDisplayManager.getScreenMargin();
		if (margins == null) {
			margins = new int[] { 0, 0, 0, 0 };
		}
		mStandard.setText(mStandardValues[standard]);
		log.d("margins[1]===" + margins[1]);
		mLeft.setProgress(margins[0] / 4);
		mTop.setProgress(margins[1] / 4);
		mRight.setProgress(margins[2] / 4);
		mBottom.setProgress(margins[3] / 4);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			mDisplayManager.setDisplayStandard(mOldStandard);
			mDisplayManager.setScreenMargin(mOldScreenMargins[0], mOldScreenMargins[1], mOldScreenMargins[2], mOldScreenMargins[3]);
			mDisplayManager.saveParams();
			break;
		default:
			break;
		}
		return false;

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		int[] margins = mDisplayManager.getScreenMargin();
		Log.d("DisplayManager", Arrays.toString(margins)+"");
		switch (seekBar.getId()) {
		case R.id.top:
			mDisplayManager.setScreenMargin(margins[0], progress * 4, margins[2], margins[3]);
			mTopValue.setText(String.valueOf(progress * 4));
			break;
		case R.id.bottom:
			mDisplayManager.setScreenMargin(margins[0], margins[1], margins[2], progress * 4);
			mBottomValue.setText(String.valueOf(progress * 4));
			break;
		case R.id.left:
			mDisplayManager.setScreenMargin(progress * 4, margins[1], margins[2], margins[3]);
			mLeftValue.setText(String.valueOf(progress * 4));
			break;
		case R.id.right:
			mDisplayManager.setScreenMargin(margins[0], margins[1], progress * 4, margins[3]);
			mRightValue.setText(String.valueOf(progress * 4));
			break;
		default:
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.standardValue:
			showMenu();
			break;
		case R.id.ok:
			mDisplayManager.saveParams();
			mOldStandard = mDisplayManager.getCurrentStandard();
			mOldScreenMargins = mDisplayManager.getScreenMargin();
			break;
		case R.id.cancel:
			mDisplayManager.setDisplayStandard(mOldStandard);
			mDisplayManager.setScreenMargin(mOldScreenMargins[0], mOldScreenMargins[1], mOldScreenMargins[2], mOldScreenMargins[3]);
			mDisplayManager.saveParams();
			initData();
		default:
			break;
		}
	}

	/**
	 * 显示操作菜单
	 */
	public void showMenu() {
		mPopupMenu.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.screen_background_dark));
		mPopupMenu.setSelection(0);
		mPopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onMenuItemClick(AdapterView<?> parent, View v, int position, long id) {
				handleMenuItemClick(position);
			}

			private void handleMenuItemClick(int position) {
				int standard = position;
				log.d("position ===" + position);
				if (standard > 3) {
					standard = standard - 4;
				} else if (standard <= 3) {
					standard = standard + 13;
				}
				boolean isSurpport = mDisplayManager.isSupportStandard(standard);
				log.d("isSurpport ===" + isSurpport);
				if (!isSurpport) {
					Toast.makeText(ScreenPage.this.getActivity(), "不支持 " + mStandardValues[position], Toast.LENGTH_LONG).show();
					return;
				}
				mDisplayManager.setDisplayStandard(standard);
				initData();
			}

		});
		try {

		} catch (Exception e) {
			// TODO: handle exception

		}
		mPopupMenu.showAtLocation(mMain, Gravity.TOP | Gravity.LEFT, 370, 180);
	}

}
