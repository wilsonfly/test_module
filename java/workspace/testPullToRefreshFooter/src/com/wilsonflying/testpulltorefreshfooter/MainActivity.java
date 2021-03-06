package com.wilsonflying.testpulltorefreshfooter;

import java.util.ArrayList;

import com.wilsonflying.testpulltorefreshfooter.R;
import com.wilsonflying.testpulltorefreshfooter.LoadListView.ILoadListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity implements ILoadListener {

//	private ListView lv;
	private LoadListView lv=null;
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
    		lv = (LoadListView) findViewById(R.id.lv);
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
	public void onLoad() {
		// TODO Auto-generated method stub
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//获取最新数据
				for (int i = 0; i < 2; i++) {
					list.add("new item"+i);
				}
				
				//通知界面更新
				showList();
				
				//通知listview刷新数据完毕，隐藏加载提示界面
				lv.loadComplete();
			}
		}, 2000);
	}
    

}
