package net.sunniwell.app.test.base.network.wifi;

import java.util.List;

import net.sunniwell.app.test.base.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class WifiAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private List<AccessPoint> mAccessPoints;
	private Context mContext;
	private int temp = -1;

	public WifiAdapter(Context context, List<AccessPoint> data) {
		this.mAccessPoints = data;
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (mAccessPoints.size() > 0) {
			return mAccessPoints.size() + 1;
		}
		return mAccessPoints.size();
	}

	@Override
	public Object getItem(int pos) {
		return mAccessPoints.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.network_wifi_list,
					null);
			holder.mCheckTag = (RadioButton) convertView
					.findViewById(R.id.wifi_check);
			holder.mWifissid = (TextView) convertView
					.findViewById(R.id.wifi_ssid);
			holder.mWifiSecure = (ImageView) convertView
					.findViewById(R.id.wifi_lock);
			holder.mWifiLock = (ImageView) convertView
					.findViewById(R.id.wifi_secure);
			holder.mSumary = (TextView) convertView
					.findViewById(R.id.wifi_summory);
			holder.mWifiButton = (TextView) convertView
					.findViewById(R.id.wifi_add);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (pos == mAccessPoints.size()) {// 添加按钮
			holder.mWifiButton.setVisibility(View.VISIBLE);
			holder.mCheckTag.setVisibility(View.GONE);
			holder.mSumary.setVisibility(View.GONE);
			holder.mWifissid.setVisibility(View.GONE);
			holder.mWifiSecure.setVisibility(View.GONE);
			holder.mWifiLock.setVisibility(View.GONE);
		} else {
			holder.mWifiButton.setVisibility(View.GONE);
			holder.mCheckTag.setVisibility(View.VISIBLE);
			holder.mSumary.setVisibility(View.VISIBLE);
			holder.mWifissid.setVisibility(View.VISIBLE);
			holder.mWifiSecure.setVisibility(View.VISIBLE);
			holder.mWifiLock.setVisibility(View.VISIBLE);
			AccessPoint mPoint = mAccessPoints.get(pos);
			holder.mWifissid.setText(mPoint.getSsid());
			holder.mSumary.setText(mPoint.getSummary());
			if (mPoint.getmRssi() == Integer.MAX_VALUE) {
				holder.mWifiSecure.setImageDrawable(null);
			}
			if (mPoint.getSecurity() == AccessPoint.SECURITY_NONE
					|| mPoint.getSummary().equals("Open")) {
				holder.mWifiLock.setVisibility(View.INVISIBLE);
			}
			holder.mWifiSecure.setImageResource(R.drawable.wifi_signal_open);
			holder.mWifiSecure.setImageLevel(mPoint.getLevel());
			if (temp == pos) {
				holder.mCheckTag.setBackgroundResource(R.drawable.icon_radio);
			} else {
				holder.mCheckTag
						.setBackgroundResource(R.drawable.icon_radio_no);
			}
		}
		return convertView;
	}

	public final class ViewHolder {
		public RadioButton mCheckTag;
		public TextView mSumary;
		public TextView mWifissid;
		public ImageView mWifiSecure;
		public ImageView mWifiLock;
		public TextView mWifiButton; // 添加按钮
	}

	public void gotoWifiConfigPage() {
		Intent intent = new Intent();
		intent.setAction("net.sunniwell.action.WIFI_CONN");
		intent.putExtra("isConn", false);
		if (mContext instanceof Activity) {
			((Activity) mContext).startActivity(intent);
		}
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

}
