package com.jaaga.learnnplay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements AnimationListener {

	ImageButton imagebutton;
	MediaPlayer[] media = new MediaPlayer[52];
	private static int click;
	Animation animbounce;
	Animation animcomb;
	
	private static int[] photos = new int[]{R.drawable.a,R.drawable.apple,R.drawable.b,
		R.drawable.ball,R.drawable.c,R.drawable.cat,R.drawable.d,R.drawable.dog,R.drawable.e,
		R.drawable.egg,R.drawable.f,R.drawable.flower,R.drawable.g,R.drawable.glasses,
		R.drawable.h,R.drawable.hat,R.drawable.i,R.drawable.icecream,R.drawable.j,
		R.drawable.juice,R.drawable.k,R.drawable.key,R.drawable.l,R.drawable.ladder,
		R.drawable.m,R.drawable.milk,R.drawable.n,R.drawable.nose,R.drawable.o,
		R.drawable.orange,R.drawable.p,R.drawable.pen,R.drawable.q,R.drawable.quilt,
		R.drawable.r,R.drawable.rabbit,R.drawable.s,R.drawable.shoes,R.drawable.t,
		R.drawable.truck,R.drawable.u,R.drawable.umbrella,R.drawable.v,R.drawable.vacuum,
		R.drawable.w,R.drawable.worm,R.drawable.x,R.drawable.xylophone,R.drawable.y,
		R.drawable.yoghurt,R.drawable.z,R.drawable.zebra};
	
	private static int[] song = new int[]{R.raw.a,R.raw.apple,R.raw.b,R.raw.ball,R.raw.c,R.raw.cat,
		R.raw.d,R.raw.dog,R.raw.e,R.raw.egg,R.raw.f,R.raw.flower,R.raw.g,R.raw.glasses,
		R.raw.h,R.raw.hat,R.raw.i,R.raw.icecream,R.raw.j,R.raw.juice,
		R.raw.k,R.raw.key,R.raw.l,R.raw.ladder,R.raw.m,R.raw.milk,R.raw.n,R.raw.nose,
		R.raw.o,R.raw.orange,R.raw.p,R.raw.pen,R.raw.q,R.raw.quilt,R.raw.r,R.raw.rabbit,
		R.raw.s,R.raw.shoes,R.raw.t,R.raw.truck,R.raw.u,R.raw.umbrella,R.raw.v,R.raw.vacuum,
		R.raw.w,R.raw.worm,R.raw.x,R.raw.xylophone,R.raw.y,R.raw.yoghurt,R.raw.z,R.raw.zebra};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		click=0;
		imagebutton = (ImageButton) findViewById(R.id.imageButton1);
		animcomb = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.comb);
		animcomb.setAnimationListener(this);
		
		animbounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
		animbounce.setAnimationListener(this);
		
		for(int i=0;i<52;i++)
		{
			media[i] = MediaPlayer.create(this, song[i]);
		}
		media[0].start();
		imagebutton.startAnimation(animcomb);
		
		imagebutton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				click++;
				Bitmap image = BitmapFactory.decodeResource(getResources(),photos[click]);  
				imagebutton.setImageBitmap(image);
			    				
				if(click % 2 == 1)
				{
				    imagebutton.startAnimation(animbounce);
					media[click-1].stop();
					media[click].start();
				}
				else {
					imagebutton.startAnimation(animcomb);
					media[click-1].stop();
					media[click].start();
				}
			}
		});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	
}
