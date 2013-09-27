package com.leoz.bz.zclient;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public abstract class ZCltReceiver extends BroadcastReceiver {
	
	public abstract IntentFilter getFilter();
}
