package com.example.letter;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;
import com.example.menu.SharedPreferenceManager;

public class WriteLetterActivity extends Activity {

	//views
	EditText editContent;
	Button buttonWrite;
	
	//send data
	int link_id;
	int user_id;
	String letter_content;
	String date;
	
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
				sendToServer();
			}
		});
	}
	
	public void sendToServer()
	{
	//	link_id = SharedPreferenceManager.getInstance().getLinkId();
	//	user_id = SharedPreferenceManager.getInstance().getUserId();
		link_id = 1;
		user_id = 1;
		
		letter_content = editContent.getText().toString();
		
		Calendar cal = Calendar.getInstance();
		int cYear = cal.get(cal.YEAR);
		int cMonth = cal.get(cal.MONTH)+1;
		int cDate = cal.get(cal.DATE);
		date = cYear + "-" + cMonth + "-" + cDate;
		
		NetworkManager.getInstnace().sendLetter(WriteLetterActivity.this, link_id, user_id, letter_content, date, new OnResultListener<ResultWriteLetter>() {
			
			@Override
			public void onSuccess(ResultWriteLetter result) {
				Toast.makeText(WriteLetterActivity.this, "편지가 전송되었습니다.",Toast.LENGTH_SHORT).show();
				finish();
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
