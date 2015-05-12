package com.example.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiveLetterCardView extends FrameLayout{

	//init();
	public ReceiveLetterCardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ReceiveLetterCardView(Context context) {
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
		LayoutInflater.from(getContext()).inflate(R.layout.item_letter_receive, this);
		profile = (ImageView)findViewById(R.id.imageProfileRLetterCard);
		content = (TextView)findViewById(R.id.textContentRLetterCard);
		delete = (Button)findViewById(R.id.buttonDeleteRLetterCard);
		
	}
	
	//set Data
	LetterCardData mData;
	public void setDataRLetterCard(LetterCardData data)
	{
		mData = data;
		content.setText(data.letterContent);
		
	}
	
}

