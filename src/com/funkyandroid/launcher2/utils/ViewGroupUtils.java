package com.funkyandroid.launcher2.utils;

import java.lang.reflect.Method;

import com.android.launcher2.Launcher;

import android.util.Log;
import android.view.ViewGroup;

public final class ViewGroupUtils {

    /**
     * Used to access ViewGroup method hidden by the SDK.
     */
    private static Method sSetChildLayersEnabledMethod;
    static {
    	Class<?>[] methodParams = { boolean.class };
    	try {
    		sSetChildLayersEnabledMethod = ViewGroup.class.getDeclaredMethod("setChildrenLayersEnabled", methodParams );
    	} catch(NoSuchMethodException e) {
    		Log.e("Funky Launcher", "Unable to find childLayersEnabled set method", e);
    		sSetChildLayersEnabledMethod = null;
    	}
    }
    
	private ViewGroupUtils() {
		super();
	}
	
	/**
     * Return the compatibility mode information for the application.
     * The returned object should be treated as read-only.
     * 
     * @return compatibility info.
     * @hide
     */
    public static void setChildLayersEnabled(final ViewGroup viewGroup, final boolean enabled) {
    	try {
    		Object[] params = {enabled};
    		sSetChildLayersEnabledMethod.invoke(viewGroup, params);
		} catch (Exception e) {
			Log.e(Launcher.TAG, "Problem getting compatibility info", e);
		}
    }
}
