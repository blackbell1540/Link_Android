package com.example.calendar;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ScheduleAdapter extends BaseAdapter {
	ArrayList<Schedule> items = new ArrayList<Schedule>();
	Context mContext;
	
	public ScheduleAdapter(Context c) {
		this.mContext = c;
	}
		
	public void add(Schedule item) {
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
		ScheduleView view;
		if(convertView != null && convertView instanceof ScheduleView)
		{ view = (ScheduleView)convertView; }
		else
		{ view = new ScheduleView(mContext); }
		view.setSchedule(items.get(position));
		return view;
	}

}
