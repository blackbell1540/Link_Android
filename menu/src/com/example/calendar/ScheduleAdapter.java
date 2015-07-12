package com.example.calendar;

import java.util.ArrayList;

import com.example.gallery.Picture;
import com.example.gallery.PictureAdapter.onButtonClickListener;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ScheduleAdapter extends BaseAdapter {
	ArrayList<Schedule> items = new ArrayList<Schedule>();
	Context mContext;
	
	public interface onButtonClickListener {
		void btnClick(Schedule s);
	}
	private onButtonClickListener adptCallback = null;
	public void setOnButtonClickListener(onButtonClickListener callback) {
		adptCallback = callback;
	}
	
	public ScheduleAdapter(Context c) {
		this.mContext = c;
	}
		
	public void add(Schedule item) {
		items.add(item);
		notifyDataSetChanged();
	}
	public void delete(Schedule item) {
		items.remove(item);
		notifyDataSetChanged();
	}
	public void clear() {
		items.clear();
		notifyDataSetChanged();
	}
		
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Schedule getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ScheduleView view;
		if(convertView != null && convertView instanceof ScheduleView)
		{ view = (ScheduleView)convertView; }
		else
		{ view = new ScheduleView(mContext); }
		view.setSchedule(items.get(position));
		view.remove.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(adptCallback != null)
					adptCallback.btnClick(view.mdata);
				
			}
		});
		return view;
	}

}
