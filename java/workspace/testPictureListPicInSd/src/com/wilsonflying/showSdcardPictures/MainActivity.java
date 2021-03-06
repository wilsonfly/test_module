package com.wilsonflying.showSdcardPictures;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	final List<String> imagePath = new ArrayList<String>();
	private final String EXTERNAL_PATH = "/sdcard";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		File path = Environment.getExternalStorageDirectory();
		Log.i("huasheng", "in getPictures, path:"+path.getPath() );
		Log.i("huasheng", "in getPictures, name:"+path.getName() );
		getPictures(path.getPath());
//		getPictures(EXTERNAL_PATH);
		if(imagePath.size() < 1){
			Log.i("huasheng", "do not find any pcitures");
			Toast.makeText(MainActivity.this, "do not find any pcitures", Toast.LENGTH_SHORT).show();
			return;
		}
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		
		BaseAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ImageView image;
				if(convertView == null){
					image = new ImageView(getApplicationContext());
					image.setAdjustViewBounds(true);
					image.setMaxHeight(220);
					image.setMaxWidth(300);
				}else{
					image = (ImageView) convertView;
				}
				
				Bitmap bitmap = BitmapFactory.decodeFile(imagePath.get(position));
				image.setImageBitmap(bitmap);
				return image;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return imagePath.size();
			}
		};
		
		gridview.setAdapter(adapter);
	}
	
	private void getPictures(String path){
		
		File file = new File(path);
		File[] files = file.listFiles();
		if(files == null){
			Log.i("huasheng", "null dir:"+file);
			return;
		}
		for(File f:files){
			if(f.isDirectory()){
				getPictures(f.getAbsolutePath());
			}else{
				if(isPicture(f.getName())){
					imagePath.add(f.getAbsolutePath());
				}
			}
		}
		
	}
	private String[] picFormat = new String[]{".jpg", ".png", ".gif"};//还是不严谨，起码应该判断后缀
	private boolean isPicture(String path){
		for(int i=0; i<picFormat.length; i++){
//			if(path.contains(picFormat[i])){
//				return true;
//			}
			
			if(path.endsWith(picFormat[i])){
				return true;
			}
			
		}
		return false;
	}
	
}
