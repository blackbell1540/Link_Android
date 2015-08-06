package com.example.menu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.menu.NetworkManager.OnResultListener;

public class WaitingActivity extends Activity{

	int user_id;	//my id
	int request;	//request number
	String sender;	//sender's mail
	String reply;	//reply yes or no
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.acitivity_wating);
	    
	    //check my waiting status
	    user_id = SharedPreferenceManager.getInstance().getUserId();
	    checkMyWaiting();
	}
	
	public void receiveReq()
	{    	
    	AlertDialog.Builder dial_request = new AlertDialog.Builder(WaitingActivity.this);
    	dial_request.setMessage(sender + "´ÔÀÇ ¿¬°á ¿äÃ»ÀÌ ÀÖ½À´Ï´Ù.").setCancelable(false)
    			.setPositiveButton("½Â³«", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						reply = "yes";
						replyReq(reply);
						SharedPreferenceManager.getInstance().setIsLinked("Y");
						Intent intent = new Intent(WaitingActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}
				}).setNegativeButton("°ÅÀý",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						reply = "no";
						replyReq(reply);
						Intent intent = new Intent(WaitingActivity.this, SignUpActivity.class);
						startActivity(intent);
						finish();							
					}
				});
    	AlertDialog dial = dial_request.create();
    	dial.show();
	}
	
	public void checkReqSender(int user_id)
	{
		NetworkManager.getInstnace().getUserInfo(WaitingActivity.this, user_id, new OnResultListener<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo result) {
				sender = result.result.get(0).email;
				receiveReq();
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void replyReq(String reply)
	{
		NetworkManager.getInstnace().replyReqest(WaitingActivity.this, user_id, reply, request, new OnResultListener<ReplyReqResult>() {

			@Override
			public void onSuccess(ReplyReqResult result) {
				
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void checkMyWaiting()
	{
		NetworkManager.getInstnace().checkReq(WaitingActivity.this, user_id, new OnResultListener<CheckReqResult>() {
			
			@Override
			public void onSuccess(CheckReqResult result) {
				if(result.success.equals("1"))
				{ 
					request = result.result.get(0).request;
				    //if I'm receiving request
				    if(request != user_id)
				    {
						//who is request sender
				    	checkReqSender(request);
				    }
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
}
