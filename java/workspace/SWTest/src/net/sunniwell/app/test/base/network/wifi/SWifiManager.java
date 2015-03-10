package net.sunniwell.app.test.base.network.wifi;

import java.util.ArrayList;
import java.util.List;

import net.sunniwell.app.test.base.util.IPUtils;
import net.sunniwell.common.log.SWLogger;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.AuthAlgorithm;
import android.net.wifi.WifiConfiguration.IpAssignment;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiConfiguration.Protocol;
import android.net.wifi.WifiConfiguration.ProxySettings;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.provider.Settings.Global;
import android.util.Log;

/**
 * 无线网络接口封装管理类
 * 
 * @author jiangshengkun
 * 
 */
public class SWifiManager {
	private SWLogger log = SWLogger.getLogger(getClass());
	private static final int WIFI_RESCAN_INTERVAL_MS = 10 * 1000;
	private static SWifiManager sManager = null;
	public static final int GOTO_CONNECT_PRE_SSID = 0;
	private static final int CONNECT_PRE_SSID = 1;
	public static final int SEND_MSG_TO_STATUSBAR = 2;
	private static final int REMOVE_NOTIFATION = 3;
	private Context ctx;
	private IntentFilter mFilter;
	private WifiManager mWifiManager;
	private Scanner mScanner;
	private Handler mWifiHandler;
	private int mPreNetworkId;
	private int mCurrentNetworkId;
	private String mCurrentSSID;
	private static WifiNetworkSetting mNetwork = null;
	private static WifiConfiguration mWifiConfig = null;
	private static AccessPoint mCurrentAccessPoint = null;
	private String mWifiPassword = null; // 暂存wifi密码
	public static final String NET_ACTION = "android.sunniwell.Wifi_STATE_CHANGED";
	public static final String NET_ACTION_MESSAGE = "message";

	/**
	 * Wi-Fi is currently being disabled. The state will change to
	 * {@link #WIFI_STATE_DISABLED} if it finishes successfully.
	 * 
	 * @see #WIFI_STATE_CHANGED_ACTION
	 * @see #getWifiState()
	 */
	public static final int WIFI_STATE_DISABLING = 0;
	/**
	 * Wi-Fi is disabled.
	 * 
	 * @see #WIFI_STATE_CHANGED_ACTION
	 * @see #getWifiState()
	 */
	public static final int WIFI_STATE_DISABLED = 1;
	/**
	 * Wi-Fi is currently being enabled. The state will change to
	 * {@link #WIFI_STATE_ENABLED} if it finishes successfully.
	 * 
	 * @see #WIFI_STATE_CHANGED_ACTION
	 * @see #getWifiState()
	 */
	public static final int WIFI_STATE_ENABLING = 2;
	/**
	 * Wi-Fi is enabled.
	 * 
	 * @see #WIFI_STATE_CHANGED_ACTION
	 * @see #getWifiState()
	 */
	public static final int WIFI_STATE_ENABLED = 3;
	/**
	 * Wi-Fi is in an unknown state. This state will occur when an error happens
	 * while enabling or disabling.
	 * 
	 * @see #WIFI_STATE_CHANGED_ACTION
	 * @see #getWifiState()
	 */
	public static final int WIFI_STATE_UNKNOWN = 4;

	/**
	 * disable Reason
	 */
	public static final int DISABLED_UNKNOWN_REASON = 0;
	/**
	 * disable Reason
	 */
	public static final int DISABLED_DNS_FAILURE = 1;
	/**
	 * disable Reason
	 */
	public static final int DISABLED_DHCP_FAILURE = 2;
	/**
	 * disable Reason
	 */
	public static final int DISABLED_AUTH_FAILURE = 3;

	/** Possible status of a network configuration. */
	public static class Status {
		private Status() {
		}

		/** this is the network we are currently connected to */
		public static final int CURRENT = 0;
		/** supplicant will not attempt to use this network */
		public static final int DISABLED = 1;
		/** supplicant will consider this network available for association */
		public static final int ENABLED = 2;

		public static final String[] strings = { "current", "disabled", "enabled" };
	}

	private SWifiManager(Context context) {
		this.ctx = context;
		if (mWifiManager == null) {
			mWifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
		}
		if (mScanner == null) {
			mScanner = new Scanner();
		}
		mPreNetworkId = -1;
		mWifiHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				ConnectivityManager connManager = (ConnectivityManager) SWifiManager.this.ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (msg.what == GOTO_CONNECT_PRE_SSID) {
					if (mCurrentSSID != null) {
						if ((mWifi == null || !mWifi.isConnected()) && mWifiManager.isWifiEnabled()) {
							String mesg = "connect to " + mCurrentSSID + " failed, please try it again!";
							sendMsgToStatusbar(mesg);
							if (hasMessages(CONNECT_PRE_SSID)) {
								removeMessages(CONNECT_PRE_SSID);
							}
							sendEmptyMessageDelayed(CONNECT_PRE_SSID, 3 * 1000);
							mCurrentSSID = null;
						}
					}
				} else if (msg.what == CONNECT_PRE_SSID) {
					// if (mWifi != null && !mWifi.isConnected() &&
					// mWifiManager.isWifiEnabled() && mPreNetworkId != -1) {
					// mWifiManager.removeNetwork(mCurrentNetworkId);
					// mCurrentNetworkId = mPreNetworkId;
					// mWifiManager.enableNetwork(mPreNetworkId, true);
					// mPreNetworkId = -1;
					// } else if (mPreNetworkId == -1 && mCurrentNetworkId != -1
					// && !mWifi.isConnected() && mWifiManager.isWifiEnabled())
					// {
					// mWifiManager.removeNetwork(mCurrentNetworkId);
					// mWifiManager.saveConfiguration();
					// mCurrentNetworkId = -1;
					// }
				} else if (msg.what == SEND_MSG_TO_STATUSBAR) {
					String message = (String) msg.obj;
					sendMsgToStatusbar(message);
				} else if (msg.what == REMOVE_NOTIFATION) {
					NotificationManager nm = (NotificationManager) msg.obj;
					nm.cancel(0);
				}
			}
		};
	}

	/**
	 * 无线网络接口封装管理类实例
	 * 
	 * @return SWifiManager
	 */
	public static SWifiManager getInstance(Context context) {
		if (sManager == null) {
			sManager = new SWifiManager(context);
		}
		if (mNetwork == null) {
			mNetwork = new WifiNetworkSetting();
		}
		if (mWifiConfig == null) {
			mWifiConfig = new WifiConfiguration();
		}
		return sManager;
	}

	/**
	 * 获取系统的 WiFi MAC
	 * 
	 * @return string
	 */
	public String getMac() {
		if (mWifiManager != null) {
			Log.d("SWifiManager", "157");
			WifiInfo info = mWifiManager.getConnectionInfo();
			Log.d("SWifiManager", "159" + info.getMacAddress());
			return info.getMacAddress();
		} else
			return "";
	}

	/**
	 * 注册网络广播,监听的事件有如下
	 */
	public IntentFilter registerBroadcast() {
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		mFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
		mFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.CONFIGURED_NETWORKS_CHANGED_ACTION);
		mFilter.addAction(WifiManager.LINK_CONFIGURATION_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		return mFilter;
	}

	/**
	 * 取消注册的广播
	 */
	public void unRegisterBroadcast() {
		mFilter = null;
	}

	/**
	 * Gets the Wi-Fi enabled state.
	 * 
	 * @return One of {@link #WIFI_STATE_DISABLED},
	 *         {@link #WIFI_STATE_DISABLING}, {@link #WIFI_STATE_ENABLED},
	 *         {@link #WIFI_STATE_ENABLING}, {@link #WIFI_STATE_UNKNOWN}
	 * @see #isWifiEnabled()
	 */
	public int getWifiState() {
		try {
			return mWifiManager.getWifiState();
		} catch (Exception e) {
			return 4;
		}
	}

	/**
	 * 打开WIFI
	 */
	public void openWifi() {
		if (!mWifiManager.isWifiEnabled())
			mWifiManager.setWifiEnabled(true);
		log.d("=======openWifi==end===");
	}

	/**
	 * 关闭 WIFI
	 */
	public void closeWifi() {
		if (mWifiManager.isWifiEnabled())
			mWifiManager.setWifiEnabled(false);
		log.d("=======closeWifi==end===");
	}

	/**
	 * Return whether Wi-Fi is enabled or disabled.
	 * 
	 * @return {@code true} if Wi-Fi is enabled
	 * @see #getWifiState()
	 */
	public boolean isWifiEnabled() {
		return getWifiState() == WIFI_STATE_ENABLED;
	}

	/**
	 * 启动 Wifi 搜索
	 */
	public void startWifiScan() {
		if (mWifiManager.isWifiEnabled()) {
			log.d(".....mScanner.forceScan()....");
			mScanner.forceScan();
		}
	}

	/**
	 * 停止 Wi-Fi 搜索
	 */
	public void stopWifiScan() {
		if (mWifiManager.isWifiEnabled()) {
			log.d(".....mScanner.pause()....");
			mScanner.pause();
		}
	}

	/**
	 * 恢复 Wi-Fi 搜索
	 */
	public void resumeWifiScan() {
		if (mWifiManager.isWifiEnabled()) {
			log.d(".....mScanner.resume()....");
			mScanner.resume();
		}
	}

	/**
	 * 
	 * @return 0 , 1 ,2
	 */
	public int getCurrentWifiStatus() {
		if (mWifiConfig != null) {
			return mWifiConfig.status;
		} else
			return 3;
	}

	/**
	 * The code referring to a reason for disabling the network Valid when
	 * {@link #status} == Status.DISABLED
	 * 
	 * @return 
	 *         DISABLED_UNKNOWN_REASON,DISABLED_DNS_FAILURE,DISABLED_DHCP_FAILURE
	 *         ,DISABLED_AUTH_FAILURE
	 */
	public int getDisableReason() {
		if (mWifiConfig != null) {
			return mWifiConfig.disableReason;
		} else {
			return DISABLED_UNKNOWN_REASON;
		}
	}

	/**
	 * 获取搜索到的所有 wifi 信息
	 * 
	 * @return List<AccessPointN>
	 */
	public List<AccessPoint> getWifiList() {
		List<AccessPoint> accessPointLists = new ArrayList<AccessPoint>();
		List<ScanResult> results = mWifiManager.getScanResults();
		if (results != null) {
			for (ScanResult result : results) {
				// Ignore hidden and ad-hoc networks.
				AccessPoint accessPoint = new AccessPoint(ctx, result);
				accessPointLists.add(accessPoint);
			}
		}
		return accessPointLists;
	}

	/**
	 * 连接网络前 设置连接的网络SSID
	 * 
	 * @param ssid
	 * @return true:设置正确，false：ssid设置错误
	 */
	public boolean setSSID(String ssid) {
		List<AccessPoint> accessPointLists = getWifiList();
		boolean isTrue = false;
		log.d("...>>>>>>>>>>ssid=" + ssid);
		if (mWifiConfig != null) {
			log.d(">>mWifiConfig!=null>>>>>>>>>>ssid=");
			if (ssid != null && !ssid.equals("")) {
				if (accessPointLists != null && accessPointLists.size() > 0) {
					for (AccessPoint accessPoint : accessPointLists) {
						if (accessPoint.getSsid().equals(ssid)) {
							mWifiConfig.SSID = ssid;
							mCurrentAccessPoint = accessPoint;
							log.d(">>>>>>>>>>>>ssid=" + mWifiConfig.SSID);
							isTrue = true;
						}
					}
				}
			} else {
				log.d("...error..ssid=" + ssid);
			}
		} else
			log.d("...mWifiConfig..error...");
		return isTrue;
	}

	/**
	 * 连接网络前要求设置Wifi 网络模式
	 * 
	 * @param mode
	 *            static:静态，dhcp：动态
	 * @return true:设置正确，false：网络模式设置错误
	 */
	public boolean setMode(String mode) {
		boolean isTrue = false;
		if (mWifiConfig != null) {
			if (mode != null && !mode.equals("")) {
				if (mode.equals("dhcp"))
					mWifiConfig.ipAssignment = IpAssignment.DHCP;
				else
					mWifiConfig.ipAssignment = IpAssignment.STATIC;
				isTrue = true;
			} else
				log.d("...please input right mode....");
		} else
			log.d("...mWifiConfig..error...");
		return isTrue;
	}

	/**
	 * 获取当前使用的wifi的接入模式
	 * 
	 * @return static dhcp
	 */
	public String getMode() {
		String mode = "dhcp";

		if (mWifiConfig != null && mWifiConfig.ipAssignment.equals(IpAssignment.STATIC))
			mode = "static";
		else if (mWifiConfig != null && mWifiConfig.ipAssignment.equals(IpAssignment.DHCP))
			mode = "dhcp";
		return mode;
	}

	/**
	 * 设置连接wifi 的密码
	 * 
	 * @return true:设置正确，false：密码设置错误
	 */
	public boolean setPassword(String pswd) {
		boolean isTrue = false;
		if (pswd != null && !pswd.equals("")) {
			mWifiPassword = pswd;
			isTrue = true;
		}
		return isTrue;
	}

	/**
	 * 保存网络信息
	 * 
	 * @param bean
	 *            网络配置对象
	 */
	public void setNetWork(WifiNetworkBean bean) {
		if (mNetwork != null) {
			WifiNetworkSetting mNetwork = new WifiNetworkSetting();
			int iMaskPre = getNetmaskLength(bean.getMask());
			log.d("......iMaskPre=" + iMaskPre);
			mNetwork.setIP(bean.getIp(), iMaskPre);
			mNetwork.setGateway(bean.getGateway());
			mNetwork.setDns(bean.getDns());
			mNetwork.setDns(bean.getDns2());
			mWifiConfig.linkProperties = mNetwork.getmLinkProperties();
		}
	}

	/**
	 * 连接一个未配置过的新网络，要求前提 setSSID()、setPassword()、setMode()、setNetWork()
	 * 
	 * @param ssid
	 */
	public boolean connectWifi(String ssid) {
		boolean flag;
		flag = false;
		try {
			mCurrentNetworkId = -1;
			mCurrentSSID = ssid;
			List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
			for (WifiConfiguration existingConfig : existingConfigs) {
				if (("\"" + ssid + "\"").equals(existingConfig.SSID)) {
					log.d("ssid=" + ssid);
					flag = true;
					mWifiConfig = existingConfig;
					log.d("===mWifiConfig===" + mWifiConfig.wepKeys[0]);
					break;
				}
			}
			if (mWifiConfig == null) {
				mWifiConfig = new WifiConfiguration();
			}
			if (!flag)
				setConfig();
			if (mWifiConfig.SSID == null || mWifiConfig.equals("")) {
				log.e("Not found ssid,please select again.");
				return false;
			}
			ConnectivityManager connManager = (ConnectivityManager) this.ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			WifiInfo wifiInfo = null;

			if (mWifi.isConnected()) {
				wifiInfo = mWifiManager.getConnectionInfo();
				mPreNetworkId = wifiInfo.getNetworkId();
				// mWifiManager.disableNetwork(wifiInfo.getNetworkId());
				// 因为连接上SSID时，再去连接其他SSID，不会重新获取IP，所以如果已连接时，将当前连接SSID，先删除当前SSID
				// mWifiManager.removeNetwork(wifiInfo.getNetworkId());
			}
			if (!flag) {
				mCurrentNetworkId = mWifiManager.addNetwork(mWifiConfig);
			} else {
				mCurrentNetworkId = mWifiManager.updateNetwork(mWifiConfig);
			}
			log.d(".....connectWifi()..currentNetworkId==" + mCurrentNetworkId);
			mWifiConfig.networkId = mCurrentNetworkId;
			mWifiManager.enableNetwork(mCurrentNetworkId, true);
			mWifiManager.saveConfiguration();
			mWifiManager.disconnect();
			if (mCurrentNetworkId != -1) {
				mWifiManager.connect(mCurrentNetworkId, null);
			}
			// if (mWifiHandler.hasMessages(GOTO_CONNECT_PRE_SSID)) {
			// mWifiHandler.removeMessages(GOTO_CONNECT_PRE_SSID);
			// }
			// mWifiHandler.sendEmptyMessageDelayed(GOTO_CONNECT_PRE_SSID, 15 *
			// 1000);
			mWifiConfig = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public final int getmCurrentNetworkId() {
		return mCurrentNetworkId;
	}

	/**
	 * 当前无线是否已经连接
	 * 
	 * @return boolean
	 */
	public boolean wifiIsConnect() {
		ConnectivityManager connManager = (ConnectivityManager) this.ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mWifi.isConnected())
			return true;
		else
			return false;
	}

	/**
	 * 获取当前无线网络信息
	 * 
	 * @return WifiNetworkBean
	 */
	public WifiNetworkBean getWifiNetWork() {
		WifiNetworkBean bean = new WifiNetworkBean();
		int iip = mWifiManager.getDhcpInfo().ipAddress;
		int igateway = mWifiManager.getDhcpInfo().gateway;
		int imask = mWifiManager.getDhcpInfo().netmask;
		int idns1 = mWifiManager.getDhcpInfo().dns1;
		int idns2 = mWifiManager.getDhcpInfo().dns2;
		bean.setIp(IPUtils.intToIp(iip));
		bean.setGateway(IPUtils.intToIp(igateway));
		bean.setMask(IPUtils.intToIp(imask));
		bean.setDns(IPUtils.intToIp(idns1));
		bean.setDns2(IPUtils.intToIp(idns2));

		WifiInfo mInfo = mWifiManager.getConnectionInfo();
		String bssid = mInfo.getBSSID();
		String ssid = mInfo.getSSID();
		int linkSpeed = mInfo.getLinkSpeed();
		String mACAddr = mInfo.getMacAddress();
		int mRssi = mInfo.getRssi();
		bean.setSsid(ssid);
		bean.setBssid(bssid);
		bean.setmRssi(mRssi);
		bean.setMACAddr(mACAddr);
		bean.setLinkspeed(linkSpeed);
		return bean;
	}

	/**
	 * 查询已经保存的所有ssid list
	 * 
	 * @return list <ssid>
	 */
	public List<String> haveConfiguration() {
		List<String> ssidList = new ArrayList();
		List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
		for (WifiConfiguration existingConfig : existingConfigs) {
			String ssid = existingConfig.SSID;
			ssidList.add(ssid);
		}
		return ssidList;
	}

	/**
	 * 根据网络是否已经配置保存过
	 * 
	 * @param SSID
	 * @return boolean
	 */
	public boolean haveConfiguration(String SSID) {
		List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
		for (WifiConfiguration existingConfig : existingConfigs) {
			if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
				return true;
			}
		}
		return false;
	}

	private void setConfig() {
		AccessPoint accessPoint = mCurrentAccessPoint;
		if (accessPoint == null)
			return;
		mWifiConfig.SSID = convertToQuotedString(accessPoint.getSsid());
		mWifiConfig.hiddenSSID = true;
		int iSecurity = accessPoint.getSecurity();
		log.d(".......getConfig()........iSecurity=" + iSecurity);
		switch (iSecurity) {
		case AccessPoint.SECURITY_NONE:
			mWifiConfig.allowedKeyManagement.set(KeyMgmt.NONE);
			break;
		case AccessPoint.SECURITY_WEP:
			mWifiConfig.allowedKeyManagement.set(KeyMgmt.NONE);
			mWifiConfig.allowedAuthAlgorithms.set(AuthAlgorithm.OPEN);
			mWifiConfig.allowedAuthAlgorithms.set(AuthAlgorithm.SHARED);
			if (mWifiPassword != null && !mWifiPassword.equals("")) {
				int length = mWifiPassword.length();
				// WEP-40, WEP-104, and 256-bit WEP (WEP-232?)
				if ((length == 10 || length == 26 || length == 58) && mWifiPassword.matches("[0-9A-Fa-f]*")) {
					mWifiConfig.wepKeys[0] = mWifiPassword;
				} else {
					mWifiConfig.wepKeys[0] = '"' + mWifiPassword + '"';
				}
			}
			break;

		case AccessPoint.SECURITY_PSK:
			mWifiConfig.allowedKeyManagement.set(KeyMgmt.WPA_PSK);
			if (mWifiPassword != null && !mWifiPassword.equals("")) {
				log.d("....PSWD===" + mWifiPassword);
				if (mWifiPassword.matches("[0-9A-Fa-f]{64}")) {
					log.d("..aaaaa..PSWD===" + mWifiPassword);
					mWifiConfig.preSharedKey = mWifiPassword;
				} else {
					log.d("..bbbb..PSWD===" + mWifiPassword);
					mWifiConfig.preSharedKey = '"' + mWifiPassword + '"';
				}
			}
			break;
		case AccessPoint.SECURITY_EAP:
			log.d("....SECURITY_EAP===");
			mWifiConfig.allowedKeyManagement.set(KeyMgmt.WPA_EAP);
			mWifiConfig.allowedKeyManagement.set(KeyMgmt.IEEE8021X);
			break;
		}
		mWifiConfig.proxySettings = ProxySettings.NONE;// 代理设置
		mWifiConfig.priority = 2;
		mWifiConfig.allowedProtocols.set(Protocol.WPA);
		mWifiConfig.allowedProtocols.set(Protocol.RSN);
	}

	/**
	 * Wi-Fi 启动搜索、停止搜索、恢复搜索
	 * 
	 */
	private class Scanner extends Handler {
		private int mRetry = 0;

		void resume() {
			if (!hasMessages(0)) {
				sendEmptyMessage(0);
			}
		}

		void forceScan() {
			removeMessages(0);
			sendEmptyMessage(0);
		}

		void pause() {
			mRetry = 0;
			removeMessages(0);
		}

		@Override
		public void handleMessage(Message message) {
			if (mWifiManager.startScanActive()) {
				mRetry = 0;
			} else if (++mRetry >= 3) {// 扫描不到网络
				mRetry = 0;
				return;
			}
			sendEmptyMessageDelayed(0, WIFI_RESCAN_INTERVAL_MS);
		}
	}

	private int getNetmaskLength(String maskStr) {
		int mask = toInt(maskStr);
		int prefixLength = 0;
		for (int i = 1; i < Integer.SIZE; i++) {
			if (((mask >> i) & 1) == 1) {
				prefixLength++;
			}
		}
		return prefixLength;
	}

	private int toInt(final String ip) {
		int[] intIP = changeToInt(ip);
		if (intIP != null) {
			int num = ((intIP[0] << 24) | (intIP[1] << 16) | (intIP[2] << 8) | intIP[3]);
			return num;
		}
		return 0;
	}

	private int[] changeToInt(final String ip) {
		if (ip == null)
			return null;
		String[] str = ip.split("\\.");
		if (str.length != 4)
			return null;
		try {
			int[] intIP = new int[4];
			for (int i = 0; i < str.length; i++) {
				intIP[i] = Integer.parseInt(str[i]);
			}
			return intIP;
		} catch (Exception e) {
		}
		return null;
	}

	public String convertToQuotedString(String string) {
		return "\"" + string + "\"";
	}

	public void sendMsg(Message msg) {
		if (mWifiHandler.hasMessages(msg.what)) {
			mWifiHandler.removeMessages(msg.what);
		}
		mWifiHandler.sendMessage(msg);
	}

	public void sendMsg(Message msg, int delayMillis) {
		if (mWifiHandler.hasMessages(msg.what)) {
			mWifiHandler.removeMessages(msg.what);
		}
		mWifiHandler.sendMessageDelayed(msg, delayMillis);
	}

	public void sendMsgToStatusbar(Message msg) {
		if (mWifiHandler.hasMessages(SEND_MSG_TO_STATUSBAR)) {
			mWifiHandler.removeMessages(SEND_MSG_TO_STATUSBAR);
		}
		mWifiHandler.sendMessage(msg);
	}

	/**
	 * 向状态栏发送消息
	 * 
	 * @param msg
	 */
	private void sendMsgToStatusbar(String msg) {
		// final NotificationManager nm = (NotificationManager)
		// ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		// Notification notification = new Notification();
		// notification.icon = R.drawable.icon;
		// notification.tickerText = msg;
		// Intent intent = new Intent(ctx,
		// net.sunniwell.app.swsettings.huawei.SWSettingsActivity.class);
		// PendingIntent mPendingIntent = PendingIntent.getActivity(ctx, 0,
		// intent, 0);
		// String str = ctx.getString(R.string.app_name);
		// notification.setLatestEventInfo(ctx, str, str, mPendingIntent);
		// nm.notify(0, notification);
		// Message mesg = new Message();
		// mesg.what = REMOVE_NOTIFATION;
		// mesg.obj = nm;
		// mWifiHandler.sendMessageDelayed(mesg, 3 * 1000);
	}

	public WifiInfo getConnectionInfo() {
		return mWifiManager.getConnectionInfo();
	}

	public void reassociate() {
		mWifiManager.reassociate();
	}

	public void disconnect() {
		mWifiManager.disconnect();
	}

	public WifiInfo getWifiInfo() {
		return mWifiManager.getConnectionInfo();
	}

	public void setWifiUnEnabled() {
		Settings.Secure.putInt(ctx.getContentResolver(), "default_wifi_mod", 0);
		mWifiManager.setWifiEnabled(false);
		try {
			Settings.Global.putInt(ctx.getContentResolver(), Global.WIFI_SAVED_STATE, 0);
			WifiConfiguration wcg = mWifiManager.getWifiApConfiguration();
			if (wcg != null && mWifiManager.isWifiApEnabled()) {
				mWifiManager.setWifiApEnabled(wcg, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setWifiEnabled() {
		if (mWifiManager.isWifiApEnabled()) {
			mWifiManager.setWifiApEnabled(null, false);
		}
		mWifiManager.setWifiEnabled(true);
	}

	public void downEthConnect() {
		EthernetManager manager = (EthernetManager) ctx.getSystemService(Context.ETHERNET_SERVICE);
		PppoeManager mPppoeManager = (PppoeManager) ctx.getSystemService(Context.PPPOE_SERVICE);
		if (mPppoeManager.isPppoeEnabled() && mPppoeManager.getPppoeType() == PppoeManager.DEVICE_TYPE_ETHERNET) {
			mPppoeManager.setPppoeEnabled(false);
		}
		manager.setEthernetEnabled(false);
	}

	public void downWlanConnect() {
		PppoeManager mPppoeManager = (PppoeManager) ctx.getSystemService(Context.PPPOE_SERVICE);
		if (mPppoeManager.isPppoeEnabled() && mPppoeManager.getPppoeType() == PppoeManager.DEVICE_TYPE_WIFI) {
			mPppoeManager.setPppoeEnabled(false);
		}
		setWifiUnEnabled();
	}
}
