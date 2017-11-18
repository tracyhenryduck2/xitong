package com.message;

public class DeviceLoginParamBean extends ParamBean {
   private String devTid;
   private String prodKey;
   private String token;


	public String getDevTid() {
		return devTid;
	}
	public void setDevTid(String devTid) {
		this.devTid = devTid;
	}
	public String getProdKey() {
		return prodKey;
	}
	public void setProdKey(String prodKey) {
		this.prodKey = prodKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
