<<<<<<< HEAD:menu/src/com/example/menu/FragmentOptionProfileModi.java
<<<<<<< HEAD
package com.example.menu;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentOptionProfileModi extends DialogFragment{

	//views
	ImageView imageProfile;
	Button buttonComplete;
	EditText editProfileName, editProfileBirth, editProfilePhone;
	
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
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_profile_modify,  container, false);
		
		//find views
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		editProfileName = (EditText)view.findViewById(R.id.editProfileName);
		editProfileBirth = (EditText)view.findViewById(R.id.editProfileBirth);
		editProfilePhone = (EditText)view.findViewById(R.id.editProfilePhone);
		
		//complete button click
		buttonComplete = (Button)view.findViewById(R.id.buttonComplete);
		buttonComplete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onCompleteButton("success"); }
				Toast.makeText(getActivity(), "modi complete", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}
	
	//modify button listener
	public interface onCompleteButtonClick
	{ public void onCompleteButton(String message); }
	
	onCompleteButtonClick mListener;
	
	public void setOnCompleteButtonClick(onCompleteButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("profile_modi");
	}
	
}
=======
package com.example.menu;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentOptionProfileModi extends DialogFragment{

	//views
	ImageView imageProfile;
	Button buttonComplete;
	EditText editProfileName, editProfileBirth, editProfilePhone;
	
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
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_profile_modify,  container, false);
		
		//find views
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		editProfileName = (EditText)view.findViewById(R.id.editProfileName);
		editProfileBirth = (EditText)view.findViewById(R.id.editProfileBirth);
		editProfilePhone = (EditText)view.findViewById(R.id.editProfilePhone);
		
		//complete button click
		buttonComplete = (Button)view.findViewById(R.id.buttonComplete);
		buttonComplete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onCompleteButton("success"); }
				Toast.makeText(getActivity(), "modi complete", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}
	
	//modify button listener
	public interface onCompleteButtonClick
	{ public void onCompleteButton(String message); }
	
	onCompleteButtonClick mListener;
	
	public void setOnCompleteButtonClick(onCompleteButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("profile_modi");
	}
	
}
>>>>>>> 762baa7a8c99323cc6239fedf8ec326f0c939d23
=======
package com.example.menu.optionprofile;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentOptionProfileModi extends DialogFragment{

	//views
	ImageView imageProfile;
	Button buttonComplete;
	EditText editProfileName, editProfileBirth, editProfilePhone;
	
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
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_profile_modify,  container, false);
		
		//find views
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		editProfileName = (EditText)view.findViewById(R.id.editProfileName);
		editProfileBirth = (EditText)view.findViewById(R.id.editProfileBirth);
		editProfilePhone = (EditText)view.findViewById(R.id.editProfilePhone);
		
		//complete button click
		buttonComplete = (Button)view.findViewById(R.id.buttonComplete);
		buttonComplete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onCompleteButton("success"); }
				Toast.makeText(getActivity(), "modi complete", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}
	
	//modify button listener
	public interface onCompleteButtonClick
	{ public void onCompleteButton(String message); }
	
	onCompleteButtonClick mListener;
	
	public void setOnCompleteButtonClick(onCompleteButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("profile_modi");
	}
	
}
>>>>>>> origin/0512_menus:menu/src/com/example/menu/optionprofile/FragmentOptionProfileModi.java
