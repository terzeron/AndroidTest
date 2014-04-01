package net.terzeron.actionprovidertest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
	private ShareActionProvider sap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		sap = (ShareActionProvider) menu.findItem(R.id.action_settings).getActionProvider();
		sap.setShareHistoryFileName("custom_share_history.xml");
		sap.setShareIntent(getDefaultShareIntent());
		
		return true;
	}

	private Intent getDefaultShareIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
		intent.putExtra(Intent.EXTRA_TEXT, "Extra Text");
		return intent;
	}
	
}
