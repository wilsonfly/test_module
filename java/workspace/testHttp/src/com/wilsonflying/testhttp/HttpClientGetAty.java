package com.wilsonflying.testhttp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HttpClientGetAty extends Activity {

	private HttpClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitvity_httpgetaty);
		
		client = new DefaultHttpClient();
	}
	
	public void onClickBtn(View view){
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				String url = params[0];
				HttpGet get = new HttpGet(url);
				try {
					HttpResponse response = client.execute(get);
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						
						String data = EntityUtils.toString(response.getEntity());
						System.out.println(data);
						
						return data;
					}else{
						System.out.println("no response");
						
						return null;
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				Toast.makeText(HttpClientGetAty.this, "httpClientGet获取数据:\n"+result, Toast.LENGTH_SHORT).show();
			}
			
			
		}.execute("http://fanyi.youdao.com/openapi.do?keyfrom=wilsonflying&key=924613625&type=data&doctype=xml&version=1.1&q=good");
	}
	
}
