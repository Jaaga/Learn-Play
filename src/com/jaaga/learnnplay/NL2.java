package com.jaaga.learnnplay;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class NL2 extends Activity implements AnimationListener{

	ImageButton imagebutton;
	private static int click;
	Animation animcomb;
	
	private static int[] photos = new int[]{R.drawable.n0,R.drawable.n1,
		R.drawable.n2,R.drawable.n3,R.drawable.n4,R.drawable.n5,R.drawable.n6,
		R.drawable.n7,R.drawable.n8,R.drawable.n9};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alphabets_word);
		click=0;
		imagebutton = (ImageButton) findViewById(R.id.imageButton1);
		imagebutton.setBackgroundResource(photos[click]);
		
		animcomb = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.comb);
		animcomb.setAnimationListener(this);
		
		imagebutton.startAnimation(animcomb);
		
		imagebutton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				click++;
				if(click < 9)
				{
				imagebutton.setBackgroundResource(photos[click]);
				//Bitmap image = BitmapFactory.decodeResource(getResources(),photos[click]);  
				//imagebutton.setImageBitmap(image);
				imagebutton.startAnimation(animcomb);
				}
				else{
					click = -1;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.numbers, menu);
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
