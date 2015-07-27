package com.example.menu.notice;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;

public class FragmentOptionNotice extends DialogFragment {

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
		
		
		initData();
		
		return view;
	}
	
	DataNoticeTitle tData;
	DataNoticeContent cData;
	private void initData()
	{
		NetworkManager.getInstnace().getNotice(getActivity(), new OnResultListener<ResultNotice>() {

			@Override
			public void onSuccess(ResultNotice result) {
				// TODO Auto-generated method stub
				if(result.success.equals("1"))
				{
					expandableNotice.setAdapter(noticeAdapter);
					for(DataNotice notice : result.result)
					{
						noticeAdapter.add(notice.title, notice.content);
					}
				}else
				{ Toast.makeText(getActivity(), "notice network fail", Toast.LENGTH_SHORT).show(); }
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
			

		});
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("notice");
	}
}
