package com.funkyandroid.launcher2.utils;

import java.lang.reflect.Method;

import com.android.launcher2.Launcher;

import android.app.SearchManager;
import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

public final class SearchManagerUtils {

	/**
	 * The method to get the global search activity
	 */
	
	private static Method sGetGlobalSearchActivityMethod;
	static {
		try {
			sGetGlobalSearchActivityMethod = SearchManager.class.getDeclaredMethod("getGlobalSearchActivity", (Class<?>[])null );
		} catch (NoSuchMethodException e) {
			Log.e(Launcher.TAG, "Unable to get getGlobalSearchActivity from SearchManager", e);
		}
	}
	
	/**
	 * Specialised startSearch method
	 */
	
	private static Method sStartSearchMethod;
	static {
		try {
			Class<?>[] methodParams = {String.class, boolean.class, ComponentName.class, Bundle.class, boolean.class, Rect.class };
			sStartSearchMethod = SearchManager.class.getDeclaredMethod("startSearch", methodParams);
		} catch(NoSuchMethodException e) {
			Log.e(Launcher.TAG, "Unable to get getGlobalSearchActivity from SearchManager", e);			
		}
	}
	
	
	
	private SearchManagerUtils() {
		super();
	}
	
    /**
     * Gets the name of the global search activity.
     * 
     * @param sManager The searchManager instance to use to get the information.
     */
	
	public static final ComponentName getGlobalSearchActivity(SearchManager sManager) {
		try {
			return (ComponentName) sGetGlobalSearchActivityMethod.invoke(sManager, (Object[]) null);
		} catch(Exception ex) {
			Log.e(Launcher.TAG, "Unable to get global search activity.", ex);
			return null;
		}
	}

    /**
     * As {@link #startSearch(String, boolean, ComponentName, Bundle, boolean)} but including
     * source bounds for the global search intent.
     */
    public static void startSearch(SearchManager sManager,
    						String initialQuery,
                            boolean selectInitialQuery,
                            ComponentName launchActivity,
                            Bundle appSearchData,
                            boolean globalSearch,
                            Rect sourceBounds) {
		try {
			Object[] params = { initialQuery, selectInitialQuery, launchActivity, appSearchData, globalSearch, sourceBounds };
			sStartSearchMethod.invoke(sManager, params);
		} catch(Exception ex) {
			Log.e(Launcher.TAG, "Unable to start search.", ex);
		}
    }
}
