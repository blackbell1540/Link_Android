package com.example.menu;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentOptionTheme extends DialogFragment{

	//views
	ImageView imageTheme;
	Button btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		View view = inflater.inflate(R.layout.fragment_option_theme, container, false);
		
		//find views
		imageTheme = (ImageView)view.findViewById(R.id.imageTheme);
		
		//button 
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
	}
}
