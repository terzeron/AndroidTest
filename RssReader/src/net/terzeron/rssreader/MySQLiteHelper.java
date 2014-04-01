package net.terzeron.rssreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_FEED_LIST = "feed_lsit";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COUNT = "count";

    private static final String DATABASE_NAME = "rss_reader.db";
    private static final int DATABASE_VERSION = 2;

    // 테이블을 생성하는 sql문
    private static final String DATABASE_CREATE = 
        "create table "+ TABLE_FEED_LIST + "( " + 
        COLUMN_ID + " integer primary key autoincrement, " + 
        COLUMN_NAME + " text not null, " +
        COLUMN_COUNT + " integer" +
        ");";
    private static final String DUMMY_INSERT = "insert into " + TABLE_FEED_LIST + " (" + COLUMN_NAME + ", " + COLUMN_COUNT + ") values (" + "'test feed 1', 10" + ");";  
     

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DUMMY_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEED_LIST);
        onCreate(db);
    }
}