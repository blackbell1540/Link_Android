package com.example.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TextImageView extends FrameLayout {
	TextView nameView;
	ImageView iconView;
	TextImageData mData;
	
	public TextImageView(Context context) {
		super(context);
		init();
	}

	public TextImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public interface OnImageClickListener {
		public void onImageClick(View v, TextImageData d);
	}
	OnImageClickListener mImageClickListener;
	public void setOnImageClickListener(OnImageClickListener listener) {
		mImageClickListener = listener;
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.customview_gallery, this);
		iconView = (ImageView) findViewById(R.id.image_icon);
		nameView = (TextView) findViewById(R.id.text_name);
		
		iconView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mImageClickListener != null) {
					mImageClickListener.onImageClick(TextImageView.this, mData);
				}
				
			}
		});
	}
	
	public void setTextImage(TextImageData data) {
		mData = data;
		iconView.setImageResource(data.imageId);
		nameView.setText(data.name);
		
	}
	
	public TextImageData getTextImage() {
		return mData;
	}
	
	public Drawable getImage() {
		return iconView.getDrawable();
	}
	
	public String getText() {
		return nameView.getText().toString();
	}

}
