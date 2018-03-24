package com.wilsonflying.testbaseadapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyListAdapter<T> extends BaseAdapter {

	private List<T> list = new ArrayList<T>();
	
	public MyListAdapter(Context context, int resId){
		this.context = context;
		this.listCellId = resId;
	}
	
	private Context context;
	private int listCellId;
	
	public Context getContext() {
		return context;
	}

	public int getListCellId() {
		return listCellId;
	}
	
	public void add(T item){
		list.add(item);
		notifyDataSetChanged();
	}
	
	public void remove(int position){
		list.remove(position);
		notifyDataSetChanged();
	}
	
	public void removeLast(int position){
		remove(list.size()-1);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(listCellId, null);
		}
		
		initListCell(position, convertView, parent);
		
		return convertView;
	}

	protected abstract void initListCell(int position, View convertView, ViewGroup parent);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
