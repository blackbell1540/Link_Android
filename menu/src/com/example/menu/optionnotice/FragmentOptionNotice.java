package com.example.menu.optionnotice;

import com.example.menu.R;
import com.example.menu.R.drawable;
import com.example.menu.R.layout;
import com.example.menu.R.style;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;

public class FragmentOptionNotice extends DialogFragment{

	ExpandableListView expandableNotice;
	NoticeAdapter noticeAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.MyDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//dialog options
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate
		View view = inflater.inflate(R.layout.fragment_option_notice, container, false);
		
		//find views
		expandableNotice = (ExpandableListView)view.findViewById(R.id.expandableNotice);
		noticeAdapter = new NoticeAdapter(getActivity());
		expandableNotice.setAdapter(noticeAdapter);
		
		initData();
		
		return view;
	}
	
	DataNoticeTitle tData;
	DataNoticeContent cData;
	private void initData()
	{
		for(int i=0; i<10; i++)
		{
			String title = "title" + i;
			String content = "content" + i;
			noticeAdapter.add(title, content);
		}
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("notice");
	}
}
