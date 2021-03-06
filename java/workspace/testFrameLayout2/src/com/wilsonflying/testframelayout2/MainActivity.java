package com.wilsonflying.testframelayout2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements Runnable{

	private int[] colors=new int[]{R.color.color_chi, R.color.color_huang, R.color.color_qing, R.color.color_zi, R.color.color_hei};
	private View[] views;
	static int color_offset_index = 0;
	private Handler handler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views = new View[]{
        	findViewById(R.id.textView0),
        	findViewById(R.id.textView1),
        	findViewById(R.id.textView2),
        	findViewById(R.id.textView3),
        	findViewById(R.id.textView4),
        };
        
        handler = new Handler();
        handler.postDelayed(this, 300);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<views.length; i++){
			views[i].setBackgroundColor(getResources().getColor(colors[(i+color_offset_index)%(views.length)]));	
		}

		handler.postDelayed(this, 500);
		color_offset_index++;
//		if(color_offset_index >= views.length){//可有可无，即使溢出也从0重新开始
//			color_offset_index = 0;
//		}
	}


}
