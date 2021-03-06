package com.wilsonflying.testjason;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
			InputStreamReader isr = new InputStreamReader(getAssets().open("test.jason"), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			StringBuilder builder = new StringBuilder();
			while((line = br.readLine()) != null){
				builder.append(line);
			}
			br.close();
			isr.close();
			
			JSONObject root = new JSONObject(builder.toString());
			builder.setLength(0);
			System.out.println("cat:"+root.getString("cat"));
			
			JSONArray array = root.getJSONArray("languages");
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				System.out.println("--------------");
				System.out.println("id:"+obj.getInt("id"));
				System.out.println("ide:"+obj.getString("ide"));
				System.out.println("name:"+obj.getString("name"));
				
				builder.append("id:" + obj.getInt("id")+" ide:"+obj.getString("ide") +" name:"+obj.getString("name") +"\n");
			}
			Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //create jason
        try {
        	JSONObject root = new JSONObject();
        	root.put("cat", "it");

//        	{"id":1, "ide":"eclipse", "name":"Java"},
        	JSONObject lan1 = new JSONObject();
        	lan1.put("id", 1);
        	lan1.put("ide", "eclipse");
        	lan1.put("name", "java");
        	
//        	{"id":2, "ide":"xcode", "name":"swift"},
        	JSONObject lan2 = new JSONObject();
        	lan2.put("id", 1);
        	lan2.put("ide", "xcode");
        	lan2.put("name", "swift");
        	
//        	{"id":3, "ide":"visual", "name":"c#"}
        	JSONObject lan3 = new JSONObject();
        	lan3.put("id", 1);
        	lan3.put("ide", "visual");
        	lan3.put("name", "c#");
        	
        	JSONArray array = new JSONArray();
        	array.put(lan1);
        	array.put(lan2);
        	array.put(lan3);
        	
        	root.put("languages", array);
        	
        	System.out.println(root.toString());
        	Toast.makeText(MainActivity.this, root.toString(), Toast.LENGTH_SHORT).show();
        	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }


}
