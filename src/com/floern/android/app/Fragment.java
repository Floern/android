/*
 * Floern, dev@floern.com, 2015, MIT Licence
 */
package com.floern.android.app;

import java.util.List;

/**
 * A subclass of Fragment with some additional features.
 * <p>
 * {@link #onShow()} - The Fragment has been created or got visible.<br>
 * {@link #onHide()} - The Fragment has been hidden or removed.<br>
 * {@link #onBackPressed()} - The User pressed the back button.<br>
 * {@link #overrideArgument(String, String)} - Override a Fragment's String argument.<br>
 * {@link #overrideArgument(String, int)} - Override a Fragment's int argument.<br>
 * {@link #overrideArgument(String, long)} - Override a Fragment's long argument.<br>
 * {@link #overrideArgument(String, boolean)} - Override a Fragment's boolean argument.<br>
 * <p>
 * Propagates {@link #onHiddenChanged(boolean)} to child Fragments.<br>
 * Propagates {@link #onBackPressed()} to child Fragments.<br>
 * 
 * @author Floern
 */
public class Fragment extends android.support.v4.app.Fragment {
	
	
	@Override
	public void onStart() {
		super.onStart();

		// invoke visibility callback method
		if (!isHidden()) {
			onShow();
		}
	}
	
	
	@Override
	public void onStop() {
		super.onStop();

		// invoke visibility callback method
		if (!isHidden()) {
			onHide();
		}
	}
	
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		
		// invoke visibility callback method
		if (!hidden) {
			onShow();
		}
		else {
			onHide();
		}

		// propagate the hidden state change down to child Fragments
		List<android.support.v4.app.Fragment> childFragments = getChildFragmentManager().getFragments();
		for (android.support.v4.app.Fragment childFragment : childFragments) {
			childFragment.onHiddenChanged(hidden);
		}
	}
	

	/**
	 * The Fragment has been created or got visible.
	 */
	public void onShow() {
		// to be overidden
	}
	
	
	/**
	 * The Fragment has been hidden or removed.
	 */
	public void onHide() {
		// to be overidden
	}
	
	
	/**
	 * Called when the Activity has detected the user's press of the back key.<br>
	 * It's recommended only to do something (i.e. handle the event) if the super implementation returns false. 
	 * @return true if the event has been consumed, false otherwise.
	 */
	protected boolean onBackPressed() {
		// propagate the onBackPressed to child Fragments
		return triggerOnBackPressed(getChildFragmentManager().getFragments());
	}
	
	
	/**
	 * Interate a list of active Fragments and invoke their {@link #onBackPressed()} event callback.
	 * @param fragments
	 * @return true if a Fragment consumed the event, false otherwise.
	 */
	protected static boolean triggerOnBackPressed(List<android.support.v4.app.Fragment> fragments) {
		if (fragments == null) {
			return false;
		}
		
		// iterate visible Fragments
		for (android.support.v4.app.Fragment fragment : fragments) {
			if (fragment.isVisible() && fragment instanceof Fragment) {
				boolean consumed = ((Fragment)fragment).onBackPressed();
				if (consumed) return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Override a String argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument String value
	 * @see #getArguments()
	 */
	protected void overrideArgument(String key, String value) {
		getArguments().putString(key, value);
	}
	
	
	/**
	 * Override a integer argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument integer value
	 * @see #getArguments()
	 */
	protected void overrideArgument(String key, int value) {
		getArguments().putInt(key, value);
	}
	
	
	/**
	 * Override a long argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument long value
	 * @see #getArguments()
	 */
	protected void overrideArgument(String key, long value) {
		getArguments().putLong(key, value);
	}
	
	
	/**
	 * Override a boolean argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument boolean value
	 * @see #getArguments()
	 */
	protected void overrideArgument(String key, boolean value) {
		getArguments().putBoolean(key, value);
	}
	
	
}
