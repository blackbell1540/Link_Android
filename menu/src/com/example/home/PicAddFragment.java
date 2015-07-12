package com.example.home;



import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

import com.example.gallery.GallDatabaseHelper;
import com.example.gallery.Picture;
import com.example.menu.R;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PicAddFragment extends DialogFragment{
	String name, picture, date;
	ImageView image;
	EditText text;
	Button cancle, ok;
	
	SQLiteDatabase db;
	GallDatabaseHelper helper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_picture_add, container,
				false);
		getDialog().setTitle("picture save");	
		
		name = getArguments().getString("name");
		
		helper = new GallDatabaseHelper(getActivity(), "linkGall.db", null, 1);
		db = helper.getReadableDatabase();
        Cursor c = db.query("LinkGallery", null, "picture=?", new String[]{name}, null, null, null);
        
        if (c.getCount() > 0) {
        	name = name + c.getCount();
        }
		
		
		picture = getArguments().getString("picture");
		date = getArguments().getString("date");
		
		image = (ImageView) v.findViewById(R.id.picture_add_image);
		text = (EditText) v.findViewById(R.id.picture_add_text);
		cancle = (Button) v.findViewById(R.id.picture_add_cancle);
		ok = (Button) v.findViewById(R.id.picture_add_ok);
		
		cancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		/* set image
		 * decode string to bitmap
		 * save bitmap
		 * crop image
		 * set image to view
		 */
		InputStream stream = new ByteArrayInputStream(Base64.decode(picture, Base64.DEFAULT));
		Bitmap bmp = BitmapFactory.decodeStream(stream);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(
					Environment.getExternalStorageDirectory() + name);
			bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
	        bmp = Bitmap.createScaledBitmap(
	        		BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + name,
	        				new BitmapFactory.Options()),
	        		100, 100, false);
	        image.setImageBitmap(bmp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		/* ok button click listener
		 * get text content
		 * open db
		 * save text and picture
		 */
		
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
	            values.put("date", date);
	            values.put("picture", name);
	            values.put("content", text.getText().toString());
	            db.insert("LinkGallery", null, values);
	            dismiss();
			}
		});
		
		return v;
	}
	
}
