package com.example.menu.optionalarm;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.menu.R;
import com.example.menu.SharedPreferenceManager;

public class FragmentOptionAlarm extends DialogFragment{
	//views
	Button btn;
	CheckBox checkSound, checkVibration;
	Spinner spinAlarm;
	ArrayAdapter<String> alarmAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.MyDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//dialog option
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setLayout(360,  615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_alram,  container, false);
		
		//find views
		checkSound = (CheckBox)view.findViewById(R.id.checkSound);
		checkVibration = (CheckBox)view.findViewById(R.id.checkVibration);
		spinAlarm = (Spinner)view.findViewById(R.id.spinnerAlarm);
		alarmAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
		alarmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinAlarm.setAdapter(alarmAdapter);
		
		//spinner setting
		alarmAdapter.add("alarm1");
		alarmAdapter.add("alarm2");
		alarmAdapter.add("alarm3");
		
		//spinner select
		spinAlarm.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}

		});
		
		//button alarm setting click
		btn = (Button)view.findViewById(R.id.buttonSetting);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onSettingButton("success"); }
			}
		});
		
		//button cancel click
		btn = (Button)view.findViewById(R.id.buttonDialCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dismiss();
			}
		});
	
		//sound check box view
		if(SharedPreferenceManager.getInstance().getSound().equals("ON"))
		{ checkSound.setChecked(true); }
		else
		{ checkSound.setChecked(false); }
		
		//sound check box on
		if(checkSound.isChecked())
		{ SharedPreferenceManager.getInstance().setSound("ON"); }
		else
		{ SharedPreferenceManager.getInstance().setSound("OFF"); }
		
		//vibration check box view
		if(SharedPreferenceManager.getInstance().getVibration().equals("ON"))
		{ checkVibration.setChecked(true); }
		else
		{ checkVibration.setChecked(false); }
		
		//vibration check box on/off
		if(checkVibration.isChecked())
		{ SharedPreferenceManager.getInstance().setVibration("ON"); }
		else
		{ SharedPreferenceManager.getInstance().setVibration("OFF");}
		
		return view;
	}
	
	
	public interface onSettingButtonClick
	{ public void onSettingButton(String message); }
	
	onSettingButtonClick mListener;
	
	public void setOnSettingButtonClick( onSettingButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {	
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher); 
		getDialog().setTitle("alram");
	}
}
