package com.example.menu.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.menu.R;

public class ViewNoticeContent extends FrameLayout{
	public ViewNoticeContent(Context context) {
		super(context);
		init();
	}
	
	TextView content;
	private void init()
	{
		LayoutInflater.from(getContext()).inflate(R.layout.item_notice_content, this);
		content = (TextView)findViewById(R.id.textNotice);
	}
	
	DataNoticeContent mData;
	
	public void setNoticeContent(DataNoticeContent data)
	{
		mData = data;
		content.setText(mData.content);
	}

}
