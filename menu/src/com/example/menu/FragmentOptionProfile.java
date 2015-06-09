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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentOptionProfile extends DialogFragment{

	//views
	TextView textName, textBirth, textPhone;
	ImageView imageProfile;
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
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_profile, container, false);
		
		//find views
		textName = (TextView)view.findViewById(R.id.textProfileName);
		textBirth = (TextView)view.findViewById(R.id.textProfileBirth);
		textPhone = (TextView)view.findViewById(R.id.textProfilePhone);
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		//modify button click
		btn = (Button)view.findViewById(R.id.buttonModify);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onModifyButton("success"); }
				Toast.makeText(getActivity(), "mody?", Toast.LENGTH_SHORT).show();
				
			}
		});
		return view;
	}
	
	//modify button listener
	public interface onModifyButtonClick
	{ public void onModifyButton(String message); }
	
	onModifyButtonClick mListener;
	
	public void setOnModifyButtonClick(onModifyButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("profile");
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu.LinkNetworkManager.OnResultListener;

public class FragmentOptionProfile extends DialogFragment{

	//views
	TextView textName, textBirth, textPhone;
	ImageView imageProfile;
	Button btn;
	int userId = 1;
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
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_profile, container, false);
		
		//find views
		textName = (TextView)view.findViewById(R.id.textProfileName);
		textBirth = (TextView)view.findViewById(R.id.textProfileBirth);
		textPhone = (TextView)view.findViewById(R.id.textProfilePhone);
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		//modify button click
		btn = (Button)view.findViewById(R.id.buttonModify);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null)
				{ mListener.onModifyButton("success"); }
				Toast.makeText(getActivity(), "mody?", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		initUserInfo();
		return view;
	}
	
	//modify button listener
	public interface onModifyButtonClick
	{ public void onModifyButton(String message); }
	
	onModifyButtonClick mListener;
	
	public void setOnModifyButtonClick(onModifyButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("profile");
	}

	private void initUserInfo(){
		LinkNetworkManager.getInstnace().getUserInfo(getActivity(), userId, new OnResultListener<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo user) {
				if(user.success.equals("1"))
				{
					String userName = user.result.get(0).user_name;
					int userPhoneNumber = user.result.get(0).phone_number;
					
					textName.setText(userName);
					textPhone.setText(""+userPhoneNumber);
					
				}else
				{ Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show(); }
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
>>>>>>> 762baa7a8c99323cc6239fedf8ec326f0c939d23
