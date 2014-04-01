package net.terzeron.progressdialogtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*ProgressDialog dialog =*/ ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);
		
		// 이유를 알 수 없지만 제대로 동작하지 않음
//		ProgressDialog progressDialog;
//		progressDialog = new ProgressDialog(MainActivity.this);
//		//progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//		progressDialog.setMessage("Loading...");
//		progressDialog.setCancelable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
