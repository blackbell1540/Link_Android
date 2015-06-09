package com.example.menu.manager;

import android.content.Context;
import android.content.SharedPreferences;

//single tone
public class SharedPreferenceManager {
	private static SharedPreferenceManager instance;
	public static SharedPreferenceManager getInstance()
	{
		if(instance == null)
		{ instance = new SharedPreferenceManager(); }
		return instance;
	}
	
	//shared preference
	private static final String PREF_USER = "user";
	SharedPreferences mUser;
	SharedPreferences.Editor mEditor;
	
	private SharedPreferenceManager()
	{
		mUser = LinkApplication.getContext().getSharedPreferences(PREF_USER, Context.MODE_PRIVATE);
		mEditor = mUser.edit();
	}
	
	//IsSignUp
	private static final String IS_SIGN_UP = "isSignUp";
	private String mSginUp;
	
	public String getIsSignUp()
	{
		if(mSginUp == null)
		{ mSginUp = mUser.getString(IS_SIGN_UP, "N"); }
		return mSginUp;
	}
	
	public void setIsSignUp(String sign_up)
	{
		mSginUp = sign_up;
		mEditor.putString(IS_SIGN_UP, sign_up);
		mEditor.commit();
	}
	
	//IsLinked
	private static final String IS_LINKED = "isLinked";
	private String mLinked;
	
	public String getIsLinked()
	{
		if(mLinked == null)
		{ mLinked = mUser.getString(IS_LINKED, "N"); }
		return mLinked;
	}
	
	public void setIsLinked(String linked)
	{
		mLinked = linked;
		mEditor.putString(IS_LINKED, "N");
		mEditor.commit();
	}
	
}
