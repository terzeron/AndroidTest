package net.terzeron.customtoastmessagetest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new ButtonOnClickHandler());
		
		Button custom_button = (Button) findViewById(R.id.custombutton);
		custom_button.setOnClickListener(new CustomButtonOnClickHandler());
	}
	
	class ButtonOnClickHandler implements OnClickListener {
		public void onClick(View v) {
			Context context = getApplicationContext();
			CharSequence text = "Hello toast!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast =  Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
			toast.show();
		}
	}
	
	class CustomButtonOnClickHandler implements OnClickListener {
		public void onClick(View v) {
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast_layout, 
					(ViewGroup) findViewById(R.id.toast_layout_root));
			
			// 이미지와 텍스트가 포함된 레이아웃이 토스트에서 사용됨
			ImageView image = (ImageView) layout.findViewById(R.id.image);
			image.setImageResource(R.drawable.ic_launcher);
			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("Hello! This is custom toast message");
			
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
