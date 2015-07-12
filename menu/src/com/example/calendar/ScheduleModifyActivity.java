package com.example.calendar;

import java.util.Calendar;

import com.example.menu.NetworkManager;
import com.example.menu.R;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R.array;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class ScheduleModifyActivity extends Activity {
	EditText name;
	EditText place;
	TimePicker time;
	Spinner reply;
	Spinner prenotification;
	Spinner sound;
	Button cancle;
	Button ok;
	String mode;
	int position;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_scheduling);
	    
	    Intent i = getIntent();
		position = i.getIntExtra("position", 0);
		System.out.println("position : " + position);
		final Schedule s = CalendarFragment.adapter.getItem(position);
		//final Schedule s = new Schedule();
		s.date = i.getStringExtra("date");
	    
	     cancle = (Button) findViewById(R.id.schedulecancle);
	     cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	     
	     name = (EditText) findViewById(R.id.schedulenameeditview);
	     name.setText(s.calendar_name);
	     place = (EditText) findViewById(R.id.scheduleplaceeditview);
	     place.setText(s.place);
	     time = (TimePicker) findViewById(R.id.timePicker);
	     time.setCurrentHour(s.hour);
	     time.setCurrentMinute(s.min);
	     time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				s.hour=hourOfDay;
				s.min=minute;
			}
		});
	     reply = (Spinner) findViewById(R.id.schedulereply);
	     ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.reply_array, android.R.layout.simple_spinner_item);
	     adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     reply.setAdapter(adapter1);
	     reply.setSelection(s.reply);
	     reply.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
					s.reply = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
	    	 
	     });
	     prenotification = (Spinner) findViewById(R.id.scheduleprenotification);
	     ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.prenotification_array, android.R.layout.simple_spinner_item);
	     adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     prenotification.setAdapter(adapter2);
	     prenotification.setSelection(s.prealarm);
	     prenotification.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
					s.prealarm = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
	    	 
	     });
	     sound = (Spinner) findViewById(R.id.schedulesound);
	     ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.sound_array, android.R.layout.simple_spinner_item);
	     adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     sound.setAdapter(adapter3);
	     sound.setSelection(s.sound);
	     sound.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
					s.sound = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
	    	 
	     });
	     
	     ok = (Button) findViewById(R.id.scheduleok);
	     ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				s.calendar_name = name.getText().toString();
				s.place = place.getText().toString();
				
				NetworkManager.getInstnace().getCalendarAdd(ScheduleModifyActivity.this, 1, s, new OnResultListener<ScheduleAddResult>() {

					@Override
					public void onSuccess(ScheduleAddResult r) {
						// TODO Auto-generated method stub
						if(r.success == 1) {
							CalendarFragment.adapter.items.set(position, s);
							CalendarFragment.adapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub
						Log.d("net", ""+code);
					}
					
				});
				
				finish();
			}
		});
	}

}
