package com.example.gallery;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BigPictureActivity extends Activity {
	Button x, left, right;
	ImageView image;
	TextView text;
	int position;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_big_picure);
	    
	    x = (Button) findViewById(R.id.bigPicX);
	    x.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	    left = (Button) findViewById(R.id.galleryLeft);
	    left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				position--;
				if (position == -1) {
					position = GalleryFragment.adapter.getCount()-1;
				}
				init();
			}
		});
	    right = (Button) findViewById(R.id.galleryRight);
	    right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				position++;
				position %= GalleryFragment.adapter.getCount();
				init();
			}
		});
	    image = (ImageView) findViewById(R.id.bigimage);
	    text = (TextView) findViewById(R.id.pictureLine);
	    
	    Intent intent = getIntent();
	    position = intent.getIntExtra("position", 0);
	    
	    init();
	}
	
	private void init() {
		Picture p = GalleryFragment.adapter.items.get(position);
		Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + p.picpath);
        
        image.setImageBitmap(bmp);
		text.setText(p.name);
	}

}
