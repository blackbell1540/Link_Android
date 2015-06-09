package com.example.calendar;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ScheduleView extends FrameLayout {
	Schedule mdata;
	TextView t;
	
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
		t = (TextView) findViewById(R.id.caltext);
	}
	
	public void setSchedule(Schedule data) {
		mdata = data;
		t.setText(mdata.date + " " + mdata.hour + ":" + mdata.min + " " + mdata.name);
		
	}
	
	public Schedule getSchedule() {
		return mdata;
	}

}
