package net.terzeron.android.gridview;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;


public class HelloGridView extends Activity {
	/** Called when the activity is first created. 
	 * @return 
	 * @throws IOException */


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));


		gridview.setOnItemClickListener(new OnItemClickListener() {

			private void setWallpaper(int position) throws IOException{
				Toast.makeText(HelloGridView.this, "setWallpaper was called", Toast.LENGTH_SHORT).show();

				String str1 = "R.drawable.sample_";
				String str2 = Integer.toString(position);
				String pictureWallpaper = str1.concat(str2);
				String location = pictureWallpaper.concat(".jpg");
				Bitmap newBM = BitmapFactory.decodeFile(location);
				final WallpaperManager myWP = WallpaperManager.getInstance(HelloGridView.this);
				myWP.setBitmap(newBM);
			}

			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				Toast.makeText(HelloGridView.this, "Wallpaper set " + position, Toast.LENGTH_SHORT).show();
				try {
					//                                      setWallpaper(position);
					String str1 = "R.drawable.sample_";
					String str2 = Integer.toString(position);
					String pictureWallpaper = str1.concat(str2);
					String location = pictureWallpaper.concat(".jpg");
					Bitmap newBM = BitmapFactory.decodeFile(location);
					final WallpaperManager myWP = WallpaperManager.getInstance(HelloGridView.this);
					myWP.setBitmap(newBM);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}