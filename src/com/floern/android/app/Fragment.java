/*
 * Floern, dev@floern.com, 2015, MIT Licence
 */
package com.floern.android.app;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A subclass of Fragment with some additional features.
 * <p>
 * {@link #getLayoutInflater()} - Get a LayoutInflater instance.<br>
 * {@link #findViewById(int)} - Look for a child view with the given ID.<br>
 * {@link #findImageViewById(int)} - Look for a child ImageView with the given ID.<br>
 * {@link #findTextViewById(int)} - Look for a child TextView with the given ID.<br>
 * {@link #findEditTextById(int)} - Look for a child EditText with the given ID.<br>
 * {@link #findViewGroupById(int)} - Look for a child ViewGroup with the given ID.<br>
 * {@link #findAbsListViewById(int)} - Look for a child AbsListView with the given ID.<br>
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
	
	
	/**
	 * Get the Activity's LayoutInflater instance.
	 * @return
	 */
	public LayoutInflater getLayoutInflater() {
		return getActivity().getLayoutInflater();
	}
	
	
	/**
	 * Look for a child view with the given ID.
	 * @param id The ID to search for.
	 * @return The view that has the given ID in the hierarchy or null.
	 */
	public View findViewById(int id) {
		return getView().findViewById(id);
	}
	
	
	/**
	 * Look for a child ImageView with the given ID.
	 * @param id The ID to search for.
	 * @return The ImageView that has the given ID in the hierarchy or null.
	 */
	public ImageView findImageViewById(int id) {
		return (ImageView) getView().findViewById(id);
	}
	
	
	/**
	 * Look for a child TextView with the given ID.
	 * @param id The ID to search for.
	 * @return The TextView that has the given ID in the hierarchy or null.
	 */
	public TextView findTextViewById(int id) {
		return (TextView) getView().findViewById(id);
	}
	
	
	/**
	 * Look for a child EditText with the given ID.
	 * @param id The ID to search for.
	 * @return The EditText that has the given ID in the hierarchy or null.
	 */
	public EditText findEditTextById(int id) {
		return (EditText) getView().findViewById(id);
	}
	
	
	/**
	 * Look for a child ViewGroup with the given ID.
	 * @param id The ID to search for.
	 * @return The ViewGroup that has the given ID in the hierarchy or null.
	 */
	public ViewGroup findViewGroupById(int id) {
		return (ViewGroup) getView().findViewById(id);
	}

	
	/**
	 * Look for a child AbsListView with the given ID.
	 * @param id The ID to search for.
	 * @return The AbsListView that has the given ID in the hierarchy or null.
	 */
	public AbsListView findAbsListViewById(int id) {
		return (AbsListView) getView().findViewById(id);
	}

	
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
	final public void onHiddenChanged(boolean hidden) {
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
		if (childFragments != null) {
			for (android.support.v4.app.Fragment childFragment : childFragments) {
				if (childFragment != null) {
					childFragment.onHiddenChanged(hidden);
				}
			}
		}
	}
	

	/**
	 * The Fragment (or the Activity) got visible.<br>
	 * May be overridden by a subclass.
	 */
	public void onShow() {
		// to be overidden
	}
	
	
	/**
	 * The Fragment (or the Activity) has been hidden.<br>
	 * May be overridden by a subclass.
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
			if (fragment instanceof Fragment && fragment.isVisible()) {
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
	public void overrideArgument(String key, String value) {
		getArguments().putString(key, value);
	}
	
	
	/**
	 * Override a integer argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument integer value
	 * @see #getArguments()
	 */
	public void overrideArgument(String key, int value) {
		getArguments().putInt(key, value);
	}
	
	
	/**
	 * Override a long argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument long value
	 * @see #getArguments()
	 */
	public void overrideArgument(String key, long value) {
		getArguments().putLong(key, value);
	}
	
	
	/**
	 * Override a boolean argument of the Fragment.
	 * @param key Argument key
	 * @param value Argument boolean value
	 * @see #getArguments()
	 */
	public void overrideArgument(String key, boolean value) {
		getArguments().putBoolean(key, value);
	}


	/**
	 * Find the Fragment that is directly attached to the Activity.
	 * @return Top level Fragment
	 */
	public android.support.v4.app.Fragment getTopLevelFragment() {
		android.support.v4.app.Fragment fragment = this;
		while (fragment.getParentFragment() != null) {
			fragment = fragment.getParentFragment();
		}
		return fragment;
	}
	
}
