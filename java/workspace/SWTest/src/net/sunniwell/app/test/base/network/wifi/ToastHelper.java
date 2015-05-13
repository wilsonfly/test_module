package net.sunniwell.app.test.base.network.wifi;

import net.sunniwell.app.test.base.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public final class ToastHelper {
	public static void showMessage(Context context, int res) {
		showMessage(context, res, true, Toast.LENGTH_LONG);
	}

	public static void showMessage(Context context, CharSequence msg) {
		showMessage(context, msg, true, Toast.LENGTH_LONG);
	}

	public static void showMessage(Context context, boolean isSuccess, int res) {
		showMessage(context, res, isSuccess, Toast.LENGTH_LONG);
	}

	public static void showMessage(Context context, boolean isSuccess, CharSequence msg) {
		showMessage(context, msg, isSuccess, Toast.LENGTH_LONG);
	}

	public static void showMessage(Context context, int resId, boolean isSuccess, int duration) {
		showMessage(context, buildToastLayout(context, context.getString(resId), isSuccess), duration);
	}

	public static void showMessage(Context context, CharSequence msg, boolean isSuccess, int duration) {
		showMessage(context, buildToastLayout(context, msg, isSuccess), duration);
	}

	private static Toast mLastToast;

	public static void showMessage(Context context, View view, int duration) {
		if (mLastToast != null)
			mLastToast.cancel();
		mLastToast = null;
		final Toast t = new Toast(context);
		t.setView(view);
		t.setDuration(duration);
		t.setGravity(Gravity.BOTTOM, 0, 0);
		t.show();
		mLastToast = t;
	}

	private static View buildToastLayout(Context context, CharSequence msg, boolean issuccess) {
		LinearLayout lv = new LinearLayout(context);
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		layoutInflater.inflate(R.layout.toast, lv);
		TextView tv = (TextView) lv.findViewById(R.id.toast_content);
		ImageView iv = (ImageView) lv.findViewById(R.id.toast_image);
		if (issuccess)
			iv.setImageResource(R.drawable.toast_success);
		else
			iv.setImageResource(R.drawable.toast_error);

		tv.setText(msg);
		return lv;
	}

}
