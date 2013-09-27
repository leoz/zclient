package com.leoz.bz.zclient;

public enum ZCltConnector {
	INSTANCE;
	
	public void start () {
		ZCltService.INSTANCE.start();
		if (ZCltService.INSTANCE.started()) {
			ZCltService.INSTANCE.bind();        			
		}
	}
	
	public void stop () {
		if(ZCltManager.INSTANCE.connected()) {
			ZCltService.INSTANCE.release();
		}
		ZCltService.INSTANCE.stop();		
	}
}
