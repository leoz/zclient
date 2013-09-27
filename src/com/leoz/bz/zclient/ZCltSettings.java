package com.leoz.bz.zclient;

import com.leoz.bz.zbase.ZFile;

public enum ZCltSettings {
	INSTANCE;

	private static int SIZE = 48;

//	private static final String DIR = "/sdcard/Pictures";
//	private static final String DIR = "/sdcard/Pictures/images_new";
//	private static final String DIR = "/sdcard/DCIM";
//	private static final String DIR = "/sdcard/DCIM/Camera";
	private static final String DIR = "/";
//	private static final String DIR = Environment.getRootDirectory().getAbsolutePath();
	
	public String getDir() {
		return DIR;
	}
	
	public ZFile getAppDir() {
		return new ZFile(DIR);
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public int [] getSizes() {
		int [] sizes = new int [3];
		sizes [0] = 48; // List view
		sizes [1] = 96; // Grid view
//		sizes [2] = getImageMaxSize(this); // Gallery view
		sizes [2] = 0;
		return sizes;
    }
}
