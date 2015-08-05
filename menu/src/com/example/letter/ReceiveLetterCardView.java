package com.example.letter;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

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
	

	//init data
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_letter_receive, this);
		profile = (ImageView)findViewById(R.id.imageProfileRLetterCard);
		content = (TextView)findViewById(R.id.textContentRLetterCard);
		
	}
	
	//set Data
	DataLetter mData;
	public void setDataRLetterCard(DataLetter data)
	{
		mData = data;
		content.setText(data.content);
		
	}
	
}

