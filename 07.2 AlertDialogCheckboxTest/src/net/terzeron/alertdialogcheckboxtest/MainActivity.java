package net.terzeron.alertdialogcheckboxtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.util.Log;
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

		final Builder builder2 = new Builder(this);
		Button checkboxbutton = (Button) findViewById(R.id.button1);
		checkboxbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				builder2.setTitle("Select fruits")
						.setMultiChoiceItems(items, selections,
								new DialogSelectionClickHandler())
						.setPositiveButton("Ok", new DialogButtonClickHandler());
				AlertDialog alert = builder2.create();
				alert.show();
			}
		});
	}

	class DialogSelectionClickHandler implements
			DialogInterface.OnMultiChoiceClickListener {
		public void onClick(DialogInterface dialog, int item, boolean checked) {
			selections[item] = checked;
			Log.i("fruit", items[item] + " selections: " + checked);
		}
	}

	class DialogButtonClickHandler implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int clicked) {
			switch (clicked) {
			case DialogInterface.BUTTON_POSITIVE:
				printSelectedFruit();
				break;
			}
		}
	}
	
	protected void printSelectedFruit() {
		CharSequence retValue = "";
		for (int i = 0; i < items.length; i++) {
			retValue = retValue + (items[i] + " selected: " + selections[i] + "\n");
			Log.i("fruit", items[i] + " selected: " + selections[i]);
		}
		Toast.makeText(getApplicationContext(), retValue, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
