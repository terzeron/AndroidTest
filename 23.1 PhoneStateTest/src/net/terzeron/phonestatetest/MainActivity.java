package net.terzeron.phonestatetest;

import net.terzeron.phonestatetest.R;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mServiceStateTextView;
	private TextView mCellLocationTextView;
	private TextView mCallStateTextView;
	private ProgressBar mSignalLevelProgressBar;
	private TextView mSignalLevelTextView;
	private TextView mDeviceInfoTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mServiceStateTextView = (TextView) findViewById(R.id.serviceState_info);
		mCellLocationTextView = (TextView) findViewById(R.id.cellLocation_info);
		mCallStateTextView = (TextView) findViewById(R.id.callState_info);
		mSignalLevelProgressBar = (ProgressBar) findViewById(R.id.signalLevel);
		mSignalLevelTextView = (TextView) findViewById(R.id.signalLevel_info);
		mDeviceInfoTextView = (TextView) findViewById(R.id.device_info);
	}

	protected void onPause() {
		super.onPause();
		// 리스너 등록 해지
		stopPhoneStateListener();
	}

	protected void onResume() {
		super.onResume();
		// 리스너 등록
		startPhoneStateListener();

		// 전화기 상태를 표시하는 함수 호출
		displayTelephonyInfo();
	}

	private void startPhoneStateListener() {
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		int events = PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
				| PhoneStateListener.LISTEN_CELL_LOCATION
				| PhoneStateListener.LISTEN_CALL_STATE
				| PhoneStateListener.LISTEN_SERVICE_STATE;
		tm.listen(mPhoneStateListener, events);
	}

	private void stopPhoneStateListener() {
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
	}

	private void displayTelephonyInfo() {
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		String phoneNumber = tm.getLine1Number();
		String operatorName = tm.getNetworkOperatorName();
		String simCountryCode = tm.getSimCountryIso();
		String simOperator = tm.getSimOperatorName();
		String simSerialNumber = tm.getSimSerialNumber();
		String subscriberId = tm.getSubscriberId();
		String networkType = getNetworkTypeString(tm.getNetworkType());
		String phoneType = getPhoneTypeString(tm.getPhoneType());
		String deviceInfo = "";
		deviceInfo = "<< Device Info >>\n" + "Device ID: " + deviceId + "\n"
				+ "Phone Number: " + phoneNumber + "\n" + "Operator Name: "
				+ operatorName + "\n" + "SIM Country Code: " + simCountryCode
				+ "\n" + "SIM Operator: " + simOperator + "\n"
				+ "SIM Serial Number: " + simSerialNumber + "\n"
				+ "Subscriber ID: " + subscriberId + "\n" + "Network Type: "
				+ networkType + "\n" + "Phone Type: " + phoneType + "\n";
		mDeviceInfoTextView.setText(deviceInfo);
	}

	private String getNetworkTypeString(int type) {
		String typeString = "Unknown";
		switch (type) {
		case TelephonyManager.NETWORK_TYPE_EDGE:
			typeString = "EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			typeString = "GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			typeString = "UMTS";
			break;
		default:
			typeString = "Unknown";
			break;
		}
		return typeString;
	}

	private String getPhoneTypeString(int type) {
		String typeString = "Unknown";
		switch (type) {
		case TelephonyManager.PHONE_TYPE_GSM:
			typeString = "GSM";
			break;
		case TelephonyManager.PHONE_TYPE_NONE:
			typeString = "Unknown";
			break;
		default:
			typeString = "Unknown";
			break;
		}
		return typeString;
	}
	
	private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
		public void onCallStateChanged(int state, String incomingNumber) {
			String callState = "UNKNOWN";
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				callState = "IDLE";
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				callState = "Ringing (" + incomingNumber + ")";
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				callState = "Offhook";
				break;
			}
			mCallStateTextView.setText(callState);
			super.onCallStateChanged(state, incomingNumber);
		}

		public void onCellLocationChanged(CellLocation location) {
			String locationString = location.toString();
			mCellLocationTextView.setText(locationString);
			super.onCellLocationChanged(location);
		}
		
		public void onServiceStateChanged(ServiceState serviceState) {
			String serviceStateString = "Unknown";
			switch (serviceState.getState()) {
			case ServiceState.STATE_IN_SERVICE:
				serviceStateString = "IN SERVICE";
				break;
			case ServiceState.STATE_EMERGENCY_ONLY:
				serviceStateString = "EMERGENCY ONLY";
				break;
			case ServiceState.STATE_OUT_OF_SERVICE:
				serviceStateString = "OUT OF SERVICE";
				break;
			case ServiceState.STATE_POWER_OFF:
				serviceStateString = "POWER OFF";
				break;
			default:
				serviceStateString = "Unknown";
				break;
			}
			mServiceStateTextView.setText(serviceStateString);
			super.onServiceStateChanged(serviceState);
		}
		
		public void onSignalStrengthsChanged(SignalStrength signal) {
			int level = signal.getGsmSignalStrength();
			int progress = (int) ((((float) level) / 31.0) * 100);
			String signalLevelString = getSignalLevelString(progress);
			
			mSignalLevelProgressBar.setProgress(progress);
			mSignalLevelTextView.setText(signalLevelString);
			super.onSignalStrengthChanged(level);
		}
		
		private String getSignalLevelString(int level) {
			String signalLevelString = "Weak";
			if (level > 75) {
				signalLevelString = "Excellent";
			} else if (level > 50) {
				signalLevelString = "Good";
			} else if (level > 25) {
				signalLevelString = "Moderate";
			} else if (level > 0) {
				signalLevelString = "Weak";
			}
			return signalLevelString;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
