package com.example.gallery;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PictureAdapter extends BaseAdapter {
	ArrayList<Picture> items = new ArrayList<Picture>();
	Context mContext;
	
	public PictureAdapter(Context c) {
		this.mContext = c;
	}
		
	public void add(Picture item) {
		items.add(item);
		notifyDataSetChanged();
	}
		
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PictureView view;
		if(convertView != null && convertView instanceof PictureView)
		{ view = (PictureView)convertView; }
		else
		{ view = new PictureView(mContext); }
		view.setPicture(items.get(position));
		return view;
	}

}
