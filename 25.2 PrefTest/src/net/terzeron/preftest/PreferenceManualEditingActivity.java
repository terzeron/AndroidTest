package net.terzeron.preftest;

import com.example.ch25_2.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class PreferenceManualEditingActivity extends Activity {
    private SharedPreferences mPref;
    private EditText mED;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        mED = (EditText)findViewById(R.id.editText1);
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mED.setText(mPref.getString("edittext_preference", "디폴트값"));
    }
    
    public void mOnSave(View view)  {

    	Editor editor = mPref.edit();
    	editor.putString("edittext_preference", mED.getText().toString());
    	
    	editor.commit();
        finish();
    }
}