package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	//Handler
	Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		String start = "N";
				//SharedPreferenceManager.getInstance().getIsSignUp();
		String link = "Y";
		//SharedPreferenceManager.getInstance().getIsLinked();
		
		//already signUP
		if(start.equals("Y"))
		{	
			//not linked - waiting
			if(link.equals("N"))
			{	moveWaiting(); }
			else  //link already
			{ moveHome(); }

		}
		else   //sign up not yet
		{	
			moveSignUp();
		}
		
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
	private void moveSignUp()
	{
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
//				Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
//				startActivity(intent);
//				finish();
				
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
