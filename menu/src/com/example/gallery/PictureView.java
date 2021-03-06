package com.example.gallery;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureView extends FrameLayout {
	TextView nameView;
	ImageView iconView;
	Picture mData;
	Button delete;
	
	public PictureView(Context context) {
		super(context);
		init();
	}

	public PictureView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.item_gallery_picture, this);
		iconView = (ImageView) findViewById(R.id.image_icon);
		nameView = (TextView) findViewById(R.id.text_name);
		delete = (Button) findViewById(R.id.gallery_remove);
	}
	
	public void setPicture(Picture data) {
		mData = data;
		Bitmap newImage = Bitmap.createScaledBitmap(
        		BitmapFactory.decodeFile(
        				Environment.getExternalStorageDirectory() + data.picpath,
        				new BitmapFactory.Options()),
        		100, 100, false);
		iconView.setImageBitmap(newImage);
		nameView.setText(data.date + "\n" + data.name);
		
	}
	
	public Picture getPicture() {
		return mData;
	}
	
	public int getImageId() {
		return mData.imageId;
	}
	
	public String getText() {
		return mData.name;
	}

}
