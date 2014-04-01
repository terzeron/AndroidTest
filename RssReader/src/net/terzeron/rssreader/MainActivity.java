package net.terzeron.rssreader;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Category에 Feed가 속하고 Feed에 Item들이 속하는 구조
 * 화면 전환 시, 좌우 슬라이딩 효과가 필요함 
 * 모든 기능에서 애매한 부분은 옵션(pref)으로 처리
 * 
 * feed list table schema: create table feed_list (id int, name varchar(100), item_count int);
 */

public class MainActivity extends Activity {

	private FeedListDatasource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new FeedListDatasource(this);
		datasource.open();
		List<Feed> values = datasource.getAllFeeds();
		ArrayAdapter<Feed> adapter = new ArrayAdapter<Feed>(this, android.R.layout.simple_list_item_1, values);
		ListView feedListView = (ListView) findViewById(R.id.feedListView);
		feedListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
