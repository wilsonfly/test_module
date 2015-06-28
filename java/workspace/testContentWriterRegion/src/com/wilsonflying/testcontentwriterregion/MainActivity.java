package com.wilsonflying.testcontentwriterregion;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDeleteDB(View view){
		File file = new File("/data/data/"+getPackageName()+"/databases/myprovider.db");
		if(file!=null && file.exists()){
			if(file.delete()){
				Toast.makeText(this, file.toString()+"删除成功", Toast.LENGTH_SHORT).show();
			}
		}
		
    }

}
