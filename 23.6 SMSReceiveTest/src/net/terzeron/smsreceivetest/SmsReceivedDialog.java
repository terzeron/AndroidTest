package net.terzeron.smsreceivetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class SmsReceivedDialog extends Activity {

	private static final int DIALOG_SHOW_MESSAGE = 1;
	public static final String SMS_FROM_ADDRESS_EXTRA = "com.nhncorp.android.SMS_FROM_ADDRESS";
	public static final String SMS_FROM_DISPLAY_NAME_EXTRA = "com.nhncorp.android.SMS_FROM_DISPLAY_NAME";
	public static final String SMS_MESSAGE_EXTRA = "com.nhncorp.android.SMS_MESSAGE";
	private String mFromDisplayName;
	private String mFromAddress;
	private String mMessage;
	private String mFullBodyString;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFromAddress = getIntent().getExtras()
				.getString(SMS_FROM_ADDRESS_EXTRA);
		mFromDisplayName = getIntent().getExtras().getString(
				SMS_FROM_DISPLAY_NAME_EXTRA);
		mMessage = getIntent().getExtras().getString(SMS_MESSAGE_EXTRA);
		mFullBodyString = mFromDisplayName + "(" + mFromAddress + ")"
				+ mMessage;
		showDialog(DIALOG_SHOW_MESSAGE);
	}

	protected Dialog onCreateDialog(int id, Bundle bundle) {
		switch (id) {
		case DIALOG_SHOW_MESSAGE:
			return new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_email)
					.setTitle("메시지가 왔습니다.")
					.setMessage(mFullBodyString)
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.dismiss();
									finish();
								}
							}).create();
		}
		return null;
	}
}
