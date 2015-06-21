package com.example.menu;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.calendar.CalendarFragment;
import com.example.gallery.GalleryFragment;
import com.example.home.HomeFragment;
import com.example.letter.LetterFragment;
import com.example.menu.notice.FragmentOptionNotice;
import com.example.menu.optionalarm.FragmentOptionAlarm;
import com.example.menu.optiondropout.FragmentOptionDropOut;
import com.example.menu.optiontheme.FragmentOptionTheme;
import com.example.menu.profile.FragmentOptionProfile;
import com.example.menu.profile.FragmentOptionProfile.onModifyButtonClick;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements MenuFragment.MenuCallback {
	SlidingMenu sMenu;
	private FragmentTabHost mTabHost;
	ImageView tHome, tGallery, tLetter, tcalendar; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_container);
		
		//tab image
		tHome = new ImageView(MainActivity.this);
		tGallery = new ImageView(MainActivity.this);
		tLetter = new ImageView(MainActivity.this);
		tcalendar = new ImageView(MainActivity.this);
		
		tHome.setImageResource(R.drawable.tab_home_selector);
		tGallery.setImageResource(R.drawable.tab_gallery_selector);
		tLetter.setImageResource(R.drawable.tab_letter_selector);
		tcalendar.setImageResource(R.drawable.tab_calendar_selector);
		
		//tabhost
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(tHome),
				HomeFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("gallery").setIndicator(tGallery),
				GalleryFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("letter").setIndicator(tLetter),
				LetterFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("calendar").setIndicator(tcalendar),
				CalendarFragment.class, null);
		
		getSupportFragmentManager().beginTransaction().add(R.id.menucontainer, new MenuFragment()).commit();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
		
		sMenu = getSlidingMenu();
		sMenu.setMode(SlidingMenu.RIGHT);
		sMenu.setBehindOffset(200);
		sMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sMenu.setShadowWidth(20);
		sMenu.setShadowDrawable(R.drawable.shadow);
		sMenu.setFadeDegree(0.5f);
		setSlidingActionBarEnabled(false);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			toggle();
			return true;
		}
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void selectMenu(int menuId) {
		switch (menuId) {
		case MenuFragment.MENU_PROFILE:
			FragmentOptionProfile profileDial = new FragmentOptionProfile();
			profileDial.show(getSupportFragmentManager(), "profile");
			profileDial.setOnModifyButtonClick(new onModifyButtonClick() {
				
				@Override
				public void onModifyButton(String message) {
					if(message.equals("success"))
					{}
					
				}
			});
			break;
		case MenuFragment.MENU_THEME:
			FragmentOptionTheme themeDial = new FragmentOptionTheme();
			themeDial.show(getSupportFragmentManager(), "theme");
			break;
		case MenuFragment.MENU_ALRAM:
			FragmentOptionAlarm alramDial = new FragmentOptionAlarm();
			alramDial.show(getSupportFragmentManager(), "alarm");
			break;
		case MenuFragment.MENU_NOTICE:
			FragmentOptionNotice noticeDial = new FragmentOptionNotice();
			noticeDial.show(getSupportFragmentManager(), "notice");
			break;
		case MenuFragment.MENU_DROP_OUT:
			FragmentOptionDropOut dropOutDial = new FragmentOptionDropOut();
			dropOutDial.show(getSupportFragmentManager(), "dropout");
			break;
			
			

		default:
			break;
		}		
		
	}
}
