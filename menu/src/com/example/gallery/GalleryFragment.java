package com.example.gallery;

import java.io.File;

import com.example.menu.R;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class GalleryFragment extends Fragment  {
	ListView list;
	Button remove;
	static PictureAdapter adapter;
	SQLiteDatabase db;
	GallDatabaseHelper helper;
	
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_gallery, container, false);
        helper = new GallDatabaseHelper(getActivity(), "linkGall.db", null, 1);
        
        adapter = new PictureAdapter(getActivity());
        adapter.setOnButtonClickListener(new PictureAdapter.onButtonClickListener() {
			
			@Override
			public void btnClick(Picture p) {
				File f = new File(Environment.getExternalStorageDirectory() + p.picpath);
				f.delete();
				db = helper.getWritableDatabase();
		        db.delete("LinkGallery", "_id=?", new String[]{""+p.imageId});
		        adapter.delete(p);
			}
		});
        
        list = (ListView) V.findViewById(R.id.picList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("item clicked");
				Intent intent = new Intent(getActivity(), BigPictureActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
        });
        
        /*
        db = helper.getWritableDatabase();
        db.delete("LinkGallery", null, null);
        for (int i = 0; i < 2; i++) {
        	ContentValues values = new ContentValues();
            values.put("date", "2015-06-14 00:00:00");
            values.put("picture", "/repPic.jpg");
            values.put("content", "picture"+i);
            db.insert("LinkGallery", null, values);
        }
       
        
        db = helper.getWritableDatabase();
        db.delete("LinkGallery", null, null);
        */
        
        db = helper.getReadableDatabase();
        Cursor c = db.query("LinkGallery", null, null, null, null, null, null);
        while(c.moveToNext()) {
        	int _id = c.getInt(c.getColumnIndex("_id"));
        	String date = c.getString(c.getColumnIndex("date"));
        	String picture = c.getString(c.getColumnIndex("picture"));
        	String content = c.getString(c.getColumnIndex("content"));
        	Picture p = new Picture(_id, date, picture, content);
        	adapter.add(p);
        }
        
        return V;
    }
}