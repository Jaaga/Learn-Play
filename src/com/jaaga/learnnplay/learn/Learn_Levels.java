package com.jaaga.learnnplay.learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.jaaga.learnnplay.R;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class Learn_Levels extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn);
		
		AdBuddiz.showAd(this);
		
		ImageButton alphabets = (ImageButton) findViewById(R.id.Quiz_alphabets);
		ImageButton numbers = (ImageButton) findViewById(R.id.Quiz_fruits);
		ImageButton fruits = (ImageButton) findViewById(R.id.Learn_fruits);
		
		alphabets.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Learn_alphabets.class);
				startActivity(intent);
			}
		});
		
		numbers.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Learn_numbers.class);
				startActivity(intent);
			}
		});
		
		fruits.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Learn_Fruits.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
