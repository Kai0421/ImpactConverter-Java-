package com.example.impactconverter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImpactConverterFrag extends Fragment {

	private double feet;
	private EditText et;
	private static String message = null;
	
	//onCreateView is used instead of onCreate as the layout is being used as a fragment activity
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		//The layout is inflated into the fragment
		View v = inflater.inflate(R.layout.converter_activity, container, false);
		
		//Check to see if and extras were added when the activity was created 
		//We have to use getActivity().getIntent()as you cannot use getIntent alone from
		//within a fragment
		if (getActivity().getIntent().getExtras() != null) {
			String messagePassed = (getActivity().getIntent().getExtras().getString("stringPass"));
			TextView tv = (TextView) v.findViewById(R.id.tv_two);
			
			//If data is passed a text view is assigned it's value and the colour is changed
			tv.setText(messagePassed);
			tv.setTextColor(Color.parseColor("#9061FF"));
		}
		
		//Get the int id of the convert button 
		Button bConvert = (Button) v.findViewById(R.id.createString);
		bConvert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Get the id of the EditText view
				et = (EditText)getView().findViewById(R.id.numEnter);
				
				String sNum =  null;
				
				
				//If the user enters a value for mph that value is retrieved from the EditText view
				//and parsed to a double and assigned to a static variable
				if(!(et.getText().toString().isEmpty()) ){
					sNum = et.getText().toString();
					double dNum = Double.parseDouble(sNum);
					
					//Call the method that will produce the string that will be displayed on the 
					//main screen
					message = calculateFreeFall(dNum);
				}
			}
		});
		
		Button b = (Button) v.findViewById(R.id.returnBtn_tv);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//Intent to launch back to the first screen
				Intent intent = new Intent(getActivity(),
						ImpactConverterMain.class);
				//If the EditText view was not empty put the value as an extra in the intent
				if(message != null)
					intent.putExtra("stringReturned", message);
				Log.d("debug", "start activity intent");
				startActivity(intent);
				
				//reset the value of message
				message = null;
			}
		});

		return v;
	}

	private String calculateFreeFall(double valueEntered) {

		//The value of the impact increases by 0.32 approimately for every 10 mps
		//An array of 15 elements covers the speed range of 30-100mph
		//The values are more or less accurate
		double[] speedBands = { 1, 1.16, 1.32, 1.48, 1.64, 1.8, 1.96, 2.12,
				2.28, 2.44, 2.6, 2.76, 2.92, 3.08, 3.24 };
		double feetToMeters = 3.28;
		double meters;
		
		if(valueEntered <= 30)
			feet = (valueEntered * speedBands[0]);
		else if(valueEntered <= 35)
			feet = (valueEntered * speedBands[1]);
		else if(valueEntered <= 40)
			feet = (valueEntered * speedBands[2]);
		else if(valueEntered <= 45)
			feet = (valueEntered * speedBands[3]);
		else if(valueEntered <= 50)
			feet = (valueEntered * speedBands[4]);
		else if(valueEntered <= 55)
			feet = (valueEntered * speedBands[5]);
		else if(valueEntered <= 60)
			feet = (valueEntered * speedBands[6]);
		else if(valueEntered <= 65)
			feet = (valueEntered * speedBands[7]);
		else if(valueEntered <= 70)
			feet = (valueEntered * speedBands[8]);
		else if(valueEntered <= 80)
			feet = (valueEntered * speedBands[9]);
		else if(valueEntered <= 85)
			feet = (valueEntered * speedBands[10]);
		else if(valueEntered <= 90)
			feet = (valueEntered * speedBands[11]);
		else if(valueEntered <= 95)
			feet = (valueEntered * speedBands[12]);
		else if(valueEntered <= 100)
			feet = (valueEntered * speedBands[13]);
		else if(valueEntered > 100)
			feet = (valueEntered * speedBands[14]);
		
		meters = (feet / feetToMeters);
		meters = Math.ceil(meters);

		//The message that will be displayed on the first screen
		String message = ("Crashing a car at " + valueEntered + "mph is equivalent to free " +
				"falling " + (int)feet + "ft (" + (int)meters + "m)");

		return message;
	}

}
