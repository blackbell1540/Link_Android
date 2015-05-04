package com.example.menu;

import com.example.menu.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureView extends FrameLayout {
	TextView nameView;
	ImageView iconView;
	Picture mData;
	
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
	}
	
	public void setPicture(Picture data) {
		mData = data;
		iconView.setImageResource(data.imageId);
		nameView.setText(data.name);
		
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
