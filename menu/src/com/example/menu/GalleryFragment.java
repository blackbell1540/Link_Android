package com.example.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class GalleryFragment extends Fragment  {
	LinearLayout list;
	Button plus;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_gallery, container, false);
        
        list = (LinearLayout) V.findViewById(R.id.picList);
        plus = (Button) V.findViewById(R.id.plusPicButton);
        
        /* To-do : use db (load picture)
         * 			TextImageView.OnImageClick -> show big picture
         */
        
        plus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextImageView mv = new TextImageView(getActivity());
		        mv.setTextImage(new TextImageData(R.drawable.ic_launcher, "mv"));
		        list.addView(mv);
			}
		});

        return V;
    }
}