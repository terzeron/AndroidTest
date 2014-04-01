package net.terzeron.alertdialogradiotest;

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
	final CharSequence[] items = {"apple", "banana", "orange", "kiwi"};
	boolean[] selections = new boolean[items.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Builder builder1 = new Builder(this);
		Button radiobutton = (Button) findViewById(R.id.button1);
		radiobutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				builder1.setTitle("select a fruit").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Toast.makeText(getApplicationContext() , "Selected item is " + items[item], Toast.LENGTH_SHORT).show();
					}
				}).setPositiveButton("Ok", new DialogButtonClickHandler());
				AlertDialog alert = builder1.create();
				alert.show();
			}
		});
	}
	
	class DialogButtonClickHandler implements DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int clicked) {
			Toast.makeText(getApplicationContext(), "item " + clicked + " is selected", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
