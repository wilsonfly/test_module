package net.sunniwell.app.shutdown;

import net.sunniwell.common.log.SWLogger;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * 
 * @author zhangdaili
 * 
 * @Date 2013-11-28
 */
public class PowerWindow extends Activity {
	private SWLogger log = SWLogger.getLogger(PowerWindow.class);
	private static final int WRAP = WindowManager.LayoutParams.WRAP_CONTENT;
	/** 常规的顶级窗口--该顶级view下的部件可以继续操作 */
	public static final int TYPE_TOPLEVEL_WIN_NORMAL = 1;
	/** 遮罩式(模式)顶级窗口--该顶级view下的部件被遮住，不可以操作 */
	public static final int TYPE_TOPLEVEL_WIN_MODAL = 2;
	private boolean isShow;
	private WindowManager wm;
	private RelativeLayout mLayout;
	private PowerManager mPowerManager;
	private BroadcastReceiver mPowerReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		registerPowerReciver();
	}

	private void registerPowerReciver() {
		mPowerReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				log.d("......action:" + action);
			}

		};
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(Intent.ACTION_SCREEN_OFF);
		mFilter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(mPowerReceiver, mFilter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			showPowerWin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(mPowerReceiver);
		super.onDestroy();
	}

	public void showPowerWin() {
		if (!isShow) {
			log.d("showPowerWin");
			isShow = true;
			wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
			mLayout = getView();
			WindowManager.LayoutParams mWmlp = getWinLayParams(TYPE_TOPLEVEL_WIN_MODAL);
			wm.addView(mLayout, mWmlp);
		}
	}

	private RelativeLayout getView() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = (RelativeLayout) inflater.inflate(R.layout.power, null);
		Button ok = (Button) mLayout.findViewById(R.id.ok);
		Button cancel = (Button) mLayout.findViewById(R.id.cancel);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (wm != null && mLayout != null) {
					wm.removeView(mLayout);
					gotoSleep();
					finish();
				}
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (wm != null && mLayout != null) {
					isShow = false;
					wm.removeView(mLayout);
					finish();
				}
			}

		});
		mLayout.requestFocus();
		ok.requestFocus();
		return mLayout;
	}

	private WindowManager.LayoutParams getWinLayParams(int type) {
		WindowManager.LayoutParams wlp = new WindowManager.LayoutParams(WRAP,
				WRAP);
		wlp.dimAmount = 0.0f;
		wlp.format = PixelFormat.TRANSPARENT;
		if (type == TYPE_TOPLEVEL_WIN_MODAL) {// 模式顶级窗口
			wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;// 任何部件都在其之下，可以通过dimAmount(0~1.0)设置透明度
			wlp.dimAmount = 0.5f;
		} else if (type == TYPE_TOPLEVEL_WIN_NORMAL) {// 常规顶级窗口
			wlp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不抢聚焦点
			wlp.dimAmount = 0.5f;
		}
		wlp.flags = wlp.flags
				| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
		wlp.flags = wlp.flags
				| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // allow

		wlp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT; // 系统提示类型
		wlp.width = 554;
		wlp.height = 354;
		wlp.gravity = Gravity.CENTER;
		return wlp;
	}

	/**
	 * 进入待机
	 */
	private void gotoSleep() {
		try {
			Intent intent = new Intent("net.sunniwell.action.GOTO_SLEEP");
			PowerWindow.this.sendBroadcast(intent);
			Thread.sleep(1000);
			WakeLock lWakeLock = mPowerManager.newWakeLock(
					PowerManager.PARTIAL_WAKE_LOCK, PowerWindow.this.getClass()
							.getSimpleName());
			lWakeLock.acquire();
			log.d("......start sleep......");
			mPowerManager.goToSleep(SystemClock.uptimeMillis());
			lWakeLock.release();
			log.d("......end sleep......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
