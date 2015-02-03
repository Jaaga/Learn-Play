package com.jaaga.learnnplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jaaga.learnnplay.learn.Learn_Levels;
import com.jaaga.learnnplay.paint.PaintMe;
import com.jaaga.learnnplay.quiz.Quiz_Levels;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class MainActivity extends Activity {

	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		
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
	
	/** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
