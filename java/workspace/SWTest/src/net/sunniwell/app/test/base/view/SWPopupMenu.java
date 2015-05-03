package net.sunniwell.app.test.base.view;

import net.sunniwell.app.test.base.R;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * 自定义菜单
 * 
 * @author zhangdaili
 * 
 * @Date 2013-1-21
 */
public class SWPopupMenu extends PopupWindow implements OnItemClickListener,
		OnKeyListener {
	private Context mContext;
	private String[] mMenuItems;
	private ListView mMenuView;
	private OnMenuItemClickListener mMenuItemClickListener;

	public void setOnMenuItemClickListener(
			OnMenuItemClickListener mMenuItemClickListener) {
		this.mMenuItemClickListener = mMenuItemClickListener;
	}

	public SWPopupMenu(Context context, String[] items) {
		mContext = context;
		this.mMenuItems = items;
		initView();
	}

	private void initView() {
		LayoutInflater layoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = layoutInflater.inflate(R.layout.popupmenu, null);
		mMenuView = (ListView) v.findViewById(R.id.menu_body);
		ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, mMenuItems);
		mMenuView.setAdapter(menuAdapter);
		mMenuView.setOnItemClickListener(this);
		mMenuView.setOnKeyListener(this);
		this.setWidth(LayoutParams.WRAP_CONTENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setContentView(v);
	}

	public void setSelection(int index) {
		try {
			mMenuView.setSelection(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		if (mMenuItemClickListener != null) {
			mMenuItemClickListener.onMenuItemClick(parent, v, position, id);
			this.dismiss();
		}
	}

	public interface OnMenuItemClickListener {
		public void onMenuItemClick(AdapterView<?> parent, View v,
				int position, long id);
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				this.dismiss();
				break;
			default:
				break;
			}
		}
		return false;
	}

}
