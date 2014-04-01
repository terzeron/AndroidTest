package net.terzeron.canvastest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawingView(this));
	}
	
	public class DrawingView extends View {
		public DrawingView(Context context) {
			super(context);
		}
		
		public DrawingView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}
		
		public DrawingView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setTextSize(22);
			paint.setAntiAlias(true);
			
			// circle
			canvas.drawCircle(240, 100, 70, paint);
			canvas.drawText("Circle", 200, 190, paint);
			
			// rectangle
			canvas.drawRect(190, 200, 290, 300, paint);
			canvas.drawText("Rect", 200, 320, paint);
			
			// arc
			RectF rf = new RectF(190, 350, 290, 450);
			canvas.drawArc(rf, 0, 100, true, paint);
			canvas.drawText("Arc", 200, 470, paint);
			
			// line
			paint.setStrokeWidth(10);
			canvas.drawLine(200, 500, 300, 500, paint);
			canvas.drawText("Line", 200, 530, paint);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
