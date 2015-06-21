package com.example.menu;

import android.content.Context;
import android.content.SharedPreferences;

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
	
	//sound option
	private static final String SOUND_ON = "sound_on";
	private String mSound;
	
	public String getSound()
	{
		if(mSound == null)
		{ mSound = mUser.getString(SOUND_ON, "N"); }
		return mSound;
	}
	
	public void setSound(String sound)
	{
		mSound = sound;
		mEditor.putString(SOUND_ON, "N");
		mEditor.commit();
	}
	
	//vibration option
	private static final String VIBRATION_ON = "vibration_on";
	private String mVibration;
	
	public String getVibration()
	{
		if(mVibration == null)
		{ mVibration = mUser.getString(VIBRATION_ON, "ON"); }
		return mVibration;
	}
	
	public void setVibration(String vibration)
	{
		mVibration = vibration;
		mEditor.putString(VIBRATION_ON, "ON");
		mEditor.commit();
	}
	
}
