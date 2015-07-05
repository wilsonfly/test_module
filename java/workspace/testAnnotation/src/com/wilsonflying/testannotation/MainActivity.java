package com.wilsonflying.testannotation;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	public static final String NAME_KEY = "name_key";
	public static final String AGE_KEY = "age_key";
	
	@ViewById(R.id.btnShowToast)
	Button button1;
	
	@ViewById
	Button btnOpenSecond;
	
	@ViewById(R.id.tvFirst)
	TextView tvFirst;
	
	@ViewById
	TextView tvSecond;
	
	@ViewsById({R.id.tvFirst, R.id.tvSecond})
	List<TextView> list;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Click(R.id.btnShowToast)
    public void showToast(){
    	Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.btnOpenSecond)
    public void startSecondAty(){
    	Intent intent = new Intent(MainActivity.this, SecondAty_.class);
    	startActivity(intent);
    }

    @Click(R.id.btnOpenSecondExtra)
    public void startSecondAtyExtra(){
    	Intent intent = new Intent(MainActivity.this, ThirdAty_.class);
    	intent.putExtra(NAME_KEY, "付小兜");
//    	intent.putExtra(NAME_KEY, "fuxiaodou");
    	intent.putExtra(AGE_KEY, 18);
    	startActivity(intent);
    }
    
    @Click(R.id.btnOpenForth)
    public void startForthAty(){
    	Intent intent = new Intent(MainActivity.this, ForthAty_.class);
    	startActivity(intent);
    }
    
    @Click(R.id.btnOpenService)
    public void startMyService(){
    	Intent intent = new Intent(MainActivity.this, MyService_.class);
    	startService(intent);
    }
    
    @AfterViews
    public void initText(){
    	tvFirst.setText("初始化第一个textview");
    	tvSecond.setText("初始化第二个textview");
    }
    
    @Click(R.id.btnSetText)
    public void updateText(){
    	for (TextView tv : list) {
    		tv.setText("hello ...");
		}
    }
    
    
}
