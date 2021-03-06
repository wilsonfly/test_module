package com.wilsonflying.testjni;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private TextView textView;
	private EditText et_putString;
	private final String TAG = "MainActivity";
	private final static int SHOW_TOAST = 100;
	
	public static int getSHOW_TOAST() {
		return SHOW_TOAST;
	}

	private Handler mHandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SHOW_TOAST:
//				Toast.makeText(MainActivity.this, "called by native", Toast.LENGTH_SHORT).show();
//				Toast.makeText(getApplicationContext(), "aa", Toast.LENGTH_SHORT).show();
//				textView.setText("called by native");
				Log.i(TAG, "in handlemessage show_toast");
				break;

			default:
				break;
			}
		};
	};
	
    public Handler getmHandler() {
		return mHandler;
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        textView = (TextView) findViewById(R.id.tv);
        et_putString = (EditText) findViewById(R.id.et_putString);
//        textView.setText(stringFromJNI());
    }

    public native String stringFromJNI();

    static {
    	System.loadLibrary("native_fun");
    }
    
    public void onClickFun(View v){
    	switch (v.getId()) {
		case R.id.btn_readString:
//			textView.setText(stringFromJNI());
			textView.setText(new AccessNative().readStringFromJNI());
			break;
		case R.id.btn_putString:
			
//			AccessNative an = new AccessNative();
//			String strFromNative = an.putStringFromJNI(et_putString.getText().toString());
		
			Log.i(TAG,et_putString.getText().toString());
			String strFromNative = new AccessNative().putStringFromJNI(et_putString.getText().toString());
			textView.setText(strFromNative);
			
			break;
		case R.id.btn_sum:
			double sum = new AccessNative().getSum(2, 3.0);
			Log.i(TAG, "sum:"+sum);
			textView.setText(""+sum);
			break;
			
		case R.id.btn_callBackVoid:
			new AccessNative().callBackVoid();
			break;
			
		case R.id.btn_getMemVar:
			new AccessNative().GetMemVar();
			break;
			
		default:
			break;
		}
    }
}
