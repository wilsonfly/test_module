package com.wilsonflying.testhttp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HttpClientPostAty extends Activity {

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
				HttpPost post = new HttpPost(url);
				StringEntity entity;
				try {
					entity = new StringEntity(params[1]);
					post.setEntity(entity);
					
					HttpResponse response = client.execute(post);
					String data = EntityUtils.toString(response.getEntity());
					System.out.println(data);
					
					return data;
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
				Toast.makeText(HttpClientPostAty.this, "httpClientPost获取数据:\n"+result, Toast.LENGTH_SHORT).show();
			}
			
			
		}.execute("http://fanyi.youdao.com/openapi.do","keyfrom=wilsonflying&key=924613625&type=data&doctype=xml&version=1.1&q=good");
	}
	
}
