package com.jaaga.learnnplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.jaaga.learnnplay.learn.Learn_Levels;
import com.jaaga.learnnplay.paint.PaintMe;
import com.jaaga.learnnplay.quiz.Quiz_Levels;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AdBuddiz.setPublisherKey("5f1960ce-e58e-499f-9487-1bce789a5fd0");
	    AdBuddiz.cacheAds(this);

		ImageButton learn = (ImageButton) findViewById(R.id.resume);
		ImageButton quiz = (ImageButton) findViewById(R.id.Quiz_fruits);
		ImageButton paint = (ImageButton) findViewById(R.id.option1);

		learn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Learn_Levels.class);
				startActivity(intent);
			}
		});

		quiz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Quiz_Levels.class);
				startActivity(intent);
			}
		});

		paint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), PaintMe.class);
				startActivity(intent);
			}
		});

	}
}
