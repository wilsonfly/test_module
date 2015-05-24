package com.wilsonflying.testxml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	private final String CUSTOMER_FILE = "customer_info.xml";
	private final String LANGUGAGE_FILE = "language_info.xml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		tv = (TextView) findViewById(R.id.text);

		//XmlResourceParser,xml,customer_info.xml
		findViewById(R.id.button_xmlrp_cust).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				XmlResourceParser xrp = getResources().getXml(R.xml.customer_info);
				StringBuilder sb = new StringBuilder("");

				try {
					while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
						if (xrp.getEventType() == XmlResourceParser.START_TAG) {
							String tag = xrp.getName();
							if (tag.equals("customer")) {
								sb.append("name: " + xrp.getAttributeValue(0)
										+ " ");
								sb.append("phone: " + xrp.getAttributeValue(1)
										+ " ");
								sb.append("email: " + xrp.getAttributeValue(2)
										+ " ");
								sb.append("\n");
							}
						}
						xrp.next();
					}

					tv.setText(sb.toString());

				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//XmlResourceParser,xml,language_info.xml
		findViewById(R.id.button_xmlrp_lan).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				XmlResourceParser parser = getResources().getXml(R.xml.language_info);
				
				tv.setText("");
				tv.append("parse language.xml in xml with XmlResourceParse"+"\n");
				
				try {
					while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
						if(parser.getEventType() == XmlResourceParser.START_TAG){
							if("lan".equals(parser.getName())){
								tv.append(parser.getAttributeValue(0)+"\n");
								do{
									System.out.println("parser.getname:"+parser.getName());
									if(parser.getEventType() == XmlResourceParser.START_TAG){
										if("name".equals(parser.getName()) ){
											tv.append("name:"+parser.nextText()+"\n");
										}else if("ide".equals(parser.getName())){
											tv.append("ide:"+parser.nextText()+"\n");
										}
									}
									parser.next();
								}while(!("lan".equals(parser.getName()) && (parser.getEventType()==XmlResourceParser.END_TAG)));
								//在lan标签内部进行循环，在遇到标签名为lan并且处于标签结尾的时候跳出循环。在内部，遇到标签名为name或者ide时，取出nextText()值。
							}
						}
						parser.next();
					}
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//DocumentBuilder,assets,customer_info.xml
		findViewById(R.id.button_doc_cust).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(getAssets().open(CUSTOMER_FILE));
					Element element = document.getDocumentElement();
					NodeList list = element.getElementsByTagName("customer");
					
					tv.setText("");
					tv.append("parse asset xml with DocumentBuilderFactory"+"\n");
					
					for(int i=0; i< list.getLength(); i++){
						Element customer = (Element) list.item(i);
						System.out.println(""+customer.getAttribute("name"));
						tv.append(customer.getAttribute("name") + "\n");
						tv.append(customer.getAttribute("email") + "\n");
						tv.append(customer.getAttribute("tel") + "\n");
					}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//documentBuilder,assets,language_info.xml
		findViewById(R.id.button_doc_lan).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				try {
					builder = factory.newDocumentBuilder();
					Document document = builder.parse(getResources().getAssets().open(LANGUGAGE_FILE));
					NodeList list = document.getElementsByTagName("lan");
					
					tv.setText("");
					tv.append("parse asset language_info.xml with DocumentBuilderFactory"+"\n");
					
					for(int i=0; i<list.getLength(); i++){
						Element lan = (Element) list.item(i);
						tv.append(lan.getAttribute("id")+ "\n");
						tv.append(lan.getElementsByTagName("name").item(0).getTextContent() + "\n");
						tv.append(lan.getElementsByTagName("ide").item(0).getTextContent() + "\n");
					}
					
//					Document document = builder.parse((InputStream) getResources().getXml(R.xml.customer_info));
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}
