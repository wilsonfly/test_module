package com.wilsonflying.testasynctask2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	TextView text;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        
        findViewById(R.id.button_readUrl).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				readUrl("http://www.baidu.com");
				
			}
		});
    }

    public void readUrl(String url){
    	new AsyncTask<String, Float, String>() {

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				try {
					URL url = new URL(params[0]);
					URLConnection connect = url.openConnection();
					InputStream is = connect.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					
					Float totalLenth = (float) connect.getContentLength();
					
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
						publishProgress(sb.toString().length()/totalLenth);
					}
					
					br.close();
					isr.close();
					is.close();
					
					return sb.toString();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "即将开始读取url", Toast.LENGTH_SHORT).show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				text.setText(result);
				Toast.makeText(MainActivity.this, "读取结束", Toast.LENGTH_SHORT).show();
				super.onPostExecute(result);
			}


			@Override
			protected void onProgressUpdate(Float... values) {
				// TODO Auto-generated method stub
				System.out.println("progress:"+values[0]);
				super.onProgressUpdate(values);
			}

			@Override
			protected void onCancelled(String result) {
				// TODO Auto-generated method stub
				super.onCancelled(result);
			}

			@Override
			protected void onCancelled() {
				// TODO Auto-generated method stub
				super.onCancelled();
			}
			
			
			
		}.execute(url);//再此传的参，可以传送多个参数
    }
    
}
