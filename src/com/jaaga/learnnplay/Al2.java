package com.jaaga.learnnplay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Al2 extends Activity implements AnimationListener{

	ImageButton imagebutton;
	MediaPlayer[] media = new MediaPlayer[26];
	private static int click;
	Animation animcomb;
	
	private static int[] photos = new int[]{R.drawable.apple,R.drawable.ball,
		R.drawable.cat,R.drawable.dog,R.drawable.egg,R.drawable.flower,R.drawable.glasses,
		R.drawable.hat,R.drawable.icecream,R.drawable.juice,R.drawable.key,
		R.drawable.ladder,R.drawable.milk,R.drawable.nose,R.drawable.orange,
		R.drawable.pen,R.drawable.quilt,R.drawable.rabbit,R.drawable.shoes,
		R.drawable.truck,R.drawable.umbrella,R.drawable.vacuum,R.drawable.worm,
		R.drawable.xylophone,R.drawable.yoghurt,R.drawable.zebra};
	
	private static int[] song = new int[]{R.raw.apple,R.raw.ball,R.raw.cat,R.raw.dog,
		R.raw.egg,R.raw.flower,R.raw.glasses,R.raw.hat,R.raw.icecream,R.raw.juice,
		R.raw.key,R.raw.ladder,R.raw.milk,R.raw.nose,R.raw.orange,R.raw.pen,
		R.raw.quilt,R.raw.rabbit,R.raw.shoes,R.raw.truck,R.raw.umbrella,R.raw.vacuum,
		R.raw.worm,R.raw.xylophone,R.raw.yoghurt,R.raw.zebra};
	
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
		imagebutton.startAnimation(animcomb);
		
		imagebutton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				click++;
				imagebutton.setBackgroundResource(photos[click]);
				imagebutton.startAnimation(animcomb);
			    				
				if(click % 2 == 1 && click < 26)
				{
					media[click-1].release();
					media[click].start();
				}
				else if(click % 2 == 0 && click < 26) {
					media[click-1].release();
					media[click].start();
				}
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
