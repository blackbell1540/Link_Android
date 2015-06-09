package com.example.letter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class LetterAdapter extends BaseAdapter{

	//item array list
	ArrayList<LetterCardData> items = new ArrayList<LetterCardData>();
	Context mContext;
	
	//type
	public static final int RECEIVE_LETTER = 0;
	public static final int SEND_LETTER = 1;
	public static final int TYPE_COUNT = 2;
	
	public LetterAdapter(Context mContext) {
		this.mContext = mContext;
	}
	
	public void add(LetterCardData item)
	{
		items.add(item);
		notifyDataSetChanged();
	}
	
	public int getViewTypeCount()
	{ return TYPE_COUNT; }
	
	public int getItemViewType(int position)
	{
		switch (items.get(position).type) {
		case LetterCardData.RECEIVE_LETTER:
			return RECEIVE_LETTER;

		case LetterCardData.SEND_LETTER:
			return SEND_LETTER;
		default:
			return TYPE_COUNT;
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		switch (getItemViewType(position)) {
		case RECEIVE_LETTER:
		{
			ReceiveLetterCardView Rview;
			if(convertView != null && convertView instanceof ReceiveLetterCardView)
			{ Rview = (ReceiveLetterCardView)convertView; }
			else
			{ Rview = new ReceiveLetterCardView(mContext); }
			Rview.setDataRLetterCard(items.get(position));
			return Rview;
		}
		case SEND_LETTER:
		{
			SendLetterCardView Sview;
			if(convertView != null && convertView instanceof SendLetterCardView)
			{ Sview = (SendLetterCardView)convertView; }
			else
			{ Sview = new SendLetterCardView(mContext); }
			Sview.setDataSLetterCard(items.get(position));
			return Sview;
		}
		default:
		{
			ReceiveLetterCardView Rview;
			if(convertView != null && convertView instanceof ReceiveLetterCardView)
			{ Rview = (ReceiveLetterCardView)convertView; }
			else
			{ Rview = new ReceiveLetterCardView(mContext); }
			Rview.setDataRLetterCard(items.get(position));
			return Rview;
		}
		}
	}

}
