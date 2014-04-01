package net.terzeron.longclicktest;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean isRed = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Button 이벤트
		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, R.string.click_message_str, Toast.LENGTH_SHORT).show();
			}
		});
		
		// Long 클릭 이벤트
		button.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				Button b = (Button) findViewById(v.getId());
				if (isRed) {
					isRed = false;
					b.setBackgroundColor(Color.CYAN);
				} else {
					isRed = true;
					b.setBackgroundColor(Color.RED);
				}
				Toast.makeText(MainActivity.this, R.string.longclick_message_str, Toast.LENGTH_SHORT).show();
				return false;
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
