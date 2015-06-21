package com.wilsonflying.testxmldefaulthandler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Xml2Product extends DefaultHandler {

	private Product product;
	private List<Product> products;
	private StringBuffer buffer = new StringBuffer();

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();

		products = new ArrayList<Product>();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);

		buffer.append(ch, start, length);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		if (localName.equals("product")) {
			product = new Product();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);

		if (localName.equals("product")) {
			products.add(product);
		} else if (localName.equals("id")) {
			product.setId(Integer.parseInt(buffer.toString().trim()));
			buffer.setLength(0);
		} else if (localName.equals("name")) {
			product.setName(buffer.toString().trim());
			buffer.setLength(0);
		} else if (localName.equals("price")) {
			product.setPrice(Float.parseFloat(buffer.toString().trim()));
			buffer.setLength(0);
		}
	}

}
