package com.funkyandroid.launcher2.utils;

import java.lang.reflect.Method;

import com.android.launcher2.Launcher;

import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.util.Log;

public final class ResourceUtils {

	/**
	 * getCompatibiltyInfo method
	 */
	
	private static Method sGetCompatibilityInfo;
	static {
		try {
			Resources.class.getDeclaredMethod("getCompatibilityInfo", (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			Log.e(Launcher.TAG, "Problem getting compatibility info method", e);
		}
	}
	
	
	private ResourceUtils() {
		super();
	}
	
	/**
     * Return the compatibility mode information for the application.
     * The returned object should be treated as read-only.
     * 
     * @return compatibility info.
     * @hide
     */
    public static CompatibilityInfo getCompatibilityInfo(final Resources resources) {
    	try {
    		return (CompatibilityInfo) sGetCompatibilityInfo.invoke(resources, (Object[]) null);
		} catch (Exception e) {
			Log.e(Launcher.TAG, "Problem getting compatibility info", e);
			return null;
		}
    }
}
