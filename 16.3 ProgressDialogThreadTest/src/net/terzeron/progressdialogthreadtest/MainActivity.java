package net.terzeron.progressdialogthreadtest;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	final static int PROGRESS_DIALOG = 0;
	ProgressThread progressThread;
	ProgressDialog progressDialog;
	final static String DIALOG_TAG = "progress_dialog";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(PROGRESS_DIALOG);
			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_DIALOG:
			progressDialog = new ProgressDialog(this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Data Loading...");
//			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "취소",
//					new DialogInterface.OnClickListener() {
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							progressThread.interrupt();
//						}
//
//					});
			progressThread = new ProgressThread(new MyHandler(this));
			progressThread.start();
			progressDialog.setMax(100);

			return progressDialog;
		default:
			return null;
		}
	}
	
	static class MyHandler extends Handler {
		private final WeakReference<MainActivity> mActivity;

		public MyHandler(MainActivity activity) {
			mActivity = new WeakReference<MainActivity>(activity);
		}

		public void handleMessage(Message msg) {
			MainActivity activity = mActivity.get();
			int total = msg.arg1;
			activity.progressDialog.setProgress(total);
			if (total >= 100) {
				activity.dismissDialog(PROGRESS_DIALOG);
				activity.progressThread.interrupt();
			}
		}
	}

	private class ProgressThread extends Thread {
		Handler handler;
		int total;

		ProgressThread(Handler handler) {
			this.handler = handler;
			this.total = 0;
		}

		public void run() {
			total = 0;
			while (!interrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Log.e("ERROR", "Thread interrupted");
				}
				Message msg = handler.obtainMessage();
				msg.arg1 = total;
				handler.sendMessage(msg);
				total++;
			}
		}
	}

}
