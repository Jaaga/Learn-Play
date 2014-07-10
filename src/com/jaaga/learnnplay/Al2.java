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

public class Al2 extends Activity {

	MediaPlayer[] media = new MediaPlayer[26];
	private static int click = 0;

	private static int[] photos = new int[] { R.drawable.apple,
			R.drawable.ball, R.drawable.cat, R.drawable.dog, R.drawable.egg,
			R.drawable.flower, R.drawable.glasses, R.drawable.hat,
			R.drawable.icecream, R.drawable.juice, R.drawable.key,
			R.drawable.ladder, R.drawable.milk, R.drawable.nose,
			R.drawable.orange, R.drawable.pen, R.drawable.quilt,
			R.drawable.rabbit, R.drawable.shoes, R.drawable.truck,
			R.drawable.umbrella, R.drawable.vacuum, R.drawable.worm,
			R.drawable.xylophone, R.drawable.yoghurt, R.drawable.zebra };

	private static int[] song = new int[] { R.raw.apple, R.raw.ball, R.raw.cat,
			R.raw.dog, R.raw.egg, R.raw.flower, R.raw.glasses, R.raw.hat,
			R.raw.icecream, R.raw.juice, R.raw.key, R.raw.ladder, R.raw.milk,
			R.raw.nose, R.raw.orange, R.raw.pen, R.raw.quilt, R.raw.rabbit,
			R.raw.shoes, R.raw.truck, R.raw.umbrella, R.raw.vacuum, R.raw.worm,
			R.raw.xylophone, R.raw.yoghurt, R.raw.zebra };

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		media[click] = MediaPlayer.create(this, song[click]);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		final int width = size.x;
		final int height = size.y;

		mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		mBG = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		for (int i = 0; i < 26; i++) {
			mAlpha[i] = BitmapFactory.decodeResource(getResources(), photos[i]);
			mAlphaHwidth[i] = mAlpha[i].getWidth() / 2;
			mAlphaHheight[i] = mAlpha[i].getHeight() / 2;
		}

		c = new Canvas(mBitmap);
		p = new Paint();

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
					media[click].stop();
					media[click].release();
					click++;
					if (click <= 25) {
						media[click] = MediaPlayer.create(
								getApplicationContext(), song[click]);
						
						canvas.drawBitmap(mAlpha[click], 0, 0, null);
						media[click].start();
						media[click]
								.setOnCompletionListener(new OnCompletionListener() {

									@Override
									public void onCompletion(MediaPlayer mp) {
										mp.reset();
									}
								});
					} else {
						click = 0;
						media[click] = MediaPlayer.create(
								getApplicationContext(), song[click]);
					}
				} else {
					
					canvas.drawBitmap(mAlpha[click], 0, 0, null);
					media[click].start();
					media[click]
							.setOnCompletionListener(new OnCompletionListener() {

								@Override
								public void onCompletion(MediaPlayer mp) {
									mp.reset();
								}
							});
				}

				Random rw = new Random();
				final int rWidth = rw.nextInt(3);

				Random rh = new Random();
				final int rHeight = rh.nextInt(3);
				
				x = x + vx;
				y = y + vy;

				if ((y + 2 * mAlphaHheight[0] - 1 >= this.getHeight())
						|| (y <= -1)) {
					vy = -vy;
				} else {vy = vy + rHeight * 0.4f;}

				if ((x + 2 * mAlphaHwidth[0] + 1 >= this.getWidth())
						|| (x <= -1)) {
					vx = -vx;
				} else {vx = vx + rWidth *0.5f;}

				postInvalidateDelayed(2);
			}
		};

		setContentView(v);
		v.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				Log.d(TAG, "on touch!" + event);
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
