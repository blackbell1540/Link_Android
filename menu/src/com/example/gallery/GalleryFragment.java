package com.example.gallery;

import com.example.letter.LetterCardData;
import com.example.letter.WriteLetterActivity;
import com.example.menu.R;
import com.example.menu.R.drawable;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
        
        list = (ListView) V.findViewById(R.id.picList);
        adapter = new PictureAdapter(getActivity());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), BigPictureActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
        });
        
        helper = new GallDatabaseHelper(getActivity(), "linkGall.db", null, 1);
        db = helper.getReadableDatabase();
        Cursor c = db.query("LinkGallery", null, null, null, null, null, null);
        while(c.moveToNext()) {
        	int _id = c.getInt(c.getColumnIndex("_id"));
        	String date = c.getString(c.getColumnIndex("date"));
        	String picture = c.getString(c.getColumnIndex("picture"));
        	String content = c.getString(c.getColumnIndex("content"));
        	Picture p = new Picture(_id, date, picture, content);
        	adapter.items.add(p);
        	adapter.notifyDataSetChanged();
        }
        
        return V;
    }
}