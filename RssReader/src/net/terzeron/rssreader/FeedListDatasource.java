package net.terzeron.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FeedListDatasource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_COUNT };

	public FeedListDatasource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Feed createFeed(String name, int count) {
		Cursor cursor = null;
		try {
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_NAME, name);
			long insertId = database.insert(MySQLiteHelper.TABLE_FEED_LIST, null,
					values);
			cursor = database.query(MySQLiteHelper.TABLE_FEED_LIST, allColumns,
					MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null,
					null, null);
			cursor.moveToFirst();
			return cursorToFeed(cursor);
		} finally {
			closeCursor(cursor);
		}
	}

	public void deleteFeed(Feed Feed) {
		long id = Feed.getId();
		database.delete(MySQLiteHelper.TABLE_FEED_LIST, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Feed> getAllFeeds() {
		List<Feed> feed_list = new ArrayList<Feed>();
		Cursor cursor = null;
		try {
			cursor = database.query(MySQLiteHelper.TABLE_FEED_LIST, allColumns,
					null, null, null, null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Feed feed = cursorToFeed(cursor);
				feed_list.add(feed);
				cursor.moveToNext();
			}
			return feed_list;
		} finally {
			closeCursor(cursor);
		}
	}

	private void closeCursor(Cursor cursor) {
		try {
			if (cursor != null) {
				cursor.close();
			}
		} catch (Exception e) {
		}
	}

	private Feed cursorToFeed(Cursor cursor) {
		Feed feed = new Feed();
		int idIndex = cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID);
		int nameIndex = cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME);
		int countIndex = cursor.getColumnIndex(MySQLiteHelper.COLUMN_COUNT);
		feed.setId(cursor.getLong(idIndex));
		feed.setName(cursor.getString(nameIndex));
		feed.setCount(cursor.getLong(countIndex));
		return feed;
	}
}