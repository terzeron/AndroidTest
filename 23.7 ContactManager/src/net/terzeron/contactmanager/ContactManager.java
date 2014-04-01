package net.terzeron.contactmanager;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactManager extends Activity {
	public static final String TAG = "ContactManager";
	private Button mAddAccountButton;
	private ListView mContactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 연락처 추가 버튼
		mAddAccountButton = (Button) findViewById(R.id.addContactButton);
		// 연락처 리스트
		mContactList = (ListView) findViewById(R.id.contactList);
		// 연락처 추가를 위한 액티비티로 진행
		mAddAccountButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				launchContactAdder();
			}
		});
		// 연락처를 가져와서 목록에 설정
		populateContactList();
	}

	private void populateContactList() {
		Cursor cursor = getContacts();
		ContactAdapter adapter = new ContactAdapter(this, cursor);
		mContactList.setAdapter(adapter);
	}

	private class ContactAdapter extends CursorAdapter {
		public ContactAdapter(Context context, Cursor cursor) {
			super(context, cursor);
		}

		public void bindView(View view, Context context, Cursor cursor) {
			// DISPLAY_NAME과 _ID 필드의 값을 꺼냄
			int col = cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			String name = cursor.getString(col);
			String value = name;
			col = cursor.getColumnIndex(ContactsContract.Contacts._ID);
			String id = cursor.getString(col);

			// _ID 필드를 가지고 다시 조회
			ContentResolver cr = getContentResolver();
			Cursor cursor2 = cr.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
					new String[] { id }, null);
			int typeIndex = cursor2
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
			int numberIndex = cursor2
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			while (cursor2.moveToNext()) {
				String number = cursor2.getString(numberIndex);
				switch (cursor2.getInt(typeIndex)) {
				case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
					value += " 핸드폰:" + number;
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
					value += " 집:" + number;
					break;
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
					value += " 회사:" + number;
					break;
				}
			}
			cursor2.close();
			TextView text = (TextView) view.findViewById(R.id.contactEntryText);
			text.setText(value);
		}

		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			final LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.contact_entry, parent, false);
			return v;
		}
	}

	// 연락처 정보를 커서로 조회
	private Cursor getContacts() {
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";
		Cursor c = managedQuery(uri, projection, null, null, sortOrder);
		return c;
	}

	// 연락처 추가를 위한 액티비티를 기동
	protected void launchContactAdder() {
		Intent i = new Intent(this, ContactAdder.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
