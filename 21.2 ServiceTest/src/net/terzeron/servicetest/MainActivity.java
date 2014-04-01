package net.terzeron.servicetest;

import com.example.net.terzeron.servicetest.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void btn_onclick(View v) {
		if (v.getId() == R.id.btn_start) {
			startService(new Intent(MainActivity.this, Mp3Service.class));
		} else if (v.getId() == R.id.btn_stop) {
			stopService(new Intent(MainActivity.this, Mp3Service.class));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
