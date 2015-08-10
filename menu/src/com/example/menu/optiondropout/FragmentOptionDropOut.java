package com.example.menu.optiondropout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;
import com.example.menu.SharedPreferenceManager;
import com.example.menu.SplashActivity;

public class FragmentOptionDropOut extends DialogFragment {

	//views
	Button btn;
	int user_id;
	
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
		View view = inflater.inflate(R.layout.fragment_option_dropout, container, false);

		//button OK click
		btn = (Button)view.findViewById(R.id.buttonOK);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				user_id = SharedPreferenceManager.getInstance().getUserId();
				DropOut(user_id);
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
		return view;
	}
	
	private void DropOut(int user_id)
	{
		NetworkManager.getInstnace().dropOut(getActivity(), user_id, new OnResultListener<ResultDropOut>() {
			
			@Override
			public void onSuccess(ResultDropOut result) {
				//information init
				SharedPreferenceManager.getInstance().setIsLinked("N");
				SharedPreferenceManager.getInstance().setIsSignUp("N");
				SharedPreferenceManager.getInstance().setLinkId(0);
				SharedPreferenceManager.getInstance().setUserEmail(null);
				SharedPreferenceManager.getInstance().setUserId(0);
				SharedPreferenceManager.getInstance().setAlarm(0);
				SharedPreferenceManager.getInstance().setFont(null);
				SharedPreferenceManager.getInstance().setSound(null);
				SharedPreferenceManager.getInstance().setTheme(0);
				SharedPreferenceManager.getInstance().setUserPhone(null);
				SharedPreferenceManager.getInstance().setVibration(null);
				Toast.makeText(getActivity(), "Ε»Επ ΌΊ°ψ", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity(), SplashActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
	}
}
