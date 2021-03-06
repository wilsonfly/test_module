package com.wilsonflying.testpulltorefreshheader;

import java.util.ArrayList;

import com.wilsonflying.testpulltorefreshheader.RefreshListView.IRefreshListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity implements IRefreshListener {

//	private ListView lv;
	private RefreshListView lv=null;
	private ArrayAdapter<String> adapter = null;
	private ArrayList<String> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化Listview数据内容
        initList();
        
        //关联显示
        showList();
    }

    private void showList(){
    	if(adapter == null){
    		lv = (RefreshListView) findViewById(R.id.lv);
    		lv.setInterface(this);
    		
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            lv.setAdapter(adapter);
    	}else{
    		adapter.notifyDataSetChanged();
    	}
    }
    
    private void initList(){
        list = new ArrayList<String>();

    	for (int i = 0; i < 10; i++) {
    		list.add("item "+i);
    	}
    }
    
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//获取最新数据
				for (int i = 0; i < 2; i++) {
					list.add(0, "new item"+i);
				}
				
				//通知界面
				showList();
				
				//通知listview刷新数据完毕
				lv.refreshComplete();
			}
		}, 2000);
		
	}


}
