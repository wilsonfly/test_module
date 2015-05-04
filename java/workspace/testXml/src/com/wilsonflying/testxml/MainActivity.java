package com.wilsonflying.testxml;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		XmlResourceParser xrp = getResources().getXml(R.xml.onexmlfile);
		StringBuilder sb = new StringBuilder("");
		
		try {
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType() == XmlResourceParser.START_TAG){
					String tag = xrp.getName();
					if(tag.equals("customer")){
						sb.append("name: "+xrp.getAttributeValue(0)+" ");
						sb.append("phone: "+xrp.getAttributeValue(1)+" ");
						sb.append("email: "+xrp.getAttributeValue(2)+" ");
						sb.append("\n");
					}
				}
				xrp.next();
			}
			
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			TextView tv = (TextView) findViewById(R.id.text);
			tv.setText(sb.toString());
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}