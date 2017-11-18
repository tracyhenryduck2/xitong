package com.device.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 设备表
 * @author junjun.xue
 *
 */
@Table(name="device")
public class DeviceBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 设备id 
	*/
	@Column(name="dev_tid")
	private String devTid;

	/**
	 * 设备token 
	*/
	@Column(name="token")
	private String token;

	/**
	 * 控制码 
	*/
	@Column(name="ctrl_key")
	private String ctrlKey;

	/**
	 * 绑定码 
	*/
	@Column(name="bind_key")
	private String bindKey;

	/**
	 * 设备mac地址 
	*/
	@Column(name="mac")
	private String mac;

	/**
	 * 设备固件版本号 
	*/
	@Column(name="bin_ver")
	private String binVer;

	/**
	 * 设备固件类型A,B 
	*/
	@Column(name="bin_type")
	private String binType;

	/**
	 * 设备ip 
	*/
	@Column(name="ip")
	private String ip;

	/**
	 * 是否在线 
	*/
	@Column(name="online")
	private Long online;

	/**
	 * 设备种类id 
	*/
	@Column(name="pid")
	private Long pid;

	/**
	 * 设备名称 
	*/
	@Column(name="name")
	private String name;

	/**
	 * wifi名称 
	*/
	@Column(name="ssid")
	private String ssid;
    	
    /** 
     * 
     * @return 
     */ 
    public Long getId() {
    	return id;
    }
    
    /** 
     * 
     * @param 
     */ 
    public void setId(Long id) {
    	this.id = id;
    }
    	
    /** 
     * 设备id
     * @return 
     */ 
    public String getDevTid() {
    	return devTid;
    }
    
    /** 
     * 设备id
     * @param 
     */ 
    public void setDevTid(String devTid) {
    	this.devTid = devTid;
    }
    	
    /** 
     * 设备token
     * @return 
     */ 
    public String getToken() {
    	return token;
    }
    
    /** 
     * 设备token
     * @param 
     */ 
    public void setToken(String token) {
    	this.token = token;
    }
    	
    /** 
     * 控制码
     * @return 
     */ 
    public String getCtrlKey() {
    	return ctrlKey;
    }
    
    /** 
     * 控制码
     * @param 
     */ 
    public void setCtrlKey(String ctrlKey) {
    	this.ctrlKey = ctrlKey;
    }
    	
    /** 
     * 绑定码
     * @return 
     */ 
    public String getBindKey() {
    	return bindKey;
    }
    
    /** 
     * 绑定码
     * @param 
     */ 
    public void setBindKey(String bindKey) {
    	this.bindKey = bindKey;
    }
    	
    /** 
     * 设备mac地址
     * @return 
     */ 
    public String getMac() {
    	return mac;
    }
    
    /** 
     * 设备mac地址
     * @param 
     */ 
    public void setMac(String mac) {
    	this.mac = mac;
    }
    	
    /** 
     * 设备固件版本号
     * @return 
     */ 
    public String getBinVer() {
    	return binVer;
    }
    
    /** 
     * 设备固件版本号
     * @param 
     */ 
    public void setBinVer(String binVer) {
    	this.binVer = binVer;
    }
    	
    /** 
     * 设备固件类型A,B
     * @return 
     */ 
    public String getBinType() {
    	return binType;
    }
    
    /** 
     * 设备固件类型A,B
     * @param 
     */ 
    public void setBinType(String binType) {
    	this.binType = binType;
    }
    	
    /** 
     * 设备ip
     * @return 
     */ 
    public String getIp() {
    	return ip;
    }
    
    /** 
     * 设备ip
     * @param 
     */ 
    public void setIp(String ip) {
    	this.ip = ip;
    }
    	
    /** 
     * 是否在线
     * @return 
     */ 
    public Long getOnline() {
    	return online;
    }
    
    /** 
     * 是否在线
     * @param 
     */ 
    public void setOnline(Long online) {
    	this.online = online;
    }
    	
    /** 
     * 设备种类id
     * @return 
     */ 
    public Long getPid() {
    	return pid;
    }
    
    /** 
     * 设备种类id
     * @param 
     */ 
    public void setPid(Long pid) {
    	this.pid = pid;
    }
    	
    /** 
     * 设备名称
     * @return 
     */ 
    public String getName() {
    	return name;
    }
    
    /** 
     * 设备名称
     * @param 
     */ 
    public void setName(String name) {
    	this.name = name;
    }
    	
    /** 
     * wifi名称
     * @return 
     */ 
    public String getSsid() {
    	return ssid;
    }
    
    /** 
     * wifi名称
     * @param 
     */ 
    public void setSsid(String ssid) {
    	this.ssid = ssid;
    }
}