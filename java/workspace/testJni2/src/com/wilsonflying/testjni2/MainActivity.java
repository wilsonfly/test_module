package com.wilsonflying.testjni2;

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
    	System.loadLibrary("testJni2");
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
		case R.id.btn_updateIntArray:
			int[] array = new int[]{1,2,3,4,5};
			int[] result = new int[5];
			result = new AccessNative().updateIntArray(array);
//			Toast.makeText(MainActivity.this, "orgin:"+array.toString()+" updated:"+result.toString(), Toast.LENGTH_SHORT).show();
//			Toast.makeText(MainActivity.this, "orgin:"+String.valueOf(array)+" updated:"+String.valueOf(result), Toast.LENGTH_SHORT).show();
			
			String str_orgin = "";
			String str_result = "";
			for (int i = 0; i < array.length; i++) {
				str_orgin += array[i];
				str_result += result[i];
			}
			Toast.makeText(MainActivity.this, "orgin:"+ str_orgin+" updated:"+str_result, Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.btn_calculteIntArray:
			int[] array2 = new int[]{1,2,3,4,5};
			int[] result2 = new int[5];
			result2 = new AccessNative().calculateIntArray(array2);
			
			String str_orgin2 = "";
			String str_result2 = "";
			for (int i = 0; i < array2.length; i++) {
				str_orgin2 += array2[i];
				str_result2 += result2[i];
			}
			Toast.makeText(MainActivity.this, "orgin:"+ str_orgin2+" updated:"+str_result2, Toast.LENGTH_SHORT).show();
			
			break;
		default:
			break;
		}
    }

}
