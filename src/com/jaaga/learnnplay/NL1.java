package com.jaaga.learnnplay;

import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class NL1 extends Activity{

	MediaPlayer[] media = new MediaPlayer[10];
	private static int click = 0;
	
	private static int[] photos = new int[]{R.drawable.n0,R.drawable.n1,
		R.drawable.n2,R.drawable.n3,R.drawable.n4,R.drawable.n5,R.drawable.n6,
		R.drawable.n7,R.drawable.n8,R.drawable.n9};
	
	private static int[] song = new int[]{R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,
		R.raw.f,R.raw.g,R.raw.h,R.raw.i,R.raw.j};
	
    private Bitmap mBitmap;
	private Bitmap[] mAlpha = new Bitmap[10];
	private int[] mAlphaHwidth = new int[10];
	private int[] mAlphaHheight = new int[10];
	private Bitmap mBG;
	private Paint p;
	private float x = 0;
	private float y = 0;
	private float vx = 1;
	private float vy = 1;
	private Canvas c;
	private static final String TAG = "MainActivity";
	private boolean mTouching; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		for(int i=0;i<10;i++)
		{
			media[i] = MediaPlayer.create(this, song[i]);
		}
		Random rw = new Random();
		final int rWidth = rw.nextInt(10);
		
		Random rh = new Random();
		final int rHeight = rh.nextInt(10);
		
		Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final int height = size.y;
		
        mBitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        
        mBG = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        for(int i=0;i<10;i++)
		{
        	mAlpha[i] = BitmapFactory.decodeResource(getResources(), photos[i]);
        	mAlphaHwidth[i] = mAlpha[i].getWidth() / 2;
            mAlphaHheight[i] = mAlpha[i].getHeight() / 2;
		}
        
        c = new Canvas(mBitmap);
        p = new Paint();

        View v = new View(this){

			protected void onDraw(Canvas canvas) 
        	{	
        		float ScaleX = this.getWidth() / ((float)mBG.getWidth());
        		float ScaleY = this.getHeight() / ((float)mBG.getHeight());
        		
        		canvas.save();
        		canvas.scale(ScaleX, ScaleY);
        		canvas.drawBitmap(mBG, 0, 0, null);
        		
        		canvas.restore();
        		
        		float angle = SystemClock.uptimeMillis()/ 5.0f;
        		canvas.translate(x, y);

        		if(mTouching){
        			click++;
        			canvas.rotate(angle,(float)mAlphaHheight[click],(float)mAlphaHwidth[click]);
            		canvas.drawBitmap(mAlpha[click], 0, 0, null);
            		media[click].start();
            		media[click].setOnCompletionListener(new OnCompletionListener() {
            			
            			@Override
            			public void onCompletion(MediaPlayer mp) {
            				mp.release();
            				mp.reset();
            			}
            		});
        		}
        		else{
        			canvas.rotate(angle,(float)mAlphaHheight[click],(float)mAlphaHwidth[click]);
            		canvas.drawBitmap(mAlpha[click], 0, 0, null);
            		media[click].start();
            		media[click].setOnCompletionListener(new OnCompletionListener() {
            			
            			@Override
            			public void onCompletion(MediaPlayer mp) {
            				
            				mp.reset();
            			}
            		});
        		}
        		
        		x = x + vx;
        		y = y + vy;
        		
        		if((y + 2*mAlphaHheight[0]>= this.getHeight()) ||(y <= -1))
        		{
        			vy = -vy;
        		}
        		else
        		{
        			if(rHeight >= 5){
        				vy = vy + rHeight;
        			}else if(rHeight >= 2 && rHeight < 5){
        				vy = vy + rHeight;
        			}else{vy = vy + 6;}
        		}
        		
        		if((x + 2*mAlphaHwidth[0]>= this.getWidth()) || (x <= -1))
        		{
        			vx = -vx;
        		}
        		else
        		{
        			if(rWidth >= 5){
        				vx = vx + rHeight;
        			}else if(rWidth >= 2 && rWidth < 5){
        				vx = vx + rWidth;
        			}else{vx = vx + 7;}
        		}
        		
        		postInvalidateDelayed(1);
        	}
        };
        
        setContentView(v);
        v.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "on touch!"+event);
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					mTouching = true;
				}
				if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL||action ==MotionEvent.ACTION_MOVE){
					mTouching = false;
				}
				if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE){
					x = event.getX();
					y = event.getY();
					vx = 0;
					vy = 0;
					}
				
		        float Pointx = event.getX();
		        float Pointy = event.getY();
		        p.setColor(0xff0000ff);
		        c.drawCircle(Pointx, Pointy, 2, p);
		        return true;
			}
			
		});
       
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alphabets_word, menu);
		return true;
	}



}
