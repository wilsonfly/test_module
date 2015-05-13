package net.sunniwell.app.test.base.swdb;

import net.sunniwell.app.test.base.R;
import net.sunniwell.app.test.base.SWFragment;
import net.sunniwell.app.test.base.util.SWUtil;
import net.sunniwell.common.log.SWLogger;
import android.app.DevInfoManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class SWdbPage extends SWFragment implements OnClickListener {
	private SWLogger log = SWLogger.getLogger(getClass());
	private EditText mSetParamName;
	private EditText mSetParamValue;
	private EditText mGetParamName;
	private EditText mGetParamValue;
	private DevInfoManager mDevInfoManager;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		mDevInfoManager = (DevInfoManager) getActivity().getSystemService(DevInfoManager.DATA_SERVER);
		View v = inflater.inflate(R.layout.swdb, null);
		v.requestFocus();
		mSetParamName = (EditText) v.findViewById(R.id.setparamname);
		mSetParamValue = (EditText) v.findViewById(R.id.setparamvalue);
		mGetParamName = (EditText) v.findViewById(R.id.getparamname);
		mGetParamValue = (EditText) v.findViewById(R.id.getparamvalue);
		v.findViewById(R.id.setok).setOnClickListener(this);
		v.findViewById(R.id.setcancel).setOnClickListener(this);
		v.findViewById(R.id.getok).setOnClickListener(this);
		v.findViewById(R.id.getcancel).setOnClickListener(this);
		initData();
		return v;
	}

	@Override
	public void onStop() {
		log.d("...onStop...");
		super.onStop();
	}

	private void initData() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setok:
			setParam();
			break;
		case R.id.setcancel:
			setParamCanel();
			break;
		case R.id.getok:
			getParam();
			break;
		case R.id.getcancel:
			getParamCanel();
			break;
		default:
			break;
		}
	}

	private void setParam() {
		String name = mSetParamName.getText().toString();
		String value = mSetParamValue.getText().toString();
		if (SWUtil.isEmpty(name) && SWUtil.isEmpty(value)) {
			Toast.makeText(getActivity(), "参数值和参数名不能为空!", Toast.LENGTH_LONG).show();
			return;
		}
		mDevInfoManager.update(name, value, DevInfoManager.Default_Attribute);
		Toast.makeText(getActivity(), "设置参数成功!", Toast.LENGTH_LONG).show();
	}

	private void setParamCanel() {
		mSetParamName.setText("");
		mSetParamValue.setText("");
	}

	private void getParam() {
		String name = mGetParamName.getText().toString();
		if (SWUtil.isEmpty(name)) {
			Toast.makeText(getActivity(), "读取参数名不能为空!", Toast.LENGTH_LONG).show();
			return;
		}
		mGetParamValue.setText(mDevInfoManager.getValue(name));
	}

	private void getParamCanel() {
		mGetParamName.setText("");
		mGetParamValue.setText("");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

}
