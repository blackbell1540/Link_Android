package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menu.NetworkManager.OnResultListener;

public class SignUpActivity extends Activity{

	//views
	EditText editFindPartner, editInvitePartner;
	Button buttonFindPartner, buttonInvitePartner;
	String partenrMail, partnerPhone;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sign_up);
	    
	    //find views
	    editFindPartner = (EditText)findViewById(R.id.editFindPartner);
	    editInvitePartner = (EditText)findViewById(R.id.editInvitePartner);
	    
	    //findPartner button click
	    //button find
	    buttonFindPartner = (Button)findViewById(R.id.buttonFindPartner);
	    buttonFindPartner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				partenrMail = editFindPartner.getText().toString();
				if(partenrMail.length() != 0)
				{ findPartner(); }
				else
				{ Toast.makeText(SignUpActivity.this, "상대방의 email을 입력해주세요", Toast.LENGTH_SHORT).show(); }
			}
		});
	    
	    //invite partner button click
	    buttonInvitePartner = (Button)findViewById(R.id.buttonInvitePartner);
	    buttonInvitePartner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	    
	}
	
	public void findPartner()
	{
		int user_id = SharedPreferenceManager.getInstance().getUserId();
		NetworkManager.getInstnace().findPartner(SignUpActivity.this, partenrMail, user_id, new OnResultListener<FindPartnerResult>() {
			
			@Override
			public void onSuccess(FindPartnerResult result) {
				if(result.message.equals("OK"))
				{
					//move waiting Activity
					Intent intent = new Intent(SignUpActivity.this, WaitingActivity.class);
					startActivity(intent);
					finish();
				}else
				{	//toast : there is no partner
					Toast.makeText(SignUpActivity.this, result.message, Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
