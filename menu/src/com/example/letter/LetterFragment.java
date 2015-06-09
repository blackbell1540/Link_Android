package com.example.letter;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

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

public class LetterFragment extends Fragment  {
	
	
	//Views
	ListView list;
	LetterAdapter letterAdapter;
	Button buttondelete, buttonAdd;
	LetterCardData cardData;
	
	public static final String SELECTED_CARD_NUMBER = "seleted_card";
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_letter, container, false);

        //find views
        list = (ListView)V.findViewById(R.id.listLetter);
        letterAdapter = new LetterAdapter(getActivity());
        list.setAdapter(letterAdapter);
  
        initData();
        
        //delete button click
        
        //add button click
        buttonAdd = (Button)V.findViewById(R.id.buttonAddLetter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), WriteLetterActivity.class);
				startActivity(intent);
				
			}
		});
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//throw data
				int pos = position;
				
				//new Activity
				Intent intent = new Intent(getActivity(), ReadLetterActivity.class);
				intent.putExtra(SELECTED_CARD_NUMBER, pos);
				startActivity(intent);
			}
        });
        
        return V;
        

    }
private void initData() {
		
	for(int i = 0 ; i<5; i++)
	{
		LetterCardData data = new LetterCardData();
		data.letterContent = "receive content";
		data.type = LetterCardData.RECEIVE_LETTER;
		letterAdapter.add(data);

		LetterCardData _data = new LetterCardData();
		_data.letterContent = "send content";
		_data.type = LetterCardData.SEND_LETTER;
		letterAdapter.add(_data);
	}
	
}
 

}
