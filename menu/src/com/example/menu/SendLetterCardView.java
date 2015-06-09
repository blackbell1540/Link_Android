package com.example.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SendLetterCardView extends FrameLayout{

	//init();
	public SendLetterCardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SendLetterCardView(Context context) {
		super(context);
		init();
	}
	
	//views
	ImageView profile;
	TextView content;
	Button delete;
	
	//init data
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_letter_send, this);
		profile = (ImageView)findViewById(R.id.imageProfileSLetterCard);
		content = (TextView)findViewById(R.id.textContentSLetterCard);
		delete = (Button)findViewById(R.id.buttonDeleteSLetterCard);
		
	}
	
	//set Data
	LetterCardData mData;
	public void setDataSLetterCard(LetterCardData data)
	{
		mData = data;
		content.setText(data.letterContent);
		
	}
	
}

