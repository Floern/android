/*
 * Floern, dev@floern.com, 2015, MIT Licence
 */
package com.floern.android.app;

import android.support.v7.app.AppCompatActivity;

/**
 * A subclass of Activity with some additional features.
 * <p>
 * Propagates {@link #onBackPressed()} to Fragments.<br>
 * 
 * @author Floern
 */
public class Activity extends AppCompatActivity {
	
	
	@Override
	public void onBackPressed() {
		// propagate the onBackPressed to Fragments
		boolean consumed = Fragment.triggerOnBackPressed(getSupportFragmentManager().getFragments());
		if (consumed) {
			// event has been consumed by a Fragment, prevent default action
			return;
		}
		
		// default action
		super.onBackPressed();
	}
	
	
}
