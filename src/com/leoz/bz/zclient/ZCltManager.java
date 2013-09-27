package com.leoz.bz.zclient;

import com.leoz.bz.zthumb.ZSrvRemoteService;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public enum ZCltManager {
	INSTANCE;
	
	private static final String TAG = "[z::local] ZCltManager"; /// TODO: FIX ME

	private ZSrvRemoteService mService = null;
	private ZCltConnection mConnection = null;

	public void connect(){
    	
    	Log.v(TAG, "connect"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			mConnection = new ZCltConnection();
		} else {
			Log.v(TAG, "Cannot connect - service already connect");
		}
    }

    public void disconnect(){
    	
    	Log.v(TAG, "disconnect"); /// TODO: FIX ME
    	
    	mConnection = null;
    }
    
    public boolean connected(){
    	return (mConnection != null);
    }
    
    public ZCltConnection connection(){
    	return mConnection;
    }

    public void invokeSetDir(String dir){
    	
    	Log.v(TAG, "invokeSetDir"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.setDir(dir);
				Log.d(TAG, "invokeSetDir: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeSetDir: RemoteException");
			}
		}
    }
    
	public void invokeSetSize(int size){
    	
    	Log.v(TAG, "invokeSetSize"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.setSize(size);
				Log.d(TAG, "invokeSetSize: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeSetSize: RemoteException");
			}
		}
    }
    
	public void invokeSetCount(int count){
    	
    	Log.v(TAG, "invokeSetCount"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.setThreads(count);
				Log.d(TAG, "invokeSetCount: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeSetCount: RemoteException");
			}
		}
    }

	public void invokeScanDir(String dir, int[] sizes){
    	
    	Log.v(TAG, "invokeScanDir"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.scanDir(dir, sizes);
				Log.d(TAG, "invokeScanDir: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeScanDir: RemoteException");
			}
		}
    }

	public void invokeScanFile(String file, int size){
    	
    	Log.v(TAG, "invokeScanFile"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.scanFile(file, size);
				Log.d(TAG, "invokeScanFile: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeScanFile: RemoteException");
			}
		}
    }

	public void invokeScanStart(){
    	
    	Log.v(TAG, "invokeScanStart"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.startScan();
				Log.d(TAG, "invokeScanStart: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeScanStart: RemoteException");
			}
		}
    }
    
	public void invokeScanStop(){
    	
    	Log.v(TAG, "invokeScanStop"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.stopScan();
				Log.d(TAG, "invokeScanStop: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeScanStop: RemoteException");
			}
		}
    }
    
	public void invokeClearCache(){
    	
    	Log.v(TAG, "invokeClearCache"); /// TODO: FIX ME
    	
		if(mConnection == null) {
			Log.v(TAG, "Cannot invoke - service not bound");
		} else {
			try {
				mService.clearCache();
				Log.d(TAG, "invokeClearCache: OK");
			} catch (RemoteException re) {
				Log.e(TAG, "invokeClearCache: RemoteException");
			}
		}
    }
	
	class ZCltConnection implements ServiceConnection {
	    public void onServiceConnected(ComponentName className, IBinder boundService ) {
	    	
	    	Log.v(TAG, "onServiceConnected" );
	    	mService = ZSrvRemoteService.Stub.asInterface((IBinder)boundService);
	    }
	
	    public void onServiceDisconnected(ComponentName className) {
	    	
	    	Log.v(TAG, "onServiceDisconnected" );
	    	mService = null;
	    }
	};
}
