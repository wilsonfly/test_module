package com.wilsonflying.testpulltorefresh;

import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends Activity {

	private PullToRefreshListView lv;
	private List<String> list;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (PullToRefreshListView) findViewById(R.id.lv);
        
        list = new ArrayList<String>();
        list.add("hello");
        list.add("xiaodou");
        list.add("小兜");

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        
        lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
					
					protected void onPostExecute(Void result) {
						adapter.addAll("更新内容一","更新内容二");
						lv.onRefreshComplete();
					};
				}.execute();
			}
		});
        
    }


}
