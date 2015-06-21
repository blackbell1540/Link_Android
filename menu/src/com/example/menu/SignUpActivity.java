package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity{

	//views
	EditText editFindPartner, editInvitePartner;
	Button buttonFindPartner, buttonInvitePartner;
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
				//no exist partner
				
				//exist partner - waiting
				String waiting = SharedPreferenceManager.getInstance().getIsLinked();
				if(waiting.equals("N"))
				{
					Intent intent = new Intent(SignUpActivity.this, WaitingActivity.class);
					startActivity(intent);
					finish();
				}
				
			}
		});
	    
	    //invite partner button click
	    buttonInvitePartner = (Button)findViewById(R.id.buttonInvitePartner);
	    buttonInvitePartner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	}
}
