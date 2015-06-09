package com.example.letter;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadLetterActivity extends Activity {

	//views
	ImageView imageProfile;
	TextView textDate, textContent;
	
	int position = 0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_read_letter);
	    
	    //find views
	    imageProfile = (ImageView)findViewById(R.id.imageReadLetterProfile);
	    textDate = (TextView)findViewById(R.id.textLetterDate);
	    textContent = (TextView)findViewById(R.id.textLetterContent);
	    
	    //getIntent
	    Intent intent = getIntent();
	    position = intent.getIntExtra(LetterFragment.SELECTED_CARD_NUMBER, 0);
	    
	    textContent.setText("Item" + position + "clicked");
	}

}
