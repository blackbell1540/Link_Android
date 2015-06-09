package com.example.menu.optionnotice;

import com.example.menu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ViewNoticeContent extends FrameLayout{

	public ViewNoticeContent(Context context) {
		super(context);
		init();
	}
	
	TextView content;
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_notice, this);
		content = (TextView)findViewById(R.id.textNotice);
	}
	
	DataNoticeContent mData;
	
	public void setNoticeContent(DataNoticeContent data)
	{
		mData = data;
		content.setText(mData.content);
	}

}
