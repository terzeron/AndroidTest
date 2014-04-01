package net.terzeron.statusnotificationtest1;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final int HELLO_ID = 1;
	//private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonOnClickHandler());
    }
    
    class ButtonOnClickHandler implements OnClickListener {
    	public void onClick(View v) {
    		String ns = Context.NOTIFICATION_SERVICE;
    		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
    		
    		int icon = R.drawable.ic_launcher;
    		CharSequence tickerText = "First notificaiton";
    		long when = System.currentTimeMillis();
    		Notification.Builder builder = new Notification.Builder(MainActivity.this);
    		
    		builder.setSmallIcon(icon).setTicker(tickerText).setWhen(when);
    		
    		// API level 16
    		//Notification notification = builder.build();
    		// API level 11
    		@SuppressWarnings("deprecation")
			Notification notification = builder.getNotification();
    		mNotificationManager.notify(HELLO_ID, notification);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
