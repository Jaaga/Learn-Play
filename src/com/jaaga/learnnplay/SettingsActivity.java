package com.jaaga.learnnplay;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			oldDevices();
		} 
		else {
			showpreferencesFragmentStyle(savedInstanceState);
		}
	}

	private void showpreferencesFragmentStyle(Bundle savedInstanceState) {
		
		if (savedInstanceState == null) 
		{
			FragmentTransaction trans = getFragmentManager().beginTransaction();
			Fragment fragment = new MyPreferencesFragment();
			trans.replace(android.R.id.content, fragment);
			trans.commit();
		}
	}

	@SuppressWarnings("deprecation")
	private void oldDevices() {
		addPreferencesFromResource(R.xml.setting);
	}

	public static class MyPreferencesFragment extends PreferenceFragment {

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.addPreferencesFromResource(R.xml.setting);
			return super.onCreateView(inflater, container, savedInstanceState);
		}
	}
}
