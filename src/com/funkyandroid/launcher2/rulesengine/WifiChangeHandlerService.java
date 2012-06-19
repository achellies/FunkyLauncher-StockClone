package com.funkyandroid.launcher2.rulesengine;


import com.funkyandroid.launcher.R;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;

public class WifiChangeHandlerService extends IntentService {	
	/**
	 * The broadcast intent indicating a change in the wallpaper
	 */
	
	public static final String BROADCAST_WALLPAPER_CHANGED = "com.funkyandroid.launcher2.rulesengine.WALLPAPER_CHANGED";
	
	/**
	 * The wallpapers to use.
	 */
	private static final int	onId = R.drawable.wallpaper_bubblegum,
								offId = R.drawable.wallpaper_escape;
	
	public WifiChangeHandlerService() {
		this("Funky Launcher Wifi Change Handler Service");
	}
	
	public WifiChangeHandlerService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		WifiChangeHandlerService.changeWallpaper(this, intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false));
	}
	
	/**
	 * Change the wallpaper to the connected or unconnected states.
	 * 
	 * @param context The context to get the wallpaper manager from.
	 * @param connected Whether or not we're connected
	 */
	public static void changeWallpaper(final Context context, final boolean connected) {
        int wallpaper;
        if(connected) {
			wallpaper = onId;
		} else {
			wallpaper = offId;
		}
        SharedPreferences ruleBasedPreferences = 
        		context.getSharedPreferences(RulesEnginePreferences.PREFERENCE_STORE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = ruleBasedPreferences.edit();
		prefsEditor.putInt(RulesEnginePreferences.WALLPAPER_PREFERNCE, wallpaper);
		prefsEditor.apply();
		
		Intent broadcast = new Intent(BROADCAST_WALLPAPER_CHANGED);
		broadcast.putExtra(RulesEnginePreferences.WALLPAPER_PREFERNCE, wallpaper);
		context.sendBroadcast(broadcast);
	}
	
}
