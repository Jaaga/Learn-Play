package com.jaaga.learnnplay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	ImageButton imagebutton;
	MediaPlayer[] media = new MediaPlayer[26];
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
	private static int click;
	private static int[] song=new int[]{R.raw.a,R.raw.abc};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		click=0;
		
		imagebutton = (ImageButton) findViewById(R.id.imageButton1);
		
		media[1] = MediaPlayer.create(this, song[0]);
		media[2] = MediaPlayer.create(this, song[1]);
		media[1].start();	
		imagebutton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				click++;
				Bitmap image = BitmapFactory.decodeResource(getResources(),photos[click]);  
				imagebutton.setImageBitmap(image);
				if(click % 2 == 1)
				{
					media[1].stop();
					media[2].start();
				}
				else {
					media[2].stop();
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
	
}
