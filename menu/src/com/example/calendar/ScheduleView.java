package com.example.calendar;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ScheduleView extends FrameLayout {
	Schedule mdata;
	TextView t;
	Button remove;
	
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
		remove = (Button) findViewById(R.id.schedule_remove);
	}
	
	public void setSchedule(Schedule data) {
		mdata = data;
		t.setText(mdata.date + " " + mdata.hour + ":" + mdata.min + " " + mdata.calendar_name);
		
	}
	
	public Schedule getSchedule() {
		return mdata;
	}

}
