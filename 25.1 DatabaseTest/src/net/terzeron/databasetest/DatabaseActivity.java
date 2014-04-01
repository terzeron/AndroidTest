package net.terzeron.databasetest;

import java.util.List;
import java.util.Random;

import com.example.ch25_1.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.view.Menu;

public class DatabaseActivity extends ListActivity {
	final public static String TAG = "Database";
	private MemoDatasource datasource;
	private EditText mMemoET;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		datasource = new MemoDatasource(this);
		datasource.open();
		// 저장되어 있는 데이터를 가져온다.
		List<Memo> values = datasource.getAllMemos();
		ArrayAdapter<Memo> adapter = new ArrayAdapter<Memo>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

		mMemoET = (EditText) findViewById(R.id.editText1);
	}

	public void onClick(View view) {
		ArrayAdapter<Memo> adapter = (ArrayAdapter<Memo>) getListAdapter();
		Memo memo = null;
		switch (view.getId()) {
		case R.id.add:
			String inputValue = mMemoET.getText().toString();
			if (inputValue == null || "".equals(inputValue))
				return;
			memo = datasource.createMemo(inputValue);
			adapter.add(memo);
			mMemoET.getText().clear();
			break;
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		ArrayAdapter<Memo> adapter = (ArrayAdapter<Memo>) getListAdapter();
		Memo memo = adapter.getItem(position);
		datasource.deleteMemo(memo);
		adapter.remove(memo);
		adapter.notifyDataSetChanged();
	}
}
