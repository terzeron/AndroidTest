package net.terzeron.filetest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FileActivity extends Activity {
	final private static String TAG = "appstudy";
	final private static String FILE_NAME = "appstudy.txt";
	private EditText mContentED;
	private TextView mStatusTV;
	private Button mSaveSDBT;
	private Button mLoadSDBT;
	private String mSdPath;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		mContentED = (EditText) findViewById(R.id.editText1);
		mStatusTV = (TextView) findViewById(R.id.status);
		mSaveSDBT = (Button) findViewById(R.id.savetosd);
		mLoadSDBT = (Button) findViewById(R.id.loadfromsd);
	}

	@Override
	protected void onResume() {
		super.onResume();
		String ext = Environment.getExternalStorageState();
		if (ext.equals(Environment.MEDIA_MOUNTED)) {
			mSdPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			mStatusTV.setText("SDCard Mount");
			mSaveSDBT.setText("Save to " + mSdPath + File.separatorChar
					+ FILE_NAME);
			mLoadSDBT.setText("Load from " + mSdPath + File.separatorChar
					+ FILE_NAME);
		} else {
			mStatusTV.setText("SDCard Unmount");
		}
	}

	public void mOnClickLoadFromResource(View v) {
		InputStream fres = null;
		try {
			fres = getResources().openRawResource(R.raw.appstudy);
			inputStream2Display(fres);
			mStatusTV.setText("res/raw/" + FILE_NAME + " is loaded");

		} finally {
			try {
				if (fres != null)
					fres.close();
			} catch (Exception e) {
				
			}
		}
	}

	private void inputStream2Display(InputStream fres) {
		BufferedInputStream bis = null;
		try {
			StringBuffer sb = new StringBuffer();
			bis = new BufferedInputStream(fres);
			byte[] buf = new byte[1024];
			int numRead = 0;
			while ((numRead = bis.read(buf)) != -1) {
				String readData = new String(buf, 0, numRead);
				sb.append(readData);
			}
			mContentED.setText(sb.toString());
		} catch (IOException e) {
			mStatusTV.setText("io error occurred in reading the file");
		} finally {
			try {
				if (bis != null)
					bis.close();
			} catch (Exception e) {
			
			}
		}
	}

	public void mOnClickLoadFromSD(View v) {
		FileInputStream fis = null;
		String filePath = null;
		try {
			filePath = mSdPath + File.separatorChar + FILE_NAME;
			fis = new FileInputStream(mSdPath + File.separatorChar + FILE_NAME);
			inputStream2Display(fis);
			mStatusTV.setText(filePath + " is loaded.");
		} catch (FileNotFoundException e) {
			mStatusTV.setText(filePath + " is not found.");
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {

			}
		}
	}

	public void mOnClickLoadFromLocal(View v) {
		FileInputStream fis = null;
		try {
			fis = openFileInput(FILE_NAME);
			inputStream2Display(fis);
			mStatusTV.setText(FILE_NAME + " is loaded.");
		} catch (FileNotFoundException e) {
			mStatusTV.setText(FILE_NAME + " is not found.");
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				
			}
		}
	}

	public void mOnClickSaveToSD(View v) {
		BufferedOutputStream bos = null;
		String filePath = null;
		try {
			filePath = mSdPath + File.separatorChar + FILE_NAME;
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			bos.write(mContentED.getText().toString().getBytes());
			mStatusTV.setText(filePath + " is saved.");
		} catch (FileNotFoundException ex) {
			mStatusTV.setText(filePath + " is not found.");
		} catch (IOException ex) {
			mStatusTV.setText("io error occurred in reading file '" + filePath + "'");
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (Exception ex) {

			}
		}
	}

	public void mOnClickSaveToLocal(View v) {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
			bos.write(mContentED.getText().toString().getBytes());
			mStatusTV.setText(FILE_NAME + " is saved.");
		} catch (FileNotFoundException ex) {
			mStatusTV.setText(FILE_NAME + " is not found.");
		} catch (IOException ex) {
			mStatusTV.setText("io error occurred in reading file '" + FILE_NAME + "'");
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (Exception ex) {
			}
			;
		}
	}
}