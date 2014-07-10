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
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class NL1 extends Activity {

	MediaPlayer[] media = new MediaPlayer[10];
	private static int click = 0;

	private static int[] photos = new int[] { R.drawable.n1, R.drawable.n2,
			R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6,
			R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10 };

	private static int[] song = new int[] { R.raw.a, R.raw.b, R.raw.c, R.raw.d,
			R.raw.e, R.raw.f, R.raw.g, R.raw.h, R.raw.i, R.raw.j };

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
	private static int next = 0;

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
		for (int i = 0; i < 10; i++) {
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

				if (mTouching && (next % 5 == 0)) 
				{
					vx = 1;
					vy = 1;
					media[click].stop();
					media[click].release();
					click++;
					if (click <= 9) {
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
				} 
				else {vy = vy + rHeight * 0.3f;}

				if ((x + 2 * mAlphaHwidth[0] + 1 >= this.getWidth())
						|| (x <= -1)) {
					vx = -vx;
				} 
				else {vx = vx + rWidth * 0.4f;}

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
					next++;
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
