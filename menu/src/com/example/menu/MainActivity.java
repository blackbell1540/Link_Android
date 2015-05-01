package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements MenuFragment.MenuCallback {
	SlidingMenu sMenu;
	private FragmentTabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_container);
		
		//tabhost
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator("home"),
				HomeFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("gallery").setIndicator("gallery"),
				GalleryFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("letter").setIndicator("letter"),
				LetterFragment.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("calendar").setIndicator("calendar"),
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
			Toast.makeText(MainActivity.this, "menu one clicked", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(MainActivity.this, SdfActivity.class);
			startActivity(intent);
			break;
		case MenuFragment.MENU_THEME:
			Toast.makeText(MainActivity.this, "menu two clicked", Toast.LENGTH_LONG).show();
			break;
		case MenuFragment.MENU_ALRAM:
			Toast.makeText(MainActivity.this, "menu three clicked", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}		
		
	}
}
