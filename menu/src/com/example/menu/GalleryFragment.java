package com.example.menu;

import com.example.menu.R;
import com.example.menu.LetterCardData;
import com.example.menu.WriteLetterActivity;

import android.content.Intent;
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
	Button plus;
	static PictureAdapter adapter;
	
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
        
        plus = (Button) V.findViewById(R.id.plusPicButton);
        plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Picture data = new Picture(R.drawable.ic_launcher,"mv");
				adapter.add(data);
			}
		});
        
        /* To-do : use db (load picture)
         */
        return V;
    }
}