package com.leoz.bz.zclient;

import java.util.ArrayList;
import java.util.Iterator;

import com.leoz.bz.zbase.ZBaseData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public enum ZCltService {
	INSTANCE;
	
	private static final String TAG = "[z::local] ZCltService"; /// TODO: FIX ME

	private boolean mStarted = false;
	private Activity mA = null;
	private Intent mIntent = null;
	
	private ArrayList<ZCltReceiver> mRecs = new ArrayList<ZCltReceiver>();
	
	public void init(Activity a){
		mA = a;
        mIntent = new Intent(ZBaseData.SERVER_CLASS);
	}
		
	public void addReceiver(ZCltReceiver rec) {
		mRecs.add(rec);
	}
	
	public boolean started(){
		return mStarted;
	}

	public void start(){
    	
    	Log.v(TAG, "start"); /// TODO: FIX ME
		
    	if (mStarted == true){
        	Log.v(TAG, "Service is already started"); /// TODO: FIX ME    		
    	}
    	else {
    		mA.startService(mIntent);  		    	
	    	registerReceivers();	    	
			mStarted = true;
    	}
    }
	
	private void registerReceivers() {
		for (Iterator<ZCltReceiver> i = mRecs.iterator(); i.hasNext();) {
			ZCltReceiver rec = (ZCltReceiver) i.next();
	    	mA.registerReceiver(rec, rec.getFilter(), null, new Handler()); 	
		}		
	}
	
	public void stop(){
    	
    	Log.v(TAG, "stop"); /// TODO: FIX ME
    	
    	if (mStarted == true){
    		unregisterReceivers();    		
    		mA.stopService(mIntent);
			mStarted = false;    		
    	}
    	else {
        	Log.v(TAG, "Service is already stopped"); /// TODO: FIX ME    		    		
    	}
    }
	
	private void unregisterReceivers() {
		for (Iterator<ZCltReceiver> i = mRecs.iterator(); i.hasNext();) {
			ZCltReceiver rec = (ZCltReceiver) i.next();
	    	mA.unregisterReceiver(rec); 	
		}		
	}

	public void bind(){
    	
    	Log.v(TAG, "bind"); /// TODO: FIX ME
    	
		if(!ZCltManager.INSTANCE.connected()) {
			ZCltManager.INSTANCE.connect();
			mA.bindService(mIntent, ZCltManager.INSTANCE.connection(), Context.BIND_AUTO_CREATE);
		} else {
			Log.v(TAG, "Cannot bind - service already bound");
		}
    }
	
	public void release(){
    	
    	Log.v(TAG, "release"); /// TODO: FIX ME
    	
		if(ZCltManager.INSTANCE.connected()) {
			mA.unbindService(ZCltManager.INSTANCE.connection());
			ZCltManager.INSTANCE.disconnect();
		} else {
			Log.v(TAG, "Cannot unbind - service not bound");
		}
    }
}
