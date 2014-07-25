package com.jaaga.learnnplay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Learn_Fruits extends Activity {

	public static int click = 0;
	private MediaPlayer media;
	private ImageView fruits;
	private TextView fruitName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn_fruits);
		fruits = (ImageView) findViewById(R.id.fruits);
		ImageButton next = (ImageButton) findViewById(R.id.next);
		ImageButton previous = (ImageButton) findViewById(R.id.previous);
		fruitName = (TextView) findViewById(R.id.fruitname);
		
		fruitSetUp();
		
		fruitName.setText(Constants.fruitNames[click]);
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				click++;
				
				if(click >= Constants.fruitsPhotos.length)
					click = 0;
				
				media.stop();
				fruitSetUp();
			}
		});
		
		previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				click--;
				
				if(click < 0)
					click = Constants.fruitsPhotos.length - 1;
				
				media.stop();
				fruitSetUp();
			}
		});
	}
	
	private void fruitSetUp()
	{
		fruits.setBackgroundResource(Constants.fruitsPhotos[click]);
		
		media = MediaPlayer.create(Learn_Fruits.this, Constants.fruitsSound[click]);
		media.start();
		fruitName.setText(Constants.fruitNames[click]);
	}
	
}
