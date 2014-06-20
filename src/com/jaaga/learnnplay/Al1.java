package com.jaaga.learnnplay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Al1 extends Activity implements AnimationListener{

	ImageButton imagebutton;
	MediaPlayer[] media = new MediaPlayer[26];
	private static int click;
	Animation animcomb;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alphabets_word);
		click=0;
		imagebutton = (ImageButton) findViewById(R.id.imageButton1);
		imagebutton.setBackgroundResource(photos[click]);
		
		animcomb = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.comb);
		animcomb.setAnimationListener(this);
		
		for(int i=0;i<26;i++)
		{
			media[i] = MediaPlayer.create(this, song[i]);
		}
		media[0].start();
		media[0].setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.reset();
				mp.release();
			}
		});
		imagebutton.startAnimation(animcomb);
		
		imagebutton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				click++;
				imagebutton.setBackgroundResource(photos[click]);
				//Bitmap image = BitmapFactory.decodeResource(getResources(),photos[click]);  
				//imagebutton.setImageBitmap(image);
				imagebutton.startAnimation(animcomb);
			    				
				media[click].start();
				media[click].setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.reset();
						mp.release();
					}
				});
				
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

}
