package com.example.menu;

import com.example.menu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ScheduleView extends FrameLayout {
	Schedule mdata;
	TextView hour, min, name;
	
	public ScheduleView(Context context) {
		super(context);
		init();
	}

	public ScheduleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.item_calendar_schedule, this);
		hour = (TextView) findViewById(R.id.hourtext);
		min = (TextView) findViewById(R.id.mintext);
		name = (TextView) findViewById(R.id.nametext);
	}
	
	public void setSchedule(Schedule data) {
		mdata = data;
		hour.setText(data.hour + " :");
		min.setText("" + data.min);
		name.setText(data.name);
		
	}
	
	public Schedule getSchedule() {
		return mdata;
	}

}
