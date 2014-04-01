package net.terzeron.smssendapitest;

import com.nhncorp.smssendapitest.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText mNumberEditText;
	private EditText mMessageEditText;
	private TextView mSentTextView;
	private TextView mDeliveryTextView;

	final private static String MESSAGE_SENT_ACTION = "com.nhncorp.MESSAGE_SENT";
	final private static String MESSAGE_DELIVERY_ACTION = "com.nhncorp.MESSAGE_DELIVERY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNumberEditText = (EditText) findViewById(R.id.number);
		mMessageEditText = (EditText) findViewById(R.id.message);
		mSentTextView = (TextView) findViewById(R.id.sent);
		mDeliveryTextView = (TextView) findViewById(R.id.delivery);
	}

	protected void onPause() {
		super.onPause();
		unregisterReceiver(mSentBR);
		unregisterReceiver(mDeliveryBR);
	}

	protected void onResume() {
		super.onResume();
		registerReceiver(mSentBR, new IntentFilter(MESSAGE_SENT_ACTION));
		registerReceiver(mDeliveryBR, new IntentFilter(MESSAGE_DELIVERY_ACTION));
	}

	public void mSendMessage(View v) {
		String number = mNumberEditText.getText().toString();
		String message = mMessageEditText.getText().toString();
		mSentTextView.setText("나의 송신대기");
		mDeliveryTextView.setText("상대방 수신대기");
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0,
				new Intent(MESSAGE_SENT_ACTION), 0);
		PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0,
				new Intent(MESSAGE_DELIVERY_ACTION), 0);
		smsManager.sendTextMessage(number, null, message, sentIntent,
				deliveryIntent);
	}

	private BroadcastReceiver mSentBR = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			if (getResultCode() == Activity.RESULT_OK) {
				mSentTextView.setText("메시지 송신 성공");
			} else {
				mSentTextView.setText("메시지 송신 실패");
			}
		}
	};

	private BroadcastReceiver mDeliveryBR = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			if (getResultCode() == Activity.RESULT_OK) {
				mSentTextView.setText("상대방이 메시지 수신에 성공했습니다.");
			} else {
				mSentTextView.setText("상대방이 메시지 수신에 실패했습니다.");
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
