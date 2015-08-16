package com.example.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.menu.NetworkManager;
import com.example.menu.R;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R.array;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class ScheduleAddActivity extends Activity {
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
	    
	    final Schedule s = new Schedule();
	    
	     cancle = (Button) findViewById(R.id.schedulecancle);
	     cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	     
	     name = (EditText) findViewById(R.id.schedulenameeditview);
	     place = (EditText) findViewById(R.id.scheduleplaceeditview);
	     time = (TimePicker) findViewById(R.id.timePicker);
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
				
				Intent i = getIntent();
				s.date = i.getStringExtra("date");
				s.year = i.getIntExtra("year", 0);
				s.month = i.getIntExtra("month", 0);
				s.day = i.getIntExtra("day", 0);
				NetworkManager.getInstnace().getCalendarAdd(ScheduleAddActivity.this, 0, s, new OnResultListener<ScheduleAddResult>() {

					@Override
					public void onSuccess(ScheduleAddResult r) {
						// TODO Auto-generated method stub
						if(r.success == 1) {
							s.calendar_id = r.result;
							CalendarFragment.adapter.add(s);
							setAlarm(s);	
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub
					}
					
				});
				finish();
			}
		});
	}
	
	private void setAlarm(Schedule s) {
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		//alarmManager.setRepeating(type, triggerAtMillis, intervalMillis, operation);
		 
        Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
        i.putExtra("sound", s.sound);
        i.putExtra("ticker", s.hour + ":" + s.min + " " + s.calendar_name);
        i.putExtra("title", "일정 알림 " + s.date + " " + s.hour + ":" + s.min);
        i.putExtra("text", s.calendar_name + " at " + s.place);
        i.putExtra("id", s.calendar_id);
        PendingIntent pIntent = PendingIntent.getBroadcast(ScheduleAddActivity.this, 0, i, 0);

        Calendar c = Calendar.getInstance();
        c.set(s.year, s.month-1, s.day, s.hour, s.min);
        
        switch(s.prealarm) {
		case 1:
			c.add(c.MINUTE, -5);
			break;
		case 2:
			c.add(c.MINUTE, -10);
			break;
		case 3:
			c.add(c.MINUTE, -30);
			break;
		case 4:
			c.add(c.MINUTE, -60);
			break;
		}
        
        switch(s.reply) {
        case 0:
        	alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pIntent);
        	break;
        case 1:
        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24 * 60 * 60 * 1000, pIntent);
        	break;
        case 2:
        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 7 * 24 * 60 * 60 * 1000, pIntent);
        	break;
        case 3:
        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 30 * 24 * 60 * 60 * 1000, pIntent);
        	break;
        case 4:
        	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 365 * 24 * 60 * 60 * 1000, pIntent);
        	break;
        }
        
        Log.d("alarm", "alarm setted");
		
        
	}

}
