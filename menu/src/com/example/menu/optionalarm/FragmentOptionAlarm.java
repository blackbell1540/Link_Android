package com.example.menu.optionalarm;

import com.example.menu.R;
import com.example.menu.R.drawable;
import com.example.menu.R.id;
import com.example.menu.R.layout;
import com.example.menu.R.style;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

public class FragmentOptionAlarm extends DialogFragment{

	//views
	Button btn;
	
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
		
		//button alarm setting click
		btn = (Button)view.findViewById(R.id.buttonAlarmSetting);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onSettingButton("success"); }
			}
		});
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
