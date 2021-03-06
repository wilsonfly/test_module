package com.wilsonflying.testpulltorefreshheader;

import java.sql.Date;
import java.text.SimpleDateFormat;

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

public class RefreshListView extends ListView implements OnScrollListener {

	private final String TAG = "RefreshListView";
	private View header;
	private int headerHeight;
	private int firstVisibleItem;
	
	private boolean isMarked;// 标记，当前在listview最顶端按下的
	private int startY;// 按下时的Y值

	private int pressedState;// 当前按下状态
	private final int NONE = 0;//正常状态
	private final int PULL = 1;
	private final int RELEASE = 2;
	private final int REFRESHING = 3;

	private int scrollState;
	
	private Context context;

	private Handler handler = new Handler();
	
	private IRefreshListener iRefreshListener;
	
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

		this.context = context;
		
		this.addHeaderView(header);
		this.setOnScrollListener(this);
		
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
		this.firstVisibleItem = firstVisibleItem;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (firstVisibleItem == 0) {
				isMarked = true;
				startY = (int) ev.getY();
				refreshViewByState();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			break;
		case MotionEvent.ACTION_UP:
			if (pressedState == RELEASE) {
				pressedState = REFRESHING;
				refreshViewByState();
				iRefreshListener.onRefresh();
			} else if (pressedState == PULL) {
				pressedState = NONE;
				isMarked = false;
				refreshViewByState();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	//处理移动过程中的状态
	public void onMove(MotionEvent ev) {
		if (!isMarked) {
			return;
		}

		int tmpY = (int) ev.getY();
		int position = tmpY - startY;
		int topPadding = position - headerHeight;

		switch (pressedState) {
		case NONE:
			if (position > 0) {
				pressedState = PULL;
				refreshViewByState();
			}
			break;
		case PULL:
			setTopPadding(topPadding);
			if (position > headerHeight + 30
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				pressedState = RELEASE;
				refreshViewByState();
			}
			break;
		case RELEASE:
			setTopPadding(topPadding);

			if (position < headerHeight + 30) {
				pressedState = PULL;
				refreshViewByState();
			}

			if (position <= 0) {
				pressedState = NONE;
				isMarked = false;
				refreshViewByState();
			}
			break;
		case REFRESHING:

			break;

		default:
			break;
		}
	}

	// 触屏根据状态改变界面显示
	public void refreshViewByState() {

		TextView tvTip = (TextView) findViewById(R.id.tvTip);
		ImageView imgArrow = (ImageView) findViewById(R.id.ivArrow);
		ProgressBar progress = (ProgressBar) findViewById(R.id.progressbar);
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);
		anim.setFillAfter(true);
		
		RotateAnimation anim2 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim2.setDuration(500);
		anim2.setFillAfter(true);

		switch (pressedState) {
		case NONE:
			setTopPadding(-headerHeight);
			
			imgArrow.clearAnimation();
			break;
		case PULL:
			imgArrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tvTip.setText("下拉可以刷新");
			
			imgArrow.clearAnimation();
			imgArrow.startAnimation(anim2);
			break;
		case RELEASE:
			imgArrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tvTip.setText("松开进行刷新");
			
			imgArrow.clearAnimation();
			imgArrow.startAnimation(anim);

			break;
		case REFRESHING:
			setTopPadding(50);
			
			imgArrow.clearAnimation();
			imgArrow.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			tvTip.setText("正在刷新...");
			
			break;
		default:
			break;
		}
	}
	
	//设置刷新完成后的状态
	public void refreshComplete(){
		pressedState = NONE;
		isMarked = false;
		refreshViewByState();
		
		TextView tvUpdateTime = (TextView) findViewById(R.id.tvUpdateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		tvUpdateTime.setText(time);
	}
	
	public void setInterface(IRefreshListener iRefreshListener){
		this.iRefreshListener = iRefreshListener;
	}
	
	//刷新数据
	public interface IRefreshListener{
		public void onRefresh();
	}
	
}
