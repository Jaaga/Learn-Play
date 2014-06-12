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
	Animation animove;
	Animation animbounce;
	
	private static int[] photos = new int[]{R.drawable.a,R.drawable.apple,R.drawable.b,
		R.drawable.ball,R.drawable.c,R.drawable.cat,R.drawable.d,R.drawable.dog,R.drawable.e,
		R.drawable.elephant,R.drawable.f,R.drawable.fan,R.drawable.g,R.drawable.goat,
		R.drawable.h,R.drawable.hen,R.drawable.i,R.drawable.india,R.drawable.j,
		R.drawable.joker,R.drawable.k,R.drawable.kite,R.drawable.l,R.drawable.lion,
		R.drawable.m,R.drawable.monkey,R.drawable.n,R.drawable.nest,R.drawable.o,
		R.drawable.orange,R.drawable.p,R.drawable.parrot,R.drawable.q,R.drawable.queen,
		R.drawable.r,R.drawable.rat,R.drawable.s,R.drawable.snake,R.drawable.t,
		R.drawable.train,R.drawable.u,R.drawable.umbrella,R.drawable.v,R.drawable.van,
		R.drawable.w,R.drawable.watch,R.drawable.x,R.drawable.xmas,R.drawable.y,
		R.drawable.yak,R.drawable.z,R.drawable.zebra};
	
	private static int[] song = new int[]{R.raw.a,R.raw.apple,R.raw.b,R.raw.c,R.raw.d,R.raw.e,
		R.raw.f,R.raw.g,R.raw.h,R.raw.i,R.raw.j,R.raw.k,R.raw.l,R.raw.m,R.raw.n,R.raw.o,
		R.raw.p,R.raw.q,R.raw.r,R.raw.s,R.raw.t,R.raw.u,R.raw.v,R.raw.w,R.raw.x,R.raw.y,
		R.raw.z};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		click=0;
		imagebutton = (ImageButton) findViewById(R.id.imageButton1);
		animove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
		animove.setAnimationListener(this);
		
		//animbounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
		//animbounce.setAnimationListener(this);
		
		for(int i=0;i<27;i++)
		{
			media[i] = MediaPlayer.create(this, song[i]);
		}
		media[0].start();
		imagebutton.startAnimation(animove);
		//imagebutton.startAnimation(animbounce);
		
		imagebutton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				click++;
				Bitmap image = BitmapFactory.decodeResource(getResources(),photos[click]);  
				imagebutton.setImageBitmap(image);
				imagebutton.startAnimation(animove);
				//imagebutton.startAnimation(animbounce);
				
				if(click % 2 == 1)
				{
					media[click-1].stop();
					media[click].start();
				}
				else {
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
		if (animation == animove) {
            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_LONG).show();
        }
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
