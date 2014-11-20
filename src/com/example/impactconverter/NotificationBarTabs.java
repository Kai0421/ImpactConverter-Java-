package com.example.impactconverter;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class NotificationBarTabs extends FragmentPagerAdapter implements
		TabListener, OnPageChangeListener {
	
	private final Context context;
	private final ActionBar actionBar;
	private final ViewPager viewPager;
	private final ArrayList<TabInfo> tabs = new ArrayList<TabInfo>();

	static final class TabInfo {
		private final Class<?> clss;
		private final Bundle args;

		TabInfo(Class<?> _class, Bundle _args) {
			clss = _class;
			args = _args;
		}
	}

	//Gets the context of the activity and sets the viewPager to listen for changes (activity)
	public NotificationBarTabs(FragmentActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		context = activity;
		actionBar = activity.getActionBar();
		viewPager = pager;
		viewPager.setAdapter(this);
		viewPager.setOnPageChangeListener(this);
	}

	//Adds a to the class list
	public void addTab(Tab tab, Class<?> clss, Bundle args) {
		TabInfo info = new TabInfo(clss, args);
		tab.setTag(info);
		tab.setTabListener(this);
		tabs.add(info);
		actionBar.addTab(tab);
		notifyDataSetChanged();
	}

	//The action bar tab is highlighted depending on the current selected position
	@Override
	public void onPageSelected(int position) {
		actionBar.setSelectedNavigationItem(position);
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		Object tag = tab.getTag();
		for (int x = 0; x < tabs.size(); x++) {
			if (tabs.get(x) == tag) {
				viewPager.setCurrentItem(x);
			}
		}
	}
	
	//Gets the positon of the tab clicked in an array and sets the view to that class
	@Override
	public Fragment getItem(int position) {
		TabInfo info = tabs.get(position);
		return Fragment.instantiate(context, info.clss.getName(), info.args);
	}
	
	//Returns the amount of tabs in the action bar
	@Override
	public int getCount() {
		return tabs.size();
	}
	
	// Unused methods implemented from tab listener interface
	@Override
	public void onPageScrollStateChanged(int state) {}

	@Override
	public void onPageScrolled(int position, float positionOffset,int positionOffsetPixels){}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {}

}