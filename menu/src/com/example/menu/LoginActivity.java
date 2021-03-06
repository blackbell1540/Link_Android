package com.example.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.menu.NetworkManager.OnResultListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;

public class LoginActivity extends Activity implements
 ConnectionCallbacks, OnConnectionFailedListener {

	//strings
	private static final int REQUEST_CODE_RESLOVE_ERR = 1001;
	private boolean mResolvingErr = false;
	private static final String STATE_RESOLVING_ERR = "resloving_err";
	
	//view
	private SignInButton buttonSignIn;
	private GoogleApiClient mClient;
	private ConnectionResult mConnectionResult;
	private ProgressDialog mConnectionProgressDialog;
	
	String accountEmail;	//google account email
	String phoneNumber;		//device phone number
	int request;			//check request value - move next stage
	int user_id;			//get user_id after join, check request with user_id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//get device phone number
		TelephonyManager telManager = (TelephonyManager)LoginActivity.this.getSystemService(LoginActivity.this.TELEPHONY_SERVICE);
		phoneNumber = telManager.getLine1Number().substring(3);	//0~2 : +82
		
		//client connection
		mClient = new GoogleApiClient.Builder(this)
		.addApi(Plus.API)
		.addScope(Plus.SCOPE_PLUS_LOGIN)
		.addConnectionCallbacks(connectionCallbacks)
		.addOnConnectionFailedListener(onConnectionFailedListener)
		.build();
		
		mResolvingErr = savedInstanceState != null && savedInstanceState.getBoolean(STATE_RESOLVING_ERR, false);
		
		//button Sign In click
		buttonSignIn = (SignInButton)findViewById(R.id.buttonSignIn);
		buttonSignIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!mResolvingErr)
				{ mClient.connect();  }
				
			}
		});
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putBoolean(STATE_RESOLVING_ERR, mResolvingErr);
	}
	
	//connect callbacks
	private GoogleApiClient.ConnectionCallbacks connectionCallbacks = new ConnectionCallbacks() {
		
		@Override
		public void onConnectionSuspended(int cause) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onConnected(Bundle connectionHint) {
			//after connecting
			accountEmail = Plus.AccountApi.getAccountName(mClient);	//get account email
			Login();												//login or join with email
		}
	};
	
	//login
	private void Login()
	{
		Toast.makeText(LoginActivity.this, "login try", Toast.LENGTH_SHORT).show();
		NetworkManager.getInstnace().getUserLoginJoin(LoginActivity.this, accountEmail, phoneNumber, new OnResultListener<LoginResult>() {
			
			@Override
			public void onSuccess(LoginResult result) {
				Toast.makeText(LoginActivity.this, "login ONsuccess", Toast.LENGTH_SHORT).show();
				if(result.success.equals("1"))
				{
					Toast.makeText(LoginActivity.this, "login result.success", Toast.LENGTH_SHORT).show();
					//set inserted_user_id, google_email, phone_number
					SharedPreferenceManager.getInstance().setUserEmail(accountEmail);
					SharedPreferenceManager.getInstance().setUserPhone(phoneNumber);
					//user_id = 15;	//!!!server will not send user_id when login!!!
					SharedPreferenceManager.getInstance().setUserId(user_id);
					SharedPreferenceManager.getInstance().setIsSignUp("Y");
					
					Log.i("login", accountEmail + " " + phoneNumber);
					
					//check request status
					checkMyWaiting();
					
					
					//request - 0 : no send/receive	
					if(request == 0)
					{ 	//move SignUp & find partner
						Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
						startActivity(intent);
						finish();
					}
					else
					{	//move waiting 
						Intent intent = new Intent(LoginActivity.this, WaitingActivity.class);
						startActivity(intent);
						finish();
					}

				}else
				{
					Toast.makeText(LoginActivity.this, "login result.success != 1", Toast.LENGTH_SHORT).show();
				}
				
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginActivity.this, "login onFail", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void checkMyWaiting()
	{
		//int user_id = SharedPreferenceManager.getInstance().getUserId();
		
		NetworkManager.getInstnace().checkReq(LoginActivity.this, user_id, new OnResultListener<CheckReqResult>() {
			
			@Override
			public void onSuccess(CheckReqResult result) {
				if(result.success.equals("1"))
				{ request = result.result.get(0).request; }
				else
				{ Log.i("request", result.message); }
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}	
	
	//Connect Fail
	private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = new OnConnectionFailedListener() {
		
		@Override
		public void onConnectionFailed(ConnectionResult result) {
			if(mResolvingErr){
				return; 
			}else if(result.hasResolution()){
				mResolvingErr = true;
				try {
					result.startResolutionForResult(LoginActivity.this, REQUEST_CODE_RESLOVE_ERR);
				} catch (SendIntentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mClient.connect();
				}
			}else{
				Toast.makeText(LoginActivity.this, "fail",Toast.LENGTH_SHORT).show();
				mResolvingErr = true;
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if(mConnectionProgressDialog.isShowing()){
			if(result.hasResolution()){
				try {
					result.startResolutionForResult(this, REQUEST_CODE_RESLOVE_ERR);
				} catch (SendIntentException e) {
					mClient.connect();
				}
			}
		}
		mConnectionResult = result;
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mClient.disconnect();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CODE_RESLOVE_ERR){
			mResolvingErr = false;
			if(resultCode == RESULT_OK)
			{
				if(!mClient.isConnecting() && !mClient.isConnected())
				{ mClient.connect(); }
			}
		}
	}
	@Override
	public void onConnected(Bundle connectionHint) {
		mConnectionProgressDialog.dismiss();
		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		
	}
}