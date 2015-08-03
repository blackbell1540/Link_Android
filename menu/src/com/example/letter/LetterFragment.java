package com.example.letter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;
import com.example.menu.SharedPreferenceManager;

public class LetterFragment extends Fragment  {
	
	
	//Views
	ListView list;
	LetterAdapter letterAdapter;
	Button buttondelete, buttonAdd;
	DataLetter cardData;
	
	int link_id;
	
	public static final String SELECTED_CARD_NUMBER = "seleted_card";
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_letter, container, false);

        //find views
        list = (ListView)V.findViewById(R.id.listLetter);
        letterAdapter = new LetterAdapter(getActivity());
        link_id = SharedPreferenceManager.getInstance().getLinkId();
        link_id = 1;
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
	 final int myUserId = 1;
	 Log.i("letter_frag","l: "+link_id+" u:" + myUserId);
	 NetworkManager.getInstnace().getLetterList(getActivity(), link_id, new OnResultListener<ResultLetterList>() {
		
		@Override
		public void onSuccess(ResultLetterList result) {
			if(result.success.equals("1"))
			{
				for(DataLetter dataletter : result.result)
				{
					//organize sender
					//int myUserId = SharedPreferenceManager.getInstance().getUserId();
					cardData = new DataLetter();
					if(myUserId == dataletter.sender_id)
					{ cardData.type = DataLetter.SEND_LETTER; }
					else
					{ cardData.type = DataLetter.RECEIVE_LETTER; }
					
					cardData.content = dataletter.content;
					
					list.setAdapter(letterAdapter);
					letterAdapter.add(cardData);
				}
			}else
			{ Toast.makeText(getActivity(), "letter link load fail", Toast.LENGTH_SHORT).show(); }
			
		}
		
		@Override
		public void onFail(int code) {
			// TODO Auto-generated method stub
			
		}
	});
 }
 

}
