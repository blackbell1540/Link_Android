package com.example.menu;

import java.util.Calendar;

import com.example.menu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SendBubbleView extends FrameLayout{

	//init();
	public SendBubbleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SendBubbleView(Context context) {
		super(context);
		init();
	}
	
	//views
	ImageView profile;
	TextView content;
	
	//init data
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_chatting_send, this);
		profile = (ImageView)findViewById(R.id.bubble_send_image);
		content = (TextView)findViewById(R.id.bubble_send_text);
		
	}
	
	//set Data
	Bubble mData;
	public void setBubble(Bubble data)
	{
		mData = data;
		content.setText(Calendar.YEAR + "년 "
				+ Calendar.MONTH + "월 "
				+ Calendar.DATE + "일 "
				+ Calendar.HOUR + "시 "
				+ Calendar.MINUTE + "분\n" + data.content);
		
	}
	
}

