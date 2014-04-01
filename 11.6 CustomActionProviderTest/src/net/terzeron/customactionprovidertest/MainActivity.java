package net.terzeron.customactionprovidertest;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static class SettingsActionProvider extends ActionProvider {
		private static final Intent si = new Intent(Settings.ACTION_SETTINGS);
		private final Context context;
		
		public SettingsActionProvider(Context context) {
			super(context);
			this.context = context;
		}
		
		public View onCreateActionView() {
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			View view = layoutInflater.inflate(R.layout.action_bar_settings_action_provider, null);
			ImageButton button = (ImageButton) view.findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					context.startActivity(si);
				}
			});
			return view;
		}
		
		public boolean onPerformDefaultAction() {
			context.startActivity(si);
			return true;
		}
	}

}
