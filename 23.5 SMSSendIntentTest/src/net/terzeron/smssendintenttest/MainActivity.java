package net.terzeron.smssendintenttest;

import com.nhncorp.smssendintenttest.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText mNumberEditText;
	private EditText mMessageEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNumberEditText = (EditText) findViewById(R.id.number);
		mMessageEditText = (EditText) findViewById(R.id.message);
	}
	
	public void mSendMessage(View v) {
		String number = mNumberEditText.getText().toString();
		String message = mMessageEditText.getText().toString();
		
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("smsto:" + number));
		intent.putExtra("sms_body", message);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
