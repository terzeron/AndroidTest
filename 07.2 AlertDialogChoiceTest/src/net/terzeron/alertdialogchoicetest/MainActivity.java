package net.terzeron.alertdialogchoicetest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	final CharSequence[] items = { "apple", "banana", "orange", "kiwi" };
	boolean[] selections = new boolean[items.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button listbutton = (Button) findViewById(R.id.button1);
		final Builder builder = new Builder(this);
		listbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				builder.setTitle("Select a fruit").setItems(items,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								Toast.makeText(getApplicationContext(),
										items[item], Toast.LENGTH_SHORT).show();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
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
