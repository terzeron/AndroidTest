package net.terzeron.actionbartest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextView resultView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultView = (TextView) findViewById(R.id.optMenuRSView);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new ButtonClickHandler());
	}
	
    public class ButtonClickHandler implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		resultView.setText("hello");
    	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit:
			resultView.setText("edit");
			return true;

		case R.id.exit:
			resultView.setText("exit");
			return true;
			
		case android.R.id.home:
			// 다른 액티비티에서 홈이 되는 메인액티비티로 이동하려고 할 때 
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
