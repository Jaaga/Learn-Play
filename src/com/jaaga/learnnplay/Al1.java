package com.jaaga.learnnplay;

import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class Al1 extends Activity {

	MediaPlayer[] media = new MediaPlayer[26];
	private static int click = 0;

	private static int[] photos = new int[] { R.drawable.a, R.drawable.b,
			R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
			R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
			R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
			R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r,
			R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v,
			R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z };

	private static int[] song = new int[] { R.raw.a, R.raw.b, R.raw.c, R.raw.d,
			R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j, R.raw.k,
			R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.p, R.raw.q, R.raw.r,
			R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w, R.raw.x, R.raw.y,
			R.raw.z };

	private Bitmap[] mAlpha = new Bitmap[26];
	private int[] mAlphaHwidth = new int[26];
	private int[] mAlphaHheight = new int[26];
	private Bitmap mBG;
	private float x = 0;
	private float y = 0;
	private float vx = 1;
	private float vy = 1;
	private boolean mTouching;
	private Random rand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		rand = new Random();
		Toast.makeText(this, "Tab to screen 3 times for next Alphabet", Toast.LENGTH_SHORT).show();
		media[click] = MediaPlayer.create(this, song[click]);

		mBG = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		for (int i = 0; i < 26; i++) {
			mAlpha[i] = BitmapFactory.decodeResource(getResources(), photos[i]);
			mAlphaHwidth[i] = mAlpha[i].getWidth() / 2;
			mAlphaHheight[i] = mAlpha[i].getHeight() / 2;
		}

		View v = new View(this) {

			protected void onDraw(Canvas canvas) {
				float ScaleX = this.getWidth() / ((float) mBG.getWidth());
				float ScaleY = this.getHeight() / ((float) mBG.getHeight());

				canvas.save();
				canvas.scale(ScaleX, ScaleY);
				canvas.drawBitmap(mBG, 0, 0, null);

				canvas.restore();

				canvas.translate(x, y);

				if (mTouching) {
					
					vx = 1;
					vy = 1;
					x = 0;
					y = 0;
					media[click].stop();
					media[click].release();
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						Log.e("MainActivity", "EXCEPTION "+e);
					}
					
					click++;
					if (click <= 25) {
						media[click] = MediaPlayer.create(
								getApplicationContext(), song[click]);
						
						canvas.drawBitmap(mAlpha[click], 0, 0, null);
						media[click].start();
						
					} else {
						click = 0;
						media[click] = MediaPlayer.create(
								getApplicationContext(), song[click]);
					}
				} else {
					canvas.drawBitmap(mAlpha[click], 0, 0, null);
					media[click].start();
				}
				
				final int rWidth = rand.nextInt(3);
				final int rHeight = rand.nextInt(3);

				x = x + vx;
				y = y + vy;

				if ((y + 2 * mAlphaHheight[0] - 1 >= this.getHeight())
						|| (y <= -1)) {
					vy = -vy;
				} else {vy = vy + rHeight *0.3f;}

				if ((x + 2 * mAlphaHwidth[0] + 1 >= this.getWidth())
						|| (x <= -1)) {
					vx = -vx;
				} else {vx = vx + rWidth *0.4f;}

				postInvalidateDelayed(2);
			}
		};

		setContentView(v);
		v.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN) {
					mTouching = true;
				}
				if (action == MotionEvent.ACTION_UP
						|| action == MotionEvent.ACTION_CANCEL
						|| action == MotionEvent.ACTION_MOVE) {
					mTouching = false;
				}

				return true;
			}

		});

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		media[click].pause();
		finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		media[click].start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		media[click].pause();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		media[click].stop();
	}
}
