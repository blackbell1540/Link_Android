package com.example.menu.profile;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.menu.R;

public class FragmentOptionProfile extends DialogFragment
{

	//views
	TextView textName, textBirth, textPhone;
	ImageView imageProfile, imageSelect;
	EditText editName, editBirth;
	Button btn;
	LinearLayout showProfile, modiProfile, showButton, modiButton;
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
		textName = (TextView)view.findViewById(R.id.TextProfileName);
		textBirth = (TextView)view.findViewById(R.id.TextProfileBirth);
		textPhone = (TextView)view.findViewById(R.id.TextProfilePhone);
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		imageSelect = (ImageView)view.findViewById(R.id.imageSelect);
		editName = (EditText)view.findViewById(R.id.editName);
		editBirth = (EditText)view.findViewById(R.id.editBirth);
		showProfile = (LinearLayout)view.findViewById(R.id.ShowProfile);
		modiProfile = (LinearLayout)view.findViewById(R.id.ModiProfile);
		showButton = (LinearLayout)view.findViewById(R.id.ShowButton);
		modiButton = (LinearLayout)view.findViewById(R.id.ModiButton);
		
		initUserInfo();
		
		//modify button click
		btn = (Button)view.findViewById(R.id.buttonModify);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showProfile.setVisibility(View.GONE);
				modiProfile.setVisibility(View.VISIBLE);
				showButton.setVisibility(View.GONE);
				modiButton.setVisibility(View.VISIBLE);
			}
		});
		
		//ok button click
		btn = (Button)view.findViewById(R.id.buttonThemeCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		//modify cancel button click
		btn = (Button)view.findViewById(R.id.buttonDialCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showProfile.setVisibility(View.VISIBLE);
				modiProfile.setVisibility(View.GONE);
				showButton.setVisibility(View.VISIBLE);
				modiButton.setVisibility(View.GONE);
			}
		});
		
		//modify complete button click
		btn = (Button)view.findViewById(R.id.buttonModiComplete);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateUserProfile();
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

	private void initUserInfo(){
//		NetworkManager.getInstnace().getUserInfo(getActivity(), userId, new OnResultListener<UserInfo>() {
//			
//			@Override
//			public void onSuccess(UserInfo user) {
//				if(user.success.equals("1"))
//				{
//					String userName = user.result.get(0).user_name;
//					int userPhoneNumber = user.result.get(0).phone_number;
//					
//					textName.setText(userName);
//					textPhone.setText(""+userPhoneNumber);
//					
//				}else
//				{ Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show(); }
//			}
//			
//			@Override
//			public void onFail(int code) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}
	
	private void updateUserProfile()
	{}


}
