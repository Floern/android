/*
 * Floern, dev@floern.com, 2015, MIT Licence
 */
package com.floern.android.app;

/**
 * A subclass of Fragment with some additional features.
 * <p>
 * {@link #onVisible()} - The Fragment has been created or got visible.<br>
 * {@link #onHidden()} - The Fragment has been hidden or removed.
 * 
 * @author Floern
 */
public class Fragment extends android.support.v4.app.Fragment {
	
	
	
	
	@Override
	public void onStart() {
		super.onStart();
		
		if (!isHidden()) {
			onVisible();
		}
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		
		if (!isHidden()) {
			onHidden();
		}
	}
	
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		
		if (!hidden) {
			onVisible();
		}
		else {
			onHidden();
		}
	}
	

	/**
	 * The Fragment has been created or got visible.
	 */
	public void onVisible() {
		// to be overidden
	}
	
	
	/**
	 * The Fragment has been hidden or removed.
	 */
	public void onHidden() {
		// to be overidden
	}
	
	
}
