package com.funkyandroid.launcher2.rulesengine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class NotificationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION.equals(intent.getAction())) {
			Intent serviceIntent = new Intent(context, WifiChangeHandlerService.class);
			serviceIntent.putExtra(
					WifiManager.EXTRA_SUPPLICANT_CONNECTED, 
					intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)
				);
			context.startService(serviceIntent);
		}

	}

}
