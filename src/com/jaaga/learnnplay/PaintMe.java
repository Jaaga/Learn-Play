package com.jaaga.learnnplay;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class PaintMe extends Activity {

	private Bitmap mBitmap;
	private Paint p;
	private Canvas c;
	private static final String TAG = "MainActivity";
	private static String mAlphaName = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		mAlphaName = prefs.getString("name", "no name");
		
		Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final int height = size.y;
        
        mBitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        c = new Canvas(mBitmap);
        p = new Paint();

        View view = new View(this){

			protected void onDraw(Canvas canvas) 
        	{	
        		canvas.drawBitmap(mBitmap, 0, 0, null);
        	
        		p.setTextSize(width/1.2f);
        		p.setColor(0xff0000ff);
        		canvas.drawText(mAlphaName,width/5, height/1.7f, p);
        		
        		postInvalidateDelayed(0);
        	}
        };
        
        setContentView(view);
        
        view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
		        float Pointx = event.getX();
		        float Pointy = event.getY();
		        
		        p.setColor(0xff0000ff);
		        c.drawCircle(Pointx, Pointy, 10, p);
		        return true;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paint_me, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
