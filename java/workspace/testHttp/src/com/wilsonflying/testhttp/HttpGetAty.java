package com.wilsonflying.testhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HttpGetAty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitvity_httpgetaty);
		
	}
	
	public void onClickBtn(View view){
		new AsyncTask<String, Void , String>() {

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				StringBuffer sb = null;
				try {
					URL url = new URL(params[0]);
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					
					String line;
					sb = new StringBuffer();
					while( (line=br.readLine()) != null){
						sb.append(line);
					}
					
					br.close();
					isr.close();
					is.close();
					
					System.out.println(sb.toString());
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return sb.toString();
			}
			
			protected void onPostExecute(String result) {
				Toast.makeText(HttpGetAty.this, "HttpGet获取数据:\n"+result, Toast.LENGTH_SHORT).show();

			};
			
		}.execute("http://fanyi.youdao.com/openapi.do?keyfrom=wilsonflying&key=924613625&type=data&doctype=xml&version=1.1&q=good");
//		}.execute("http://fanyi.youdao.com/openapi.do?keyfrom=wilsonflying&key=924613625&type=data&doctype=json&version=1.1&q=good");
	}
}
