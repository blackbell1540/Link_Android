package com.example.letter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;

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
	
	//delete button interface
	public interface onDeleteClickListener{
		public void onDeleteClick(View view, DataLetter data);
	}
	
	onDeleteClickListener deleListener;
	
	public void setOnDeleteClickListener(onDeleteClickListener listener)
	{ deleListener = listener; }
	
	//init data
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_letter_send, this);
		profile = (ImageView)findViewById(R.id.imageProfileSLetterCard);
		content = (TextView)findViewById(R.id.textContentSLetterCard);
		delete = (Button)findViewById(R.id.buttonDeleteSLetterCard);
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(deleListener != null)
				{ deleListener.onDeleteClick(SendLetterCardView.this, mData);}
			}
		});
		
	}
	
	//set Data
	DataLetter mData;
	public void setDataSLetterCard(DataLetter data)
	{
		mData = data;
		content.setText(data.content);
		
	}
	
}

