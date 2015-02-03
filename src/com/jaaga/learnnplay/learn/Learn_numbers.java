package com.jaaga.learnnplay.learn;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jaaga.learnnplay.Constants;
import com.jaaga.learnnplay.R;

public class Learn_numbers extends Activity {

	public static int click = 0;
	private MediaPlayer media;
	private ImageView numbers;
	private TextView numberName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn_fruits);
		
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        
		numbers = (ImageView) findViewById(R.id.fruits);
		ImageButton next = (ImageButton) findViewById(R.id.next);
		ImageButton previous = (ImageButton) findViewById(R.id.previous);
		numberName = (TextView) findViewById(R.id.fruitname);
		
		numberSetUp();
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				click++;
				
				if(click >= Constants.numberphotos.length)
					click = 0;
				
				media.stop();
				numberSetUp();
			}
		});
		
		previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				click--;
				
				if(click < 0)
					click = Constants.numberphotos.length - 1;
				
				media.stop();
				numberSetUp();
			}
		});
	}

	private void numberSetUp() {
		numbers.setBackgroundResource(Constants.numberphotos[click]);
		media = MediaPlayer.create(Learn_numbers.this, Constants.numbersound[click]);
		media.start();
		numberName.setText(Constants.numberNames[click]);
	}
}
