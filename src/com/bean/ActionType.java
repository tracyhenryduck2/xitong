package com.bean;

public enum ActionType {
	
	 HEARTBEAT("heartbeat"),
	 HEARTBEATRESP("heartbeatResp"),
	 DEVICE_SEND("devSend"),
	 DEVICE_SENDRESP("devSendResp"),
	 DEVICE_REPORTINFO("reportDevInfo"),
	 DEVICE_REPORTINFORESP("reportDevInfoResp"),
	 DEVICE_LOGIN("devLogin"),
	 DEVICE_LOGINRESP("devLoginResp"),
     APP_SEND("appSend"),
     APP_SENDRESP("appSendRep"),
     APP_LOGIN("appLogin");
	
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
