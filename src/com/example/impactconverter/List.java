package com.example.impactconverter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//List fragment used as an extra layout for navigation purposes only
public class List extends ListFragment {
	String[] itemList;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//View is inflated into the container
		View lv = inflater.inflate(R.layout.list_activity, container, false);
		
		itemList = getResources().getStringArray(R.array.list);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, itemList));

		return lv;
	}

}
