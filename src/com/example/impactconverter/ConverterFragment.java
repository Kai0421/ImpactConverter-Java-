package com.example.impactconverter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class ConverterFragment extends FragmentActivity {

	private ViewPager vp;
	private NotificationBarTabs nbt;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		vp = new ViewPager(this);
		//Give the viewpager an id so it can be referenced
		vp.setId(R.id.pager);
		setContentView(vp);

		// Get a new final instance of Action bar and set the mode to navigation mode
		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		//Set the notification bars tabs to be of ImpactConverterFrag class and the List class
		nbt = new NotificationBarTabs(this, vp);
		nbt.addTab(bar.newTab().setText("Impact Converter"),ImpactConverterFrag.class, null);
		nbt.addTab(bar.newTab().setText("List"), List.class, null);
		

	}

}
