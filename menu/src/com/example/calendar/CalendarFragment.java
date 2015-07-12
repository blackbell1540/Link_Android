package com.example.calendar;


import com.example.gallery.Picture;
import com.example.gallery.PictureAdapter;
import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class CalendarFragment extends Fragment  {
	CalendarView calendar;
	Button plus;
	String date;
	static ListView list;
	static ScheduleAdapter adapter;
	
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_calendar, container, false);
        
        plus = (Button) V.findViewById(R.id.plusScheduleButton);
        plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ScheduleAddActivity.class);
				intent.putExtra("date", date);
				startActivity(intent);
			}
		});
        list = (ListView) V.findViewById(R.id.schedules);
        adapter = new ScheduleAdapter(getActivity());
        adapter.setOnButtonClickListener(new ScheduleAdapter.onButtonClickListener() {
			
			@Override
			public void btnClick(final Schedule s) {
				NetworkManager.getInstnace().getCalendarRemove(getActivity(), s.calendar_id, new OnResultListener<ScheduleRemoveResult>() {

					@Override
					public void onSuccess(ScheduleRemoveResult r) {
						// TODO Auto-generated method stub
						if(r.success == 1)
							adapter.delete(s);
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub
						Log.d("net", ""+code);
					}
					
				});
			}
		});
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), ScheduleModifyActivity.class);
				intent.putExtra("date", date);
				intent.putExtra("position", position);
				startActivity(intent);
			}
        });
        
        //calendarview example : http://examples.javacodegeeks.com/android/core/widget/android-calendarview-example/
        calendar = (CalendarView) V.findViewById(R.id.calendar);
        //calendar.setSelected(false);
        //sets whether to show the week number
        calendar.setShowWeekNumber(false);
        //sets the first day of week according to Calendar. 2=Monday
        calendar.setFirstDayOfWeek(2);
        //The background color for the selected week.
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        //sets the color for the dates of an unfocused month. 
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        //sets the color for the separator line between weeks.
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				adapter.clear();
				plus.setVisibility(Button.VISIBLE);
				date = year + "-" + (month+1) + "-" + dayOfMonth;
				NetworkManager.getInstnace().getCalendarList(getActivity(), date, new OnResultListener<ScheduleListResult>() {

					@Override
					public void onSuccess(ScheduleListResult r) {
						// TODO Auto-generated method stub
						if(r.success == 1) {
							for (Schedule i : r.result){
								i.date = i.date.split("T")[0];
								adapter.add(i);
							}
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub
						Log.d("net", ""+code);
					}
					
				});
			}
        });
        
        plus.setVisibility(Button.INVISIBLE);
        adapter.clear();
        NetworkManager.getInstnace().getCalendarList(getActivity(), "-1", new OnResultListener<ScheduleListResult>() {

			@Override
			public void onSuccess(ScheduleListResult r) {
				// TODO Auto-generated method stub
				if(r.success == 1) {
					for (Schedule i : r.result) {
						i.date = i.date.split("T")[0];
						adapter.add(i);
					}
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				Log.d("net", ""+code);
			}
			
		});

        return V;
        
    }
}