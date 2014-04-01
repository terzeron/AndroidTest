package net.terzeron.calllogtest;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
		CallLogAdapter adapter = new CallLogAdapter(this, cursor);
		ListView listView = (ListView) findViewById(R.id.callLogList);
		listView.setAdapter(adapter);
	}
	
	private class CallLogAdapter extends CursorAdapter {
		private SimpleDateFormat mFormatter = new SimpleDateFormat("MM/DD HH:mm");
		
		public CallLogAdapter(Context context, Cursor cursor) {
			super(context, cursor);
		}
		
		public void bindView(View view, Context context, Cursor cursor) {
			int col = cursor.getColumnIndex(CallLog.Calls.NUMBER);
			String value = cursor.getString(col);
			String fvalue = PhoneNumberUtils.formatNumber(value);
			Log.d("phone", fvalue);			
			TextView text = (TextView) view.findViewById(R.id.number);
			text.setText(fvalue);
			
			col = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
			value = cursor.getString(col);
			text = (TextView) view.findViewById(R.id.name);
			text.setText(value);
			
			col = cursor.getColumnIndex(CallLog.Calls.TYPE);
			int iType = cursor.getInt(col);
			value = getTypeString(iType);
			text = (TextView) view.findViewById(R.id.type);
			text.setText(value);
			
			col = cursor.getColumnIndex(CallLog.Calls.DATE);
			long lDate = cursor.getLong(col);
			String sDate = mFormatter.format(new Date(lDate));
			text = (TextView) view.findViewById(R.id.date);
			text.setText(sDate);
			
			col = cursor.getColumnIndex(CallLog.Calls.DURATION);
			int iDuration = cursor.getInt(col);
			text = (TextView) view.findViewById(R.id.duration);
			text.setText(iDuration + "초");
		}
		
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			final LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.call_log_row, parent, false);
			return v;
		}
		
		private String getTypeString(int iType) {
			String sType = "Unknown";
			
			switch (iType) {
			case CallLog.Calls.INCOMING_TYPE:
				sType = "수신";
				break;
			case CallLog.Calls.OUTGOING_TYPE:
				sType = "발신";
				break;
			case CallLog.Calls.MISSED_TYPE:
				sType = "부재중";
				break;
			}
			return sType;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
