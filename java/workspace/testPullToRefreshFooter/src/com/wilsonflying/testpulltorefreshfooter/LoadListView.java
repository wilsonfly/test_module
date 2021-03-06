package com.wilsonflying.testpulltorefreshfooter;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.wilsonflying.testpulltorefreshfooter.R;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.Space;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoadListView extends ListView implements OnScrollListener {

	private final String TAG = "RefreshListView";
	private View footer;
	private int totalItemCount;
	private int lastVisibleItem;
	private boolean isLoading = false;
	private ILoadListener iLoadListener;
	
	public LoadListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public LoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context);

	}

	public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView(context);

	}
	
	private void initView(Context context){
		LayoutInflater inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.layout_footer, null);
		footer.findViewById(R.id.llContent).setVisibility(View.GONE);
		
		this.addFooterView(footer);
		this.setOnScrollListener(this);
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if(totalItemCount == lastVisibleItem
				&& scrollState == SCROLL_STATE_IDLE){
			if( !isLoading ){
				isLoading = true;
				footer.findViewById(R.id.llContent).setVisibility(View.VISIBLE);
				
				//加载更多数据
				iLoadListener.onLoad();
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.lastVisibleItem = firstVisibleItem+visibleItemCount;
		this.totalItemCount =totalItemCount;
	}
	
	public void setInterface(ILoadListener iLoadListener){
		this.iLoadListener = iLoadListener;
	}

	public interface ILoadListener{
		public void onLoad();
	}

	public void loadComplete() {
		// TODO Auto-generated method stub
		isLoading = false;
		footer.findViewById(R.id.llContent).setVisibility(View.GONE);
	}


	
}
