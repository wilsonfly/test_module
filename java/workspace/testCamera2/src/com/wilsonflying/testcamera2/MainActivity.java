package com.wilsonflying.testcamera2;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {

	private ImageView iv;
	private File imgFile;
	private static final int TAKE_PICTURE = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv = (ImageView) findViewById(R.id.imageView);
    }

    public void onClickBtn(View view){
    	File filePath = new File(Environment.getExternalStorageDirectory()+"/Pictures");
    	if(!filePath.exists()){
    		filePath.mkdirs();
    	}
    	
    	imgFile = new File(filePath, System.currentTimeMillis()+".jpg");
    	if(!imgFile.exists()){
    		try {
    			Log.d("shs", "imgFile :::::"+imgFile.toString());
				imgFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
    	startActivityForResult(intent, TAKE_PICTURE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	Log.d("shs", " in onactivityresult");
    	switch (requestCode) {
		case TAKE_PICTURE:
			iv.setImageURI(Uri.fromFile(imgFile));
			break;

		default:
			break;
		}
    	
    	super.onActivityResult(requestCode, resultCode, data);
    }
}
