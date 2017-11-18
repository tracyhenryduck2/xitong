package com.bean;

public enum ActionType {
	
	 HEARTBEAT("heartbeat"),
	 HEARTBEATRESP("heartbeatResp"),
	 DEVICE_SEND("deviceSend"),
	 DEVICE_SENDRESP("deviceSendResp"),
	 DEVICE_REPORTINFO("reportDevInfo"),
	 DEVICE_REPORTINFORESP("reportDevInfoResp"),
	 DEVICE_LOGIN("deviceLogin"),
	 DEVICE_LOGINRESP("deviceLoginResp"),
     APP_SEND("appSend"),
     APP_SENDRESP("appSendRep");
	
    // 成员变量  
    private String name;  
    // 构造方法  
    private ActionType(String name) {  
        this.name = name;   
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}  
    
    
}
