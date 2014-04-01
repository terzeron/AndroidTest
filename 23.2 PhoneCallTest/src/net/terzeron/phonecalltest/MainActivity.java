package net.terzeron.phonecalltest;

import com.nhncorp.phonecalltest.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText mPhoneNumberEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mPhoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);
	}
	
	public void mCallDialer(View v) {
		Uri number = Uri.parse("tel:" + mPhoneNumberEditText.getText());
		Intent intent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(intent);
	}

	public void mCallDirect(View v) {
		Uri number = Uri.parse("tel:" + mPhoneNumberEditText.getText());
		Intent intent = new Intent(Intent.ACTION_CALL, number);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
