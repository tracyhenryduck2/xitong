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
	@Column(name="deviceid")
	private String deviceid;

	/**
	 * 设备token 
	*/
	@Column(name="token")
	private String token;

	/**
	 * 控制码 
	*/
	@Column(name="ctrlkey")
	private String ctrlkey;

	/**
	 * 绑定码 
	*/
	@Column(name="bindkey")
	private String bindkey;

	/**
	 * 设备mac地址 
	*/
	@Column(name="mac")
	private String mac;

	/**
	 * 设备固件版本号 
	*/
	@Column(name="binver")
	private String binver;

	/**
	 * 设备固件类型A,B 
	*/
	@Column(name="bintype")
	private String bintype;

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
    public String getDeviceid() {
    	return deviceid;
    }
    
    /** 
     * 设备id
     * @param 
     */ 
    public void setDeviceid(String deviceid) {
    	this.deviceid = deviceid;
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
    public String getCtrlkey() {
    	return ctrlkey;
    }
    
    /** 
     * 控制码
     * @param 
     */ 
    public void setCtrlkey(String ctrlkey) {
    	this.ctrlkey = ctrlkey;
    }
    	
    /** 
     * 绑定码
     * @return 
     */ 
    public String getBindkey() {
    	return bindkey;
    }
    
    /** 
     * 绑定码
     * @param 
     */ 
    public void setBindkey(String bindkey) {
    	this.bindkey = bindkey;
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
    public String getBinver() {
    	return binver;
    }
    
    /** 
     * 设备固件版本号
     * @param 
     */ 
    public void setBinver(String binver) {
    	this.binver = binver;
    }
    	
    /** 
     * 设备固件类型A,B
     * @return 
     */ 
    public String getBintype() {
    	return bintype;
    }
    
    /** 
     * 设备固件类型A,B
     * @param 
     */ 
    public void setBintype(String bintype) {
    	this.bintype = bintype;
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

	@Override
	public String toString() {
		return "DeviceBean [id=" + id + ", deviceid=" + deviceid + ", token=" + token + ", ctrlkey=" + ctrlkey
				+ ", bindkey=" + bindkey + ", mac=" + mac + ", binver=" + binver + ", bintype=" + bintype + ", ip=" + ip
				+ ", online=" + online + ", pid=" + pid + ", name=" + name + "]";
	}
    
    
}