package net.sunniwell.app.test.base.util;

import net.sunniwell.common.log.SWLogger;

public final class SWUtil {
	private static final SWLogger log = SWLogger.getLogger(SWUtil.class);

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
