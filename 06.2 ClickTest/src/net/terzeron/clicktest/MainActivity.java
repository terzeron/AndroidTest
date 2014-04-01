package net.terzeron.clicktest;

import net.terzeron.clicktest.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	Toast.makeText(MainActivity.this, R.string.toast1_string, Toast.LENGTH_LONG).show();
    }
    
    public void myNewClickHandler(View v) {
    	Toast.makeText(MainActivity.this, R.string.toast2_string, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
