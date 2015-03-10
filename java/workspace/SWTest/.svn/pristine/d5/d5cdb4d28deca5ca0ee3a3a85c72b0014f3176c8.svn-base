package net.sunniwell.app.test.base.util;

import android.text.Editable;
import android.widget.EditText;

public class StringUtils {
	/**
	 * 删除光标处文字
	 * 
	 * @param mEditText
	 *            要处理的EditText
	 */
	public static void delText(EditText mEditText) {
		int index = mEditText.getSelectionStart();
		Editable edit = mEditText.getEditableText();
		if (index == 0) {
			return;
		}
		edit.delete(index - 1, index);
	}

	public static void addText(EditText mEditText, String text) {
		int index = mEditText.getSelectionStart();
		Editable edit = mEditText.getEditableText();
		edit.insert(index, text);

	}

	public static void addIndex(EditText mEditText) {
		int index = mEditText.getSelectionStart();
		if (index == mEditText.getText().toString().length())
			return;
		mEditText.setSelection(index + 1);

	}

	public static void decIndex(EditText mEditText) {
		int index = mEditText.getSelectionStart();
		if (index == 0)
			return;
		mEditText.setSelection(index - 1);
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String formatIpField(String ipField) {
		if (isEmpty(ipField)) {
			return "";
		}
		StringBuilder mBuilder = new StringBuilder();
		String[] mIpArray = ipField.split("\\.");
		for (int i = 0; i < mIpArray.length; i++) {
			String str = mIpArray[i];
			if (isNotEmpty(str)) {
				int len = str.length();
				if (len == 1) {
					mBuilder.append("  ");
					str += "  ";
				}
				if (len == 2) {
					mBuilder.append(" ");
					str += " ";
				}
				mBuilder.append(str);
				if (i != mIpArray.length - 1) {
					mBuilder.append("   ·   ");
				}
			}
		}
		return new String(mBuilder);
	}

	public static String intToIp(int paramInt) {
		return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "."
				+ (0xFF & paramInt >> 16) + "." + (0xFF & paramInt >> 24);
	}

	public static String removeDoubleQuotes(String string) {
		int length = string.length();
		if ((length > 1) && (string.charAt(0) == '"')
				&& (string.charAt(length - 1) == '"')) {
			return string.substring(1, length - 1);
		}
		return string;
	}

}
