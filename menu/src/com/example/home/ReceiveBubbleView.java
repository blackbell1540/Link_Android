package com.example.home;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiveBubbleView extends FrameLayout{

	//init();
	public ReceiveBubbleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ReceiveBubbleView(Context context) {
		super(context);
		init();
	}
	
	//views
	TextView contentText;
	ImageView contentImage;
	ImageView profile;
	
	//init data
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_chatting_receive, this);
		contentText = (TextView)findViewById(R.id.chatting_receive_text);
		contentImage = (ImageView)findViewById(R.id.chatting_receive_image);
		profile = (ImageView)findViewById(R.id.chatting_receive_profile);
		
	}
	
	//set Data
	Bubble mData;
	public void setBubble(Bubble data)
	{
		mData = data;
		if (data.picture.equals("-1")) {
			contentText.setText(data.date + "\n" + data.message);
		} else {
			InputStream stream = new ByteArrayInputStream(Base64.decode(data.picture, Base64.DEFAULT));
			Bitmap bmp = BitmapFactory.decodeStream(stream);
	        contentImage.setImageBitmap(bmp);
		}
		profile.setImageResource(R.drawable.ic_launcher);
	}
	
}

