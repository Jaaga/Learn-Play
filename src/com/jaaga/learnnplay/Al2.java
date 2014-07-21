package com.jaaga.learnnplay;

import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Al2 extends Activity {

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

	private MediaPlayer media;

	private int[] randomnumber = new int[4];

	private ImageButton resume;

	private ImageButton op1;

	private ImageButton op2;

	private ImageButton op3;

	private ImageButton op4;

	private int position;

	private Random rand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_al2);

		resume = (ImageButton) findViewById(R.id.imageButton1);

		op1 = (ImageButton) findViewById(R.id.imageButton3);
		op2 = (ImageButton) findViewById(R.id.ImageButton4);
		op3 = (ImageButton) findViewById(R.id.ImageButton5);
		op4 = (ImageButton) findViewById(R.id.ImageButton6);

		rand = new Random();
		shuffle();

		op1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 0) {
					
					media = MediaPlayer.create(getApplicationContext(), R.raw.clap);
					media.start();

					media.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					shuffle();
				} else {

				}
			}
		});

		op2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 1) {
					
					media = MediaPlayer.create(getApplicationContext(), R.raw.clap);
					media.start();

					media.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					shuffle();
				} else {

				}
			}
		});

		op3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 2) {
					
					media = MediaPlayer.create(getApplicationContext(), R.raw.clap);
					media.start();

					media.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					shuffle();
				} else {

				}
			}
		});

		op4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 3) {
					
					media = MediaPlayer.create(getApplicationContext(), R.raw.clap);
					media.start();

					media.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							mp.release();
						}
					});
					shuffle();
				} else {

				}
			}
		});
	}

	private void shuffle() {
		try {
			Thread.sleep(1020);
		} catch (InterruptedException e) {
			Log.e("MainActivity", "Exception "+e);
		}
		randomnumber[0] = rand.nextInt(photos.length);

		randomnumber[1] = rand.nextInt(photos.length);
		while (randomnumber[1] == randomnumber[0])
			randomnumber[1] = rand.nextInt(photos.length);

		randomnumber[2] = rand.nextInt(photos.length);
		while ((randomnumber[2] == randomnumber[0])
				|| (randomnumber[2] == randomnumber[1]))
			randomnumber[2] = rand.nextInt(photos.length);

		randomnumber[3] = rand.nextInt(photos.length);
		while ((randomnumber[3] == randomnumber[0])
				|| (randomnumber[3] == randomnumber[1])
				|| (randomnumber[3] == randomnumber[2]))
			randomnumber[3] = rand.nextInt(photos.length);

		position = rand.nextInt(4);

		media = MediaPlayer.create(getApplicationContext(), song[randomnumber[position]]);
		media.start();

		media.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
		
		resume.setBackgroundResource(android.R.drawable.ic_media_play);
		resume.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.setBackgroundResource(android.R.drawable.ic_media_pause);
				
				media = MediaPlayer.create(getApplicationContext(), song[randomnumber[position]]);
				media.start();
				
				media.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						resume.setBackgroundResource(android.R.drawable.ic_media_play);
						mp.release();
					}
				});
			}
		});
		
		op1.setBackgroundResource(photos[randomnumber[0]]);
		op2.setBackgroundResource(photos[randomnumber[1]]);
		op3.setBackgroundResource(photos[randomnumber[2]]);
		op4.setBackgroundResource(photos[randomnumber[3]]);
	}

	@Override
	protected void onResume() {
		super.onResume();
		media.start();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
