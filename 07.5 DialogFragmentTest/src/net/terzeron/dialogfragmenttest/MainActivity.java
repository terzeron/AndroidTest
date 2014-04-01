package net.terzeron.dialogfragmenttest;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	int mStackLevel = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tv = (TextView) findViewById(R.id.text);
		tv.setText("대화상자 프래그먼트를 보여주는 예제. 대화상자를 보려면 '보여주기' 버튼을 누르세요");

		Button button = (Button) findViewById(R.id.show);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog();
			}
		});

		if (savedInstanceState != null) {
			mStackLevel = savedInstanceState.getInt("level");
		}
	}

	void showDialog() {
		mStackLevel++;
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			// 기존에 대화상자 프래그먼트가 존재하면 삭제함
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		DialogFragment newFragment = MyDialogFragment.newInstance(mStackLevel);
		newFragment.show(ft, "dialog");
	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("level", mStackLevel);
	}

	static String getNameForNum(int num) {
		switch (num - 1) {
		case 1:
			return "STYLE_NO_TITLE";
		case 2:
			return "STYLE_NO_FRAME";
		case 3:
			return "STYLE_NO_INPUT";
		case 4:
			return "STYLE_NORMAL with dark fullscreen theme";
		case 5:
			return "STYLE_NORMAL with light theme";
		case 6:
			return "STYLE_NO_TITLE with light theme";
		case 7:
			return "STYLE_NO_FRAME with light theme";
		case 8:
			return "STYLE_NORMAL with light fullscreen theme";
		}
		return "STYLE_NORMAL";
	}

	public static class MyDialogFragment extends DialogFragment {
		int mNum;

		static MyDialogFragment newInstance(int num) {
			MyDialogFragment f = new MyDialogFragment();
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			return f;
		}

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments().getInt("num");

			int style = DialogFragment.STYLE_NORMAL, theme = 0;
			switch (mNum - 1) {
			case 1:
				style = DialogFragment.STYLE_NO_TITLE;
				break;
			case 2:
				style = DialogFragment.STYLE_NO_FRAME;
				break;
			case 3:
				style = DialogFragment.STYLE_NO_INPUT;
				break;
			case 4:
				style = DialogFragment.STYLE_NORMAL;
				break;
			case 5:
				style = DialogFragment.STYLE_NORMAL;
				break;
			case 6:
				style = DialogFragment.STYLE_NO_TITLE;
				break;
			case 7:
				style = DialogFragment.STYLE_NO_FRAME;
				break;
			case 8:
				style = DialogFragment.STYLE_NORMAL;
				break;
			}
			switch (mNum - 1) {
			case 4:
				theme = android.R.style.Theme_Holo;
				break;
			case 5:
				theme = android.R.style.Theme_Holo_Light_Dialog;
				break;
			case 6:
				theme = android.R.style.Theme_Holo_Light;
				break;
			case 7:
				theme = android.R.style.Theme_Holo_Light_Panel;
				break;
			case 8:
				theme = android.R.style.Theme_Holo_Light;
				break;
			}
			setStyle(style, theme);
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.activity_main, container, false);
			TextView tv = (TextView) v.findViewById(R.id.text);
			tv.setText("Dialog #" + mNum + ": using style "
					+ getNameForNum(mNum));

			Button button = (Button) v.findViewById(R.id.show);
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					((MainActivity) getActivity()).showDialog();
				}
			});

			return v;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
