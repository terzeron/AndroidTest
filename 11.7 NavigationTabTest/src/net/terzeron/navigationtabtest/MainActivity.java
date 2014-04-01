package net.terzeron.navigationtabtest;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		bar.addTab(bar
				.newTab()
				.setText("First")
				.setTabListener(
						new TabListener<FirstFragment>(this, "first",
								FirstFragment.class)));
		bar.addTab(bar
				.newTab()
				.setText("Second")
				.setTabListener(
						new TabListener<SecondFragment>(this, "second",
								SecondFragment.class)));
		bar.addTab(bar
				.newTab()
				.setText("Third")
				.setTabListener(
						new TabListener<ThirdFragment>(this, "third",
								ThirdFragment.class)));
		if (savedInstanceState != null) {
			// 탭 인덱스를 꺼냄
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}
		bar.show();
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// 탭 인덱스를 저장함
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}
	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		private final Bundle mArgs;
		private Fragment mFragment;
		
		public TabListener(Activity activity, String tag, Class<T> clz) {
			this(activity, tag, clz, null);
		}
		
		public TabListener(Activity activity, String tag, Class<T> clz, Bundle args) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
			mArgs = args;
			mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
			if (mFragment != null && !mFragment.isDetached()) {
				FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
				ft.detach(mFragment);
				ft.commit();				
			}
		}
		
		public void onTabSelected(Tab Tab, FragmentTransaction ft) {
			if (mFragment == null) {
				mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				ft.attach(mFragment);
			}
		}
		
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
		}
		
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public static class FirstFragment extends Fragment {
		int mNum;
		
		static FirstFragment newInstance(int num) {
			FirstFragment f = new FirstFragment();
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			return f;
		}
		
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		}
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.hello_first, container, false);
			TextView tv = (TextView) v.findViewById(R.id.text);
			tv.setText("Fragment #" + FirstFragment.class.getName());
			return v;
		}
	}

	public static class SecondFragment extends Fragment {
		int mNum;
		
		static SecondFragment newInstance(int num) {
			SecondFragment f = new SecondFragment();
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			return f;
		}
		
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		}
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.hello_second, container, false);
			TextView tv = (TextView) v.findViewById(R.id.text);
			tv.setText("Fragment #" + SecondFragment.class.getName());
			return v;
		}
	}

	public static class ThirdFragment extends Fragment {
		int mNum;
		
		static ThirdFragment newInstance(int num) {
			ThirdFragment f = new ThirdFragment();
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			return f;
		}
		
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		}
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.hello_third, container, false);
			TextView tv = (TextView) v.findViewById(R.id.text);
			tv.setText("Fragment #" + ThirdFragment.class.getName());
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
