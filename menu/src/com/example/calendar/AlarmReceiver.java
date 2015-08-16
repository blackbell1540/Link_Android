package com.example.calendar;

import com.example.menu.R;
import com.example.menu.SharedPreferenceManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent i) {
		Log.d("alarm", "alarm ring! ring!");
	       
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mCompatBuilder = new NotificationCompat.Builder(context);
		switch(i.getIntExtra("sound", 0)) {
		case 0:
			if("ON".equals(SharedPreferenceManager.getInstance().getSound())) {
				mCompatBuilder.setDefaults(Notification.DEFAULT_SOUND); 
			}
			else { 
				mCompatBuilder.setDefaults(Notification.DEFAULT_LIGHTS); 
			}
			break;
		case 1:
			if("ON".equals(SharedPreferenceManager.getInstance().getVibration())) {
				mCompatBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
			}
			else { 
				mCompatBuilder.setDefaults(Notification.DEFAULT_LIGHTS); 
			}
			break;
		case 2:
			mCompatBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
			break;
		}
		mCompatBuilder.setSmallIcon(R.drawable.ic_launcher);
		mCompatBuilder.setTicker(i.getStringExtra("ticker"));
		mCompatBuilder.setContentTitle(i.getStringExtra("title"));
		mCompatBuilder.setContentText(i.getStringExtra("text"));
		mCompatBuilder.setAutoCancel(true);
		nm.notify(0, mCompatBuilder.build());
	}

}
