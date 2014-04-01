package net.terzeron.broadcastreceivertest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	public static String USER_DEFINED_MSG = "net.terzeron.android.msg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_send = (Button) findViewById(R.id.it_send);
		btn_send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent serviceIntent = new Intent();
				serviceIntent.setAction(USER_DEFINED_MSG);
				serviceIntent.putExtra("data", "user defined msg");
				sendBroadcast(serviceIntent);
			}
		});
		
		Button btn_register = (Button) findViewById(R.id.it_register);
		btn_register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				MyBroadcastReceiver receiver = new MyBroadcastReceiver();
				IntentFilter filter = new IntentFilter();
				filter.addAction(USER_DEFINED_MSG);
				registerReceiver(receiver, filter);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
