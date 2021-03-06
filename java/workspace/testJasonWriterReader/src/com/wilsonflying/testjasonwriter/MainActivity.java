package com.wilsonflying.testjasonwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String JASONFILE = "/sdcard/testJasonWriter.json";
	private List<Product> products;
	private List<Product> products_readed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_write).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					FileOutputStream fo = new FileOutputStream(JASONFILE);
					products = new ArrayList<Product>();
					products.add(new Product(1, "电脑", (float) 8888.88));
					products.add(new Product(2, "微波炉", (float) 666.66));
					products.add(new Product(3, "洗衣机", (float) 1234.56));
					WriteJasonStream(fo, products);

					Toast.makeText(MainActivity.this, "写入" + JASONFILE + "完毕",
							Toast.LENGTH_SHORT).show();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		findViewById(R.id.btn_read).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FileInputStream fi;
				try {
					fi = new FileInputStream(JASONFILE);
					products_readed = readJsonStream(fi);
					
					String result = "";
					if(products_readed != null){
						
						for(Product p:products_readed){
							result += "id: "+ p.getId() + " name:"+ p.getName() +" price:" + p.getPrice() + "\n";
						}
					}
					
					Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	protected List<Product> readJsonStream(FileInputStream fi) {
		// TODO Auto-generated method stub
		JsonReader reader = new JsonReader(new InputStreamReader(fi));

		try {

			return readProductArray(reader);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	private List<Product> readProductArray(JsonReader reader)
			throws IOException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		reader.beginArray();
		while (reader.hasNext()) {
			products.add(readProduct(reader));
		}
		reader.endArray();

		return products;
	}

	private Product readProduct(JsonReader reader) throws IOException {
		// TODO Auto-generated method stub
		int id = 0;
		String name = null;
		float price = (float) 0.0;

		reader.beginObject();
		while(reader.hasNext()){
			String item = reader.nextName();
			if(item.equals("id")){
				id = reader.nextInt();
			}else if(item.equals("name")){
				name = reader.nextString();
			}else if(item.equals("price")){
				price = (float) reader.nextDouble();
			}
		}
		reader.endObject();

		return new Product(id, name, price);
	}

	protected void WriteJasonStream(FileOutputStream fo, List<Product> products2) {
		// TODO Auto-generated method stub
		try {
			JsonWriter writer = new JsonWriter(new OutputStreamWriter(fo));
			writer.setIndent("	");
			WriteProductArray(writer, products2);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void WriteProductArray(JsonWriter writer, List<Product> products2)
			throws IOException {
		// TODO Auto-generated method stub
		writer.beginArray();
		for (Product p : products2) {
			WriteProduct(writer, p);
		}
		writer.endArray();

	}

	private void WriteProduct(JsonWriter writer, Product p) throws IOException {
		// TODO Auto-generated method stub
		writer.beginObject();

		writer.name("id").value(p.getId());
		writer.name("name").value(p.getName());
		writer.name("price").value(p.getPrice());

		writer.endObject();
	}

}
