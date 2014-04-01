package net.terzeron.dragndroptest;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String IMAGEVIEW_TAG = "icon bitmap";
	ImageView iv;
	Bitmap icon;
	OnDragListener dragListener = new MyDragEventListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.imageview);
		icon = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		iv.setImageBitmap(icon);
		iv.setTag(IMAGEVIEW_TAG);
		iv.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getApplicationContext(),
						"ImageView is LongClicked", Toast.LENGTH_SHORT).show();
				Item item = new Item((CharSequence) v.getTag());
				String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
				ClipData dragData = new ClipData((CharSequence) v.getTag(),
						mimeTypes, item);
				DragShadowBuilder shadow = new MyDragShadowBuilder(iv);
				return v.startDrag(dragData, shadow, null, 0);
			}
		});
		ImageView dv = (ImageView) findViewById(R.id.dropview);
		dv.setOnDragListener(dragListener);
	}

	private static class MyDragShadowBuilder extends DragShadowBuilder {
		private static Drawable shadow;

		public MyDragShadowBuilder(View v) {
			super(v);
			shadow = new ColorDrawable(Color.LTGRAY);
		}

		public void onProvideShadowMetrics(Point size, Point touch) {
			int width;
			int height;
			width = getView().getWidth() / 2;
			height = getView().getHeight() / 2;
			size.set(width, height);
			touch.set(width / 2, height / 2);
		}

		public void onDrawShadow(Canvas canvas) {
			shadow.draw(canvas);
		}
	}

	protected class MyDragEventListener implements OnDragListener {
		public boolean onDrag(View v, DragEvent event) {
			final int action = event.getAction();
			switch (action) {
			case DragEvent.ACTION_DRAG_STARTED:
				if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
					((ImageView) v).setColorFilter(Color.BLUE);
					Toast.makeText(getApplicationContext(), "ACTION_DRAG_STARTED", Toast.LENGTH_SHORT).show();
					v.invalidate();
					return true;
				} else {
					return false;
				}
			case DragEvent.ACTION_DRAG_ENTERED:
				((ImageView) v).setColorFilter(Color.GREEN);
				Toast.makeText(getApplicationContext(), "ACTION_DRAG_ENTERED", Toast.LENGTH_SHORT).show();
				v.invalidate();
				return true;
			case DragEvent.ACTION_DRAG_LOCATION:
				return true;
			case DragEvent.ACTION_DRAG_EXITED:
				((ImageView) v).setColorFilter(Color.RED);
				Toast.makeText(getApplicationContext(), "ACTION_DRAG_EXITED", Toast.LENGTH_SHORT).show();
				v.invalidate();
				return true;
			case DragEvent.ACTION_DROP:
				Item item = event.getClipData().getItemAt(0);
				CharSequence dragData = item.getText();
				Toast.makeText(getApplicationContext(), "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
				((ImageView) v).clearColorFilter();
				v.invalidate();
				return true;
			case DragEvent.ACTION_DRAG_ENDED:
				((ImageView) v).clearColorFilter();
				v.invalidate();
				if (event.getResult()) {
					Toast.makeText(getApplicationContext(), "The drop was handled", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "The drop didn't work", Toast.LENGTH_SHORT).show();
				}
				return true;
			default:
				Log.e("MyDragEventListener", "unknown type");
				break;
			}
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
