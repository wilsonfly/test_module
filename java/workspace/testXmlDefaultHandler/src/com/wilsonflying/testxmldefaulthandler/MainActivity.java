package com.wilsonflying.testxmldefaulthandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btn_xml2product).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputStream is = getResources().openRawResource(R.raw.products);
				Xml2Product xml2product = new Xml2Product();
				try {
					Xml.parse(is, Xml.Encoding.UTF_8, xml2product);
					List<Product> products = xml2product.getProducts();
					String msg = "共计 "+products.size()+" product" + "\n";
					for(Product p:products){
						if(p != null){
							msg += "id: "+ p.getId() + " name:" + p.getName() + " price:" + p.getPrice() + "\n";
						}
					}
					
					new AlertDialog.Builder(MainActivity.this).setTitle("XML2PRODUCT 结果").setMessage(msg).setPositiveButton("确定", null).show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }

}
