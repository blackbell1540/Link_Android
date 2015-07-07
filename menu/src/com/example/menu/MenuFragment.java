package com.example.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment{
	//view
	ListView list;
	ArrayAdapter<String> mAdapter;
	
	//menu number
	public static final int MENU_INVALID = -1;
	public static final int MENU_PROFILE = 0;
	public static final int MENU_THEME = 1;
	public static final int MENU_ALRAM = 2;
	public static final int MENU_NOTICE = 3;
	public static final int MENU_DROP_OUT = 4;
	
	
	//menu callbacks
	public interface MenuCallback {
		public void selectMenu(int menuId);
	}
	
	MenuCallback callback;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof MenuCallback) {
			callback = (MenuCallback)activity;
		} else {
			// Throws...
		}
	}	
	
	//createview
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sliding_menu, container, false);
		
		//find views
		list = (ListView)view.findViewById(R.id.listSlidingMenu);
		mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		list.setAdapter(mAdapter);
		
		//menu add
		mAdapter.add("내 프로필");
		mAdapter.add("테마 수정");
		mAdapter.add("알람 수정");
		mAdapter.add("공지/약관");
		mAdapter.add("탈퇴");
		
		//list click
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(callback != null)
				{ 
					int menuId = getMenuId(position);
					if(menuId != MENU_INVALID)
					{ callback.selectMenu(menuId); }
				}
				
			}
		});
		return view;
	}
	
	public int getMenuId(int position)
	{
		switch (position) {
		case 0:
			return MENU_PROFILE;
		case 1:
			return MENU_THEME;
		case 2:
			return MENU_ALRAM;
		case 3:
			return MENU_NOTICE;
		case 4:
			return MENU_DROP_OUT;
		}
		return MENU_INVALID;
	}
	
}
