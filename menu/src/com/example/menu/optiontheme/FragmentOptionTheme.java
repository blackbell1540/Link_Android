package com.example.menu.optiontheme;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.menu.R;
import com.example.menu.SharedPreferenceManager;

public class FragmentOptionTheme extends DialogFragment {
	//views
	ImageView imageTheme;
	Button btn;
	
	int[] themeResource = {R.drawable.ffffff, R.drawable.fedcba, R.drawable.t776644 };
	
	int cThemeNum = 0;
	
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

		//set current theme
		cThemeNum = SharedPreferenceManager.getInstance().getTheme();
		imageTheme.setImageResource(themeResource[cThemeNum]);
		
		//change theme
		btn = (Button)view.findViewById(R.id.buttonBeforeTheme);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cThemeNum = (cThemeNum+1)%3;
				imageTheme.setImageResource(themeResource[cThemeNum]);
			}
		});
		
		btn = (Button)view.findViewById(R.id.buttonNextTheme);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cThemeNum = (cThemeNum+3-1)%3;
				imageTheme.setImageResource(themeResource[cThemeNum]);
			}
		});
		
		//button complete click
		btn = (Button)view.findViewById(R.id.buttonThemeOK);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				SharedPreferenceManager.getInstance().setTheme(cThemeNum);
				dismiss();
			}
		});
		
		//button cancel click
		btn = (Button)view.findViewById(R.id.buttonCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
	}
}
