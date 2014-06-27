package com.jaaga.learnnplay;

import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class Al1 extends Activity implements AnimationListener, OnSharedPreferenceChangeListener{

	MediaPlayer[] media = new MediaPlayer[26];
	private static int click = 0;
	
	private static int[] photos = new int[]{R.drawable.a,R.drawable.b,
		R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,
		R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,
		R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,
		R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,
		R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z};
	
	private static int[] song = new int[]{R.raw.a,R.raw.b,R.raw.c,R.raw.d,R.raw.e,
		R.raw.f,R.raw.g,R.raw.h,R.raw.i,R.raw.j,R.raw.k,R.raw.l,R.raw.m,R.raw.n,
		R.raw.o,R.raw.p,R.raw.q,R.raw.r,R.raw.s,R.raw.t,R.raw.u,R.raw.v,R.raw.w,
		R.raw.x,R.raw.y,R.raw.z};
	
    private Bitmap mBitmap;
	private Bitmap[] mAlpha = new Bitmap[26];
	private int[] mAlphaHwidth = new int[26];
	private int[] mAlphaHheight = new int[26];
	private Bitmap mBG;
	private Paint p;
	private float x = 0;
	private float y = 0;
	private float vx = 1;
	private float vy = 1;
	private Canvas c;
	private static final String TAG = "MainActivity";
	private boolean mTouching;
	private SharedPreferences mprefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		for(int i=0;i<26;i++)
		{
			media[i] = MediaPlayer.create(this, song[i]);
		}
		Random rw = new Random();
		final int rWidth = rw.nextInt(10);
		
		Random rh = new Random();
		final int rHeight = rh.nextInt(10);
		
		mprefs = PreferenceManager.getDefaultSharedPreferences(this);
		mprefs.registerOnSharedPreferenceChangeListener(this);
		onSharedPreferenceChanged(null, null);
        mBitmap = Bitmap.createBitmap(200, 200,Bitmap.Config.ARGB_8888);
        
        mBG = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        for(int i=0;i<26;i++)
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
        		
        		//canvas.scale(ScaleX*12, ScaleY*3);
        		canvas.restore();
        		
        		float angle = SystemClock.uptimeMillis()/ 5.0f;
        		canvas.translate(x, y);

        		if(mTouching)
        		{
        			click++;
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
        		else
        		{
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
        		
        		if((y + 2*mAlphaHheight[0] -1 >= this.getHeight()) ||(y <= -1))
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
        		
        		if((x + 2*mAlphaHwidth[0] +vx >= this.getWidth()) || (x <= -1))
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
				float Scalex = mBitmap.getWidth() / (float)v.getWidth();
		        float Scaley = mBitmap.getHeight() / (float)v.getHeight();
		        float Pointx = event.getX() * Scalex;
		        float Pointy = event.getY() * Scaley;
		        p.setColor(0xff0000ff);
		        c.drawCircle(Pointx, Pointy, 2, p);
		        return true;
			}
			
		});
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alphabets_word, menu);
		return true;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		boolean mEnableGravity = mprefs.getBoolean("gravity", true);
		
	}

}
