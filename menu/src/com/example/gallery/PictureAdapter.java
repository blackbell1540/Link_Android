package com.example.gallery;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PictureAdapter extends BaseAdapter {
	ArrayList<Picture> items = new ArrayList<Picture>();
	Context mContext;
	
	public interface onButtonClickListener {
		void btnClick(Picture p);
	}
	private onButtonClickListener adptCallback = null;
	public void setOnButtonClickListener(onButtonClickListener callback) {
		adptCallback = callback;
	}
	
	public PictureAdapter(Context c) {
		this.mContext = c;
	}

	public void add(Picture item) {
		items.add(item);
		notifyDataSetChanged();
	}
	
	public void delete(Picture item) {
		items.remove(item);
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
		final PictureView view;
		if(convertView != null && convertView instanceof PictureView)
		{ view = (PictureView)convertView; }
		else
		{ view = new PictureView(mContext); }
		view.setPicture(items.get(position));
		view.delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(adptCallback != null)
					adptCallback.btnClick(view.mData);
				
			}
		});
		return view;
	}

}
