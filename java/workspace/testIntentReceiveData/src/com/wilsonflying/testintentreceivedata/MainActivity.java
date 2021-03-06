package com.wilsonflying.testintentreceivedata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Intent intent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        
        
        if(Intent.ACTION_SEND.equals(action) && type!=null ){
        	if("text/plain".equals(type)){
        		String stringExtra = intent.getStringExtra(Intent.EXTRA_TEXT);
        		Toast.makeText(MainActivity.this, "receive:"+stringExtra, Toast.LENGTH_SHORT).show();
//        	}else if("image/jpeg".equals(type)){
        	}else if(type.startsWith("image/")){
            	byte[] byteArrayExtra = intent.getByteArrayExtra(Intent.EXTRA_STREAM);
            	Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length);
            	iv.setImageBitmap(bitmap);
            }
        }
    }

   
}
