package net.terzeron.contactmanager;

import java.util.ArrayList;
import java.util.Iterator;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ContactAdder extends Activity implements OnAccountsUpdateListener {
	public static final String TAG = "ContactAdder";
	private ArrayList<AccountData> mAccounts;
	private AccountAdapter mAccountAdapter;
	private Spinner mAccountSpinner;
	private EditText mContactEmailEditText;
	private ArrayList<Integer> mContactEmailTypes;
	private Spinner mContactEmailTypeSpinner;
	private EditText mContactNameEditText;
	private EditText mContactPhoneEditText;
	private ArrayList<Integer> mContactPhoneTypes;
	private Spinner mContactPhoneTypeSpinner;
	private Button mContactSaveButton;
	private AccountData mSelectedAccount;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_adder);

		// 화면 상의 뷰에 입력된 값을 꺼냄
		mAccountSpinner = (Spinner) findViewById(R.id.accountSpinner);
		mContactNameEditText = (EditText) findViewById(R.id.contactNameEditText);
		mContactPhoneEditText = (EditText) findViewById(R.id.contactPhoneEditText);
		mContactEmailEditText = (EditText) findViewById(R.id.contactEmailEditText);
		mContactPhoneTypeSpinner = (Spinner) findViewById(R.id.contactPhoneTypeSpinner);
		mContactEmailTypeSpinner = (Spinner) findViewById(R.id.contactEmailTypeSpinner);
		mContactSaveButton = (Button) findViewById(R.id.contactSaveButton);

		// 전화 타입 스피너
		mContactPhoneTypes = new ArrayList<Integer>();
		mContactPhoneTypes
				.add(ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
		mContactPhoneTypes
				.add(ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
		mContactPhoneTypes
				.add(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
		mContactPhoneTypes
				.add(ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);

		// 이메일 타입 스피너
		mContactEmailTypes = new ArrayList<Integer>();
		mContactEmailTypes
				.add(ContactsContract.CommonDataKinds.Email.TYPE_HOME);
		mContactEmailTypes
				.add(ContactsContract.CommonDataKinds.Email.TYPE_WORK);
		mContactEmailTypes
				.add(ContactsContract.CommonDataKinds.Email.TYPE_MOBILE);
		mContactEmailTypes
				.add(ContactsContract.CommonDataKinds.Email.TYPE_OTHER);

		// 계정 스피너
		mAccounts = new ArrayList<AccountData>();
		mAccountAdapter = new AccountAdapter(this, mAccounts);
		mAccountSpinner.setAdapter(mAccountAdapter);

		// 전화 종류 스피너
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Iterator<Integer> iter = mContactPhoneTypes.iterator();
		while (iter.hasNext()) {
			adapter.add(ContactsContract.CommonDataKinds.Phone.getTypeLabel(
					this.getResources(), iter.next(),
					getString(R.string.undefinedTypeLabel)).toString());
		}
		mContactPhoneTypeSpinner.setAdapter(adapter);
		mContactPhoneTypeSpinner.setPrompt(getString(R.string.selectLabel));

		// 이메일 종류 스피너
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		iter = mContactEmailTypes.iterator();
		while (iter.hasNext()) {
			adapter.add(ContactsContract.CommonDataKinds.Email.getTypeLabel(
					this.getResources(), iter.next(),
					getString(R.string.undefinedTypeLabel)).toString());
		}
		mContactEmailTypeSpinner.setAdapter(adapter);
		mContactEmailTypeSpinner.setPrompt(getString(R.string.selectLabel));

		// 계정 정보 조회
		AccountManager.get(this).addOnAccountsUpdatedListener(this, null, true);

		// 계정 선택 시 콜백 호출
		mAccountSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long i) {
				updateAccountSelection();
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		mContactSaveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onSaveButtonClicked();
			}
		});
	}

	private void onSaveButtonClicked() {
		createContactEntry();
		finish();
	}

	protected void createContactEntry() {
		String name = mContactNameEditText.getText().toString();
		String phone = mContactPhoneEditText.getText().toString();
		String email = mContactEmailEditText.getText().toString();
		int phoneType = mContactPhoneTypes.get(mContactPhoneTypeSpinner
				.getSelectedItemPosition());
		int emailType = mContactEmailTypes.get(mContactEmailTypeSpinner
				.getSelectedItemPosition());

		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		// 계정을 추가하기 위해 ContentProviderOperation의 목록을 만든 후 applyBatch()를 실행
		ops.add(ContentProviderOperation
				.newInsert(ContactsContract.RawContacts.CONTENT_URI)
				.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,
						mSelectedAccount.getType())
				.withValue(ContactsContract.RawContacts.ACCOUNT_NAME,
						mSelectedAccount.getName()).build());
		// 이름(StructuredName)
		ops.add(ContentProviderOperation
				.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(
						ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
				.withValue(
						ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
						name).build());
		// 전화번호
		ops.add(ContentProviderOperation
				.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(
						ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
				.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
				.withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
						phoneType).build());
		// 이메일
		ops.add(ContentProviderOperation
				.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				.withValue(
						ContactsContract.Data.MIMETYPE,
						ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
				.withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
				.withValue(ContactsContract.CommonDataKinds.Email.TYPE,
						emailType).build());
		try {
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.contactCreationFailure),
					Toast.LENGTH_SHORT).show();
		}
	}

	public void onDestroy() {
		AccountManager.get(this).removeOnAccountsUpdatedListener(this);
		super.onDestroy();
	}

	public void onAccountsUpdated(Account[] a) {
		mAccounts.clear();
		AuthenticatorDescription[] accountTypes = AccountManager.get(this)
				.getAuthenticatorTypes();

		for (int i = 0; i < a.length; i++) {
			String systemAccountType = a[i].type;
			AuthenticatorDescription ad = getAuthenticatorDescription(
					systemAccountType, accountTypes);
			AccountData data = new AccountData(a[i].name, ad);
			mAccounts.add(data);
		}
		// 계정 목록이 변경됐음을 알려줌
		mAccountAdapter.notifyDataSetChanged();
	}

	// 어떤 계정 타입인지 반환
	private static AuthenticatorDescription getAuthenticatorDescription(
			String type, AuthenticatorDescription[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].type.equals(type)) {
				return dictionary[i];
			}
		}
		throw new RuntimeException("매칭되는 계정 타입이 없음");
	}

	private void updateAccountSelection() {
		mSelectedAccount = (AccountData) mAccountSpinner.getSelectedItem();
	}

	private class AccountData {
		private String mName;
		private String mType;
		private CharSequence mTypeLabel;
		private Drawable mIcon;

		public AccountData(String name, AuthenticatorDescription description) {
			mName = name;
			if (description != null) {
				mType = description.type;
				String packageName = description.packageName;
				PackageManager pm = getPackageManager();
				if (description.labelId != 0) {
					mTypeLabel = pm.getText(packageName, description.labelId,
							null);
					if (mTypeLabel == null) {
						throw new IllegalArgumentException("LabelID provided");
					}
				} else {
					mTypeLabel = "";
				}

				if (description.iconId != 0) {
					mIcon = pm.getDrawable(packageName, description.iconId,
							null);
					if (mIcon == null) {
						throw new IllegalArgumentException(
								"IconID provided, but drawable not found");
					}
				}
			}
		}

		public String getName() {
			return mName;
		}

		public String getType() {
			return mType;
		}

		public CharSequence getTypeLabel() {
			return mTypeLabel;
		}

		public Drawable getIcon() {
			return mIcon;
		}

		public String toString() {
			return mName;
		}
	}

	// 계정을 위한 커스텀 어댑터
	private class AccountAdapter extends ArrayAdapter<AccountData> {
		public AccountAdapter(Context context,
				ArrayList<AccountData> accountData) {
			super(context, android.R.layout.simple_spinner_item, accountData);
			setDropDownViewResource(R.layout.account_entry);
		}

		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater layoutInflater = getLayoutInflater();
				convertView = layoutInflater.inflate(R.layout.account_entry,
						parent, false);
			}
			TextView firstAccountLine = (TextView) convertView
					.findViewById(R.id.firstAccountLine);
			TextView secondAccountLine = (TextView) convertView
					.findViewById(R.id.secondAccountLine);
			ImageView accountIcon = (ImageView) convertView
					.findViewById(R.id.accountIcon);
			AccountData data = getItem(position);
			firstAccountLine.setText(data.getName());
			secondAccountLine.setText(data.getTypeLabel());
			Drawable icon = data.getIcon();
			if (icon == null) {
				icon = getResources().getDrawable(
						android.R.drawable.ic_menu_search);
			}
			accountIcon.setImageDrawable(icon);
			return convertView;
		}
	}

}
