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

public class Quiz_alphabets extends Activity {

	private static ImageButton resume;

	private static ImageButton op1;

	private static ImageButton op2;

	private static ImageButton op3;

	private static ImageButton op4;

	private static int position;
	private static int[] randomnumber = new int[4];
	private static Random rand;
	private static MediaPlayer media;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_alphabets);

		resume = (ImageButton) findViewById(R.id.resume);

		op1 = (ImageButton) findViewById(R.id.option1);
		op2 = (ImageButton) findViewById(R.id.option2);
		op3 = (ImageButton) findViewById(R.id.option3);
		op4 = (ImageButton) findViewById(R.id.option4);

		rand = new Random();
		
		shuffle();

		op1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 0) {
					clapmedia();
					shuffle();
				} else {

				}
			}
		});

		op2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 1) {
					clapmedia();
					shuffle();
				} else {

				}
			}
		});

		op3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 2) {
					clapmedia();
					shuffle();
				} else {

				}
			}
		});

		op4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 3) {
					clapmedia();
					shuffle();
				} else {

				}
			}
		});
	}
	
	private void shuffle() 
	{
		
		try {
			Thread.sleep(1020);
		} catch (InterruptedException e) {
			Log.e("Quiz", "Exception "+e);
		}
		
		randomnumber[0] = rand.nextInt(Constants.alphabetphotos.length);

		randomnumber[1] = rand.nextInt(Constants.alphabetphotos.length);
		while (randomnumber[1] == randomnumber[0])
			randomnumber[1] = rand.nextInt(Constants.alphabetphotos.length);

		randomnumber[2] = rand.nextInt(Constants.alphabetphotos.length);
		while ((randomnumber[2] == randomnumber[0])
				|| (randomnumber[2] == randomnumber[1]))
			randomnumber[2] = rand.nextInt(Constants.alphabetphotos.length);

		randomnumber[3] = rand.nextInt(Constants.alphabetphotos.length);
		while ((randomnumber[3] == randomnumber[0])
				|| (randomnumber[3] == randomnumber[1])
				|| (randomnumber[3] == randomnumber[2]))
			randomnumber[3] = rand.nextInt(Constants.alphabetphotos.length);

		position = rand.nextInt(4);

		media = MediaPlayer.create(Quiz_alphabets.this, Constants.alphabetsound[randomnumber[position]]);
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
				
				media = MediaPlayer.create(Quiz_alphabets.this, Constants.alphabetsound[randomnumber[position]]);
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
		
		op1.setBackgroundResource(Constants.alphabetphotos[randomnumber[0]]);
		op2.setBackgroundResource(Constants.alphabetphotos[randomnumber[1]]);
		op3.setBackgroundResource(Constants.alphabetphotos[randomnumber[2]]);
		op4.setBackgroundResource(Constants.alphabetphotos[randomnumber[3]]);
	}
	
	
	private void clapmedia() {
		media = MediaPlayer.create(Quiz_alphabets.this, R.raw.clap);
		media.start();

		media.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
	}

}
