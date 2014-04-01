package com.example.progressbartest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}