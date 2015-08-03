package com.example.letter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;
import com.google.android.gms.common.SignInButton;

public class ReadLetterActivity extends Activity {

	//views
	ImageView imageProfile;
	TextView textDate, textContent;
	Button btn;
	int letter_id;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_read_letter);
	    
	    //find views
	    imageProfile = (ImageView)findViewById(R.id.imageReadLetterProfile);
	    textDate = (TextView)findViewById(R.id.textLetterDate);
	    textContent = (TextView)findViewById(R.id.textLetterContent);
	    
	    //getIntent - letter_id
	    Intent intent = getIntent();
	    letter_id = intent.getIntExtra(LetterFragment.SELECTED_CARD_NUMBER, 0);
	    
	    //button Back
	    btn = (Button)findViewById(R.id.buttonBack);
	    btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	    
	    initData();
	}
	
	public void initData()
	{
		NetworkManager.getInstnace().getLetter(ReadLetterActivity.this, letter_id, new OnResultListener<ResultLetter>() {
			
			@Override
			public void onSuccess(ResultLetter result) {
				if(result.success.equals("1"))
				{
					String letterContent = result.result.get(0).content;
					String letterDate = result.result.get(0).date.substring(0, 10);
					
					textDate.setText(letterDate);
					textContent.setText(letterContent);
				}else
				{
					Log.i("readLetter", result.message);
				}
				
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
