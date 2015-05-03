package net.sunniwell.app.test.base.view;

import android.content.Context;
import android.text.Selection;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class SWEditText extends EditText implements OnKeyListener {

	public SWEditText(Context context) {
		super(context);
	}

	public SWEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SWEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			String text = this.getText().toString();
			if (text != null && text.trim().length() > 0) {
				text = text.substring(0, text.length() - 1);
				this.setText(text);
				Spannable sa = this.getText();
				Selection.setSelection(sa, sa.length());
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		Spannable sa = this.getText();
		Selection.setSelection(sa, sa.length());
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {

		}
		return false;
	}

}
