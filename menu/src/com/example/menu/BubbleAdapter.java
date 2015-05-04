package com.example.menu;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BubbleAdapter extends BaseAdapter{

	//item array list
	ArrayList<Bubble> items = new ArrayList<Bubble>();
	Context mContext;
	
	//type
	public static final int RECEIVE_BUBBLE = 0;
	public static final int SEND_BUBBLE = 1;
	public static final int TYPE_COUNT = 2;
	
	public BubbleAdapter(Context mContext) {
		this.mContext = mContext;
	}
	
	public void add(Bubble item)
	{
		items.add(item);
		notifyDataSetChanged();
	}
	
	public int getViewTypeCount()
	{ return TYPE_COUNT; }
	
	public int getItemViewType(int position)
	{
		switch (items.get(position).type) {
		case Bubble.RECEIVE_BUBBLE:
			return RECEIVE_BUBBLE;

		case Bubble.SEND_BUBBLE:
			return SEND_BUBBLE;
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
		case RECEIVE_BUBBLE:
		{
			ReceiveBubbleView Rview;
			if(convertView != null && convertView instanceof ReceiveBubbleView)
			{ Rview = (ReceiveBubbleView)convertView; }
			else
			{ Rview = new ReceiveBubbleView(mContext); }
			Rview.setBubble(items.get(position));
			return Rview;
		}
		case SEND_BUBBLE:
		{
			SendBubbleView Sview;
			if(convertView != null && convertView instanceof SendBubbleView)
			{ Sview = (SendBubbleView)convertView; }
			else
			{ Sview = new SendBubbleView(mContext); }
			Sview.setBubble(items.get(position));
			return Sview;
		}
		default:
		{
			ReceiveBubbleView Rview;
			if(convertView != null && convertView instanceof ReceiveBubbleView)
			{ Rview = (ReceiveBubbleView)convertView; }
			else
			{ Rview = new ReceiveBubbleView(mContext); }
			Rview.setBubble(items.get(position));
			return Rview;
		}
		}
	}

}
