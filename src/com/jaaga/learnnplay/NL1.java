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

public class NL1 extends Activity {

	MediaPlayer[] media = new MediaPlayer[10];
	private static int click = 0;

	private static int[] photos = new int[] { R.drawable.n1, R.drawable.n2,
			R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6,
			R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10 };

	private static int[] song = new int[] { R.raw.n1, R.raw.n2, R.raw.n3,
			R.raw.n4, R.raw.n5, R.raw.n6, R.raw.n7, R.raw.n8, R.raw.n9,
			R.raw.n10 };

	private Bitmap[] mAlpha = new Bitmap[10];
	private int[] mAlphaHwidth = new int[10];
	private int[] mAlphaHheight = new int[10];
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
		Toast.makeText(this, "Tab to screen 3 times for next Alphabet",
				Toast.LENGTH_SHORT).show();
		media[click] = MediaPlayer.create(this, song[click]);

		mBG = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		for (int i = 0; i < 10; i++) {
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
					if (click <= 9) {
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
				} else {
					vy = vy + rHeight * 0.3f;
				}

				if ((x + 2 * mAlphaHwidth[0] + 1 >= this.getWidth())
						|| (x <= -1)) {
					vx = -vx;
				} else {
					vx = vx + rWidth * 0.4f;
				}

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
	

}
