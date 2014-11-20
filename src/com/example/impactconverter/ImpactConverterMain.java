package com.example.impactconverter;

/**
 * Stephen Murray
 * R00104832
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImpactConverterMain extends Activity {

	private static EditText et;
	private boolean checked = false;
	private static TextView t;

	//onCreate is called when the application is started or an activity is being recreated
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_impact_converter_main);
		
		//Get any saved values when the activity was destroyed
		if (savedInstanceState != null) {
			checked = savedInstanceState.getBoolean("save");
			buttonClicked();
		}

		//Get text int id for the shared preferences text view and button
		et = (EditText) findViewById(R.id.sp_test_ET);
		Button b = (Button) findViewById(R.id.btn_continue);

		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				//Get the string in the EditText view and save it in a local variable
				et = (EditText) findViewById(R.id.sp_test_ET);
				String passText = et.getText().toString();

				//Start a new intent to launch the ConverterFragment class
				Intent intent = new Intent(ImpactConverterMain.this,
						ConverterFragment.class);
				//Put the contents of the EditText in the intent extras
				intent.putExtra("stringPass", passText);
				startActivity(intent);
			}
		});
		
		//Used to save a local varialbe, when the button is clicked the value is changed
		//and text is displayed on the screen
		t = (TextView)findViewById(R.id.var);
		Button button = (Button)findViewById(R.id.check);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checked = true;
				buttonClicked();
			}
		});
		
		buttonClicked();

		// Call the loadPrefs() to load any text that was save when onDestroy() was called
		loadPrefs();
		
		if (getIntent().getExtras() != null) {
			String messagePassed = (getIntent().getExtras()
					.getString("stringReturned"));
			TextView et = (TextView) findViewById(R.id.textView1);
			et.setText(messagePassed);
			et.setTextColor(Color.parseColor("#9061FF"));
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean("save", checked);
	}
	
	private void buttonClicked(){
		if(checked == true){
			t.setText("The button was clicked");
		}
	}
	
	//The savePrefs methods is called whenever the onPause method is called
	@Override
	protected void onPause() {
		super.onPause();
		savePrefs();
	}

	//Method to save the preferences 
	private void savePrefs() {
		et = (EditText) findViewById(R.id.sp_test_ET);
		String saveText = et.getText().toString();

		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor edit = sharedPrefs.edit();
		edit.putString("key_ic", saveText);
		//commit to saving the key value pair
		edit.commit();
	}

	//Method to load the preferences 
	private void loadPrefs() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String nameSaved = sharedPrefs.getString("key_ic", "").toString();

		//If the length of the local variable is not greater than 0 set the text to empty 
		//if it contains text set the value to that variable
		if (nameSaved.length() > 0)
			et.setText(nameSaved);
		else
			et.setText("");
	}

}
