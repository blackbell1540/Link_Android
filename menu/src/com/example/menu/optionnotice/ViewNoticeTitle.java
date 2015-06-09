package com.example.menu.optionnotice;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ViewNoticeTitle extends FrameLayout{

	public ViewNoticeTitle(Context context) {
		super(context);
		init();
	}

	TextView title;
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_notice, this);
		title = (TextView)findViewById(R.id.textNotice);
	}
	
	DataNoticeTitle mData;
	public void setNoticTitle(DataNoticeTitle data)
	{
		mData = data;
		title.setText(mData.title);
	}
}
