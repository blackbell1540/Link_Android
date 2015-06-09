package com.example.home;

import com.example.letter.LetterAdapter;
import com.example.letter.LetterCardData;
import com.example.letter.ReadLetterActivity;
import com.example.letter.WriteLetterActivity;
import com.example.menu.R;
import com.example.menu.R.drawable;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChattingActivity extends Activity {
	ListView list;
	Button menu, imoticon, ok;
	EditText text;
	BubbleAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatting);
		
        list = (ListView) findViewById(R.id.chattinglist);
        adapter = new BubbleAdapter(this);
        list.setAdapter(adapter);
        
        menu = (Button) findViewById(R.id.chat_menu);
        imoticon = (Button) findViewById(R.id.imoticon);
        ok = (Button) findViewById(R.id.chat_ok);
        text = (EditText) findViewById(R.id.chat_text);
       
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Bubble b = new Bubble();
				b.content = text.getText().toString();
				b.imageId = R.drawable.ic_launcher;
				b.type = Bubble.SEND_BUBBLE;
				adapter.add(b);
				text.setText("");
			}
		});

    }

}
