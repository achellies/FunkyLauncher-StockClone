package com.funkyandroid.launcher2.rulesengine;

import java.io.IOException;

import com.android.launcher2.Launcher;
import com.funkyandroid.launcher.R;

import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiChangeHandlerService extends IntentService {
	
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
		WallpaperManager wpm = (WallpaperManager)context.getSystemService(Context.WALLPAPER_SERVICE);
		try {
			if(connected) {
				wpm.setResource(onId);
			} else {
				wpm.setResource(offId);
			}
		} catch(IOException ioe) {
			Log.e(Launcher.TAG, "Unable to change wallpaper", ioe);
		}		
	}

}
