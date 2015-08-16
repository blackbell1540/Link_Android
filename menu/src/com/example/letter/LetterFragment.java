package com.example.letter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.letter.LetterAdapter.onAdapterItemClickListener;
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
				DataLetter selecLetter = (DataLetter)letterAdapter.getItem(position);
				int letter_id = selecLetter.letter_id;
				
				//new Activity
				Intent intent = new Intent(getActivity(), ReadLetterActivity.class);
				intent.putExtra(SELECTED_CARD_NUMBER, letter_id);
				Log.i("letter_id",""+ letter_id);
				startActivity(intent);
			}
        });
        
        //delete button click
        letterAdapter.setOnAdapterItemClickListener(new onAdapterItemClickListener() {
			
			@Override
			public void onAdapterItemClick(LetterAdapter adapter, View view,
					DataLetter data) {
				//letter_data
				final int letter_id = data.letter_id;
				
				//alerdialog to confirm delete
				AlertDialog.Builder dial_confirm = new AlertDialog.Builder(getActivity());
				dial_confirm.setMessage("삭제하시겠습니까?").setCancelable(false).setPositiveButton("삭제", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.i("delete button click", "positivebutton click " + letter_id);
						//positive button click - require delete
						NetworkManager.getInstnace().deleteLetter(getActivity(), letter_id, new OnResultListener<ResultDelLetter>() {
							
							@Override
							public void onSuccess(ResultDelLetter result) {
								if(result.success.equals("1"))
								{ Toast.makeText(getActivity(), "삭제되었습니다.", Toast.LENGTH_SHORT).show(); }
								letterAdapter.clear();
								initData();
							}
							
							@Override
							public void onFail(int code) {
								// TODO Auto-generated method stub
								
							}
						});
					}
				}).setNegativeButton("취소", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});
				
				AlertDialog dial =  dial_confirm.create();
				dial.show();
			}
		});
        return V;
        

    }
 
 	@Override
 	public void onStart() {
 		super.onStart();
 		letterAdapter.clear();
 		initData();
 	}
 
 
 	private void initData() {
	 final int myUserId = SharedPreferenceManager.getInstance().getUserId();
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
					cardData.letter_id = dataletter.letter_id;
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
