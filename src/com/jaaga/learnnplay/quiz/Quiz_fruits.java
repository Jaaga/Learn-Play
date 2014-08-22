package com.jaaga.learnnplay.quiz;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jaaga.learnnplay.Constants;
import com.jaaga.learnnplay.R;

public class Quiz_fruits extends Activity {
	private ImageView fruit;
	private EditText fruitName;
	Random rand;
	private MediaPlayer media;
	public static int randomNumber = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_fruits);
		
		fruit = (ImageView) findViewById(R.id.fruit_image);
		ImageButton next = (ImageButton) findViewById(R.id.next_fruit);
		fruitName = (EditText) findViewById(R.id.editText1);
		rand = new Random();
		
		fruitSetUp();
		
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String Name = fruitName.getText().toString().trim();
				Name = Name.toUpperCase(Locale.US);
				if(Name.equals(Constants.fruitNames[randomNumber].toUpperCase(Locale.US))){
					media = MediaPlayer.create(Quiz_fruits.this, Constants.fruitsSound[randomNumber]);
					media.start();
					fruitName.setText(null);
				}
				else{
					media = MediaPlayer.create(Quiz_fruits.this, R.raw.wrong);
					media.start();
					fruitName.setText(null);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Fruit Quiz", e.getMessage());
				}
				fruitSetUp();
			}
		});
	}
	
	private void fruitSetUp()
	{
		randomNumber = rand.nextInt(Constants.fruitsPhotos.length);
		fruit.setBackgroundResource(Constants.fruitsPhotos[randomNumber]);
	}
	
}

