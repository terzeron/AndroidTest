package net.terzeron.dropdownnavigationtest;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.action_list,
				android.R.layout.simple_spinner_dropdown_item);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		OnNavigationListener mOnNavigationListener;

		mOnNavigationListener = new OnNavigationListener() {
			String[] strings = getResources().getStringArray(
					R.array.action_list);

			@Override
			public boolean onNavigationItemSelected(int position, long itemId) {
				ListContentFragment newFragment = new ListContentFragment();
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.fragment_container, newFragment,
						strings[position]);
				ft.commit();
				return true;
			}
		};

		actionBar.setListNavigationCallbacks(mSpinnerAdapter,
				mOnNavigationListener);

	}

	public static class ListContentFragment extends Fragment {
		private String mText;

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			mText = getTag();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			TextView text = new TextView(getActivity());
			text.setText(mText);
			return text;
		}
	}

}
