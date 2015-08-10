package com.example.menu.profile;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;
import com.example.menu.UserInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FragmentOptionProfile extends DialogFragment
{

	//views
	TextView textName, textBirth, textPhone;
	ImageView imageProfile, imageSelect;
	EditText editName, editBirth;
	Button btn;
	LinearLayout showProfile, modiProfile, showButton, modiButton;
	File mSavedFile;
	ImageLoader mLoader;
	
	int userId;
	
	public static final int REQUEST_CODE_CROP = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.MyDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//dialog option
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setLayout(360, 615);
		getDialog().getWindow().setGravity(Gravity.CENTER);
		getDialog().getWindow().getAttributes().x = 0;
		getDialog().getWindow().getAttributes().y = 0;
		
		//inflate - show profile
		View view = inflater.inflate(R.layout.fragment_option_profile, container, false);
		
		//find views
		textName = (TextView)view.findViewById(R.id.TextProfileName);
		textBirth = (TextView)view.findViewById(R.id.TextProfileBirth);
		textPhone = (TextView)view.findViewById(R.id.TextProfilePhone);
		imageProfile = (ImageView)view.findViewById(R.id.imageMenuProfile);
		imageSelect = (ImageView)view.findViewById(R.id.imageSelect);
		editName = (EditText)view.findViewById(R.id.editName);
		editBirth = (EditText)view.findViewById(R.id.editBirth);
		showProfile = (LinearLayout)view.findViewById(R.id.ShowProfile);
		modiProfile = (LinearLayout)view.findViewById(R.id.ModiProfile);
		showButton = (LinearLayout)view.findViewById(R.id.ShowButton);
		modiButton = (LinearLayout)view.findViewById(R.id.ModiButton);
		mLoader = ImageLoader.getInstance();
		
		initUserInfo();
		
		//profile image
		modiProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent photoPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				photoPicker.setType("image/*");
				photoPicker.putExtra("crop", "true");
				photoPicker.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
				photoPicker.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
				startActivityForResult(photoPicker, REQUEST_CODE_CROP);
			}
		});
		
		
		//modify button click
		btn = (Button)view.findViewById(R.id.buttonModify);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showProfile.setVisibility(View.GONE);
				modiProfile.setVisibility(View.VISIBLE);
				showButton.setVisibility(View.GONE);
				modiButton.setVisibility(View.VISIBLE);
			}
		});
		
		//ok button click
		btn = (Button)view.findViewById(R.id.buttonCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		//modify cancel button click
		btn = (Button)view.findViewById(R.id.buttonDialCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showProfile.setVisibility(View.VISIBLE);
				modiProfile.setVisibility(View.GONE);
				showButton.setVisibility(View.VISIBLE);
				modiButton.setVisibility(View.GONE);
			}
		});
		
		//modify complete button click
		btn = (Button)view.findViewById(R.id.buttonModiComplete);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateUserProfile();
			}
		});
		
		if(savedInstanceState != null)
		{
			String file = savedInstanceState.getString("filename");
			if(file != null)
			{ mSavedFile = new File(file); }
		}
		
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CODE_CROP && resultCode == Activity.RESULT_OK)
		{
			Bitmap bp = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath());
			imageProfile.setImageBitmap(bp);
			uploadImage();
		}
	}
	
	private Uri getTempUri()
	{
		mSavedFile = new File(Environment.getExternalStorageDirectory(), "temp_" + System.currentTimeMillis()/1000);
		return Uri.fromFile(mSavedFile);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if(mSavedFile != null)
		{ outState.putString("filename", mSavedFile.getAbsolutePath());}
	}
	
	//upload image
	public void uploadImage()
	{
		NetworkManager.getInstnace().upLoadImage(getActivity(), mSavedFile.getAbsolutePath(), new OnResultListener<ImageUpload>() {
			
			@Override
			public void onSuccess(ImageUpload result) {
				if(result.success.equals("1"))
				{ Log.i("iamgeupload", result.message); }
				else
				{ Log.i("iamgeupload", result.message); }
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//modify button listener
	public interface onModifyButtonClick
	{ public void onModifyButton(String message); }
	
	onModifyButtonClick mListener;
	
	public void setOnModifyButtonClick(onModifyButtonClick listener)
	{ mListener = listener; }
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_NO_TITLE, R.drawable.ic_launcher);
		getDialog().setTitle("profile");
	}

	private void initUserInfo(){
		NetworkManager.getInstnace().getUserInfo(getActivity(), userId, new OnResultListener<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo user) {
				if(user.success.equals("1"))
				{
					String userName = user.result.get(0).user_name;
					int userPhoneNumber = user.result.get(0).phone_number;
					
					textName.setText(userName);
					textPhone.setText(""+userPhoneNumber);
					
				}else
				{ Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show(); }
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void updateUserProfile()
	{
		
	}


}
