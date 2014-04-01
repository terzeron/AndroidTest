package net.terzeron.gridviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		
		public ImageAdapter(Context c) {
			mContext = c;
		}
		
		public int getCount() {
			return mThumbIds.length;
		}
		
		public Object getItem(int position) {
			return null;
		}
		
		public long getItemId(int position) {
			return 0;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv;
			if (convertView == null) {
				iv = new ImageView(mContext);
				iv.setLayoutParams(new GridView.LayoutParams(85, 85));
				iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
				iv.setPadding(8, 8, 8, 8);
			} else {
				iv = (ImageView) convertView;
			}
			iv.setImageResource(mThumbIds[position]);
			return iv;
		}
		
		private Integer[] mThumbIds = {
				android.R.drawable.arrow_up_float, android.R.drawable.btn_dropdown,
				android.R.drawable.btn_star, android.R.drawable.arrow_up_float,
				android.R.drawable.bottom_bar, android.R.drawable.arrow_down_float,
				android.R.drawable.btn_default, android.R.drawable.btn_default_small,
				android.R.drawable.btn_dialog, android.R.drawable.btn_dropdown,
				android.R.drawable.btn_minus, android.R.drawable.btn_plus,
				android.R.drawable.btn_radio, android.R.drawable.btn_star,
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
