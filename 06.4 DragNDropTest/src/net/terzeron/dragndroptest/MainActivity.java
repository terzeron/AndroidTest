package net.terzeron.dragndroptest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener {

	private final int START_DRAG = 0;
	private final int END_DRAG = 1;
	private int isMoving;
	private float offset_x, offset_y;
	private boolean start_yn = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) this.findViewById(R.id.button1);
		button.setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (start_yn) {
				offset_x = event.getRawX();
				offset_y = event.getRawY();
				start_yn = false;
			}
			Toast.makeText(MainActivity.this,  "Drag Start", Toast.LENGTH_SHORT).show();
			isMoving = START_DRAG;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			Toast.makeText(MainActivity.this, "Drag End", Toast.LENGTH_SHORT).show();
			isMoving = END_DRAG;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (isMoving == START_DRAG) {
				v.setX((int) event.getRawX() - offset_x);
				v.setY((int) event.getRawY() - offset_y);
			}
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
