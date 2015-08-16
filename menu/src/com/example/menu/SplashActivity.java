package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.menu.NetworkManager.OnResultListener;

public class SplashActivity extends Activity {

	//Handler
	Handler mHandler = new Handler();
	
	String e_mail;
	String phone_number;
	int request;
	int user_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		//sign up check
		String start = SharedPreferenceManager.getInstance().getIsSignUp();

		//link check
		SharedPreferenceManager.getInstance().setIsLinked("Y");
		String link = SharedPreferenceManager.getInstance().getIsLinked();

		//already signUP
		if(start.equals("Y"))
		{	
			//get user info
			e_mail = SharedPreferenceManager.getInstance().getUserEmail();
			phone_number = SharedPreferenceManager.getInstance().getUserPhone();
			user_id = SharedPreferenceManager.getInstance().getUserId();
			
			Login();
						
			//not linked - find or waiting
			if(link.equals("N"))
			{ checkMyWaiting(); }
			else  //link already
			{ moveHome(); }
		}
		else   //sign up not yet
		{ moveLogin(); }
		
	}
	
	//login
	private void Login()
	{
		NetworkManager.getInstnace().getUserLoginJoin(SplashActivity.this, e_mail, phone_number, new OnResultListener<LoginResult>() {
			
			@Override
			public void onSuccess(LoginResult result) {
				if(result.success.equals("1"))
				{
					SharedPreferenceManager.getInstance().setUserId(result.result);
					SharedPreferenceManager.getInstance().setIsSignUp("Y");
				}
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	//check my req
	private void checkMyWaiting()
	{
		NetworkManager.getInstnace().checkReq(SplashActivity.this, user_id, new OnResultListener<CheckReqResult>() {
			
			@Override
			public void onSuccess(CheckReqResult result) {
				if(result.success.equals("1"))
				{ 
					request = result.result.get(0).request;
					Log.i("request", request + " " + user_id );
					//request - 0 : no send/receive
					//move SignUp & find partner
					Log.i("request code",""+request);
					if(request == 0)
					{ moveSignUp(); }
					else
					{ moveWaiting(); }
				}
				else
				{ Log.i("request", result.message); }
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}	
	
	//move Main
	private void moveHome()
	{
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//set signUp Yes
				SharedPreferenceManager.getInstance().setIsSignUp("Y");
				//startActivity
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 3000);
	}
	
	//move SignUp
	private void moveLogin()
	{
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				
			}
		}, 1000);
	}
	
	//move Wating
	private void moveWaiting()
	{
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, WaitingActivity.class);
				startActivity(intent);
				finish();
			}
		}, 1000);
	}
	
	//moveFindPartner
	private void moveSignUp()
	{
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
				startActivity(intent);
				finish();
			}
		}, 1000);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}