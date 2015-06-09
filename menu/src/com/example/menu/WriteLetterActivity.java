package com.example.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteLetterActivity extends Activity {

	//views
	EditText editContent;
	Button buttonWrite;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_write_letter);
	    
	    //find views
	    editContent = (EditText)findViewById(R.id.editLetterContent);
	    
	    //send letter
	    buttonWrite = (Button)findViewById(R.id.buttonWriteLetter);
	    buttonWrite.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(WriteLetterActivity.this, "add", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
