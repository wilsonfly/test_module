package com.wilsonflying.testpulltorefreshheader;

import android.content.Context;
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

public class RefreshListView extends ListView implements OnScrollListener {

	private final String TAG = "RefreshListView";
	private View header;
	private int headerHeight;
	private int firstVisibleItem;
	private boolean isMarked;// 标记，当前在listview最顶端按下的
	private int startY;// 按下时的Y值

	private int pressedState;// 当前按下状态
	private final int NONE = 0;
	private final int PULL = 1;
	private final int RELEASE = 2;
	private final int REFRESHING = 3;

	private int scrollState;

	public RefreshListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context);

	}

	public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView(context);

	}

	public void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		header = inflater.inflate(R.layout.layout_header, null);

		MeasureView(header);
		headerHeight = header.getMeasuredHeight();
		Log.d(TAG, "headerHeight:" + headerHeight);
		setTopPadding(-headerHeight);

		this.addHeaderView(header);
	}

	// 通知父布局，header占用的宽/高
	public void MeasureView(View view) {
		ViewGroup.LayoutParams param = view.getLayoutParams();
		if (param == null) {
			param = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);

		}

		int width = ViewGroup.getChildMeasureSpec(0, 0, param.width);
		int height;
		int tmpHeight = param.height;
		if (tmpHeight > 0) {
			height = MeasureSpec
					.makeMeasureSpec(tmpHeight, MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}

	// 设置header的top位置为其高度的负值，达到隐藏的效果
	public void setTopPadding(int topPadding) {
		header.setPadding(header.getPaddingLeft(), topPadding,
				header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		this.scrollState = scrollState;

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (firstVisibleItem == 0) {
				isMarked = true;
				startY = (int) ev.getY();
			}
			refreshViewByState();
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			break;
		case MotionEvent.ACTION_UP:
			if (pressedState == RELEASE) {
				pressedState = REFRESHING;
			} else if (pressedState == PULL) {
				pressedState = NONE;
				isMarked = false;
			}
			refreshViewByState();
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	// 判断移动过程中的操作
	public void onMove(MotionEvent ev) {
		if (!isMarked) {
			return;
		}

		int tmpY = (int) ev.getY();
		int space = tmpY = startY;
		int topPadding = space - headerHeight;

		switch (pressedState) {
		case NONE:
			if (space > 0) {
				pressedState = PULL;
				refreshViewByState();
			}
			break;
		case PULL:
			setTopPadding(topPadding);
			if (space > headerHeight + 30
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				pressedState = RELEASE;
				refreshViewByState();
			}
			break;
		case RELEASE:
			setTopPadding(topPadding);

			if (space < headerHeight + 30) {
				pressedState = PULL;
			}

			if (space <= 0) {
				pressedState = NONE;
				isMarked = false;
			}
			refreshViewByState();
			break;
		case REFRESHING:

			break;

		default:
			break;
		}
	}

	// 触屏根据状态改名界面显示
	public void refreshViewByState() {

		TextView tip = (TextView) findViewById(R.id.tip);
		ImageView img = (ImageView) findViewById(R.id.arrow);
		ProgressBar progress = (ProgressBar) findViewById(R.id.progressbar);
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);
		RotateAnimation anim2 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim2.setDuration(500);

		switch (pressedState) {
		case NONE:
			setTopPadding(-headerHeight);
			img.clearAnimation();
			break;
		case PULL:
			img.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("下拉可以刷新");

			img.startAnimation(anim2);
			break;
		case RELEASE:
			img.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("松开进行刷新");
			img.startAnimation(anim2);

			break;
		case REFRESHING:
			img.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			tip.setText("正在刷新...");
			img.clearAnimation();
			break;

		default:
			break;
		}
	}
}
