package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 令牌表
 * @author xin.chou
 *
 */
@Table(name="client_token")
public class ClientTokenBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * clientid 
	*/
	@Column(name="client_id")
	private Long clientId;

	/**
	 * 操作令牌 
	*/
	@Column(name="access_token")
	private String accessToken;

	/**
	 * 刷新令牌 
	*/
	@Column(name="refresh_token")
	private String refreshToken;

	/**
	 * 过期时间（单位:秒） 
	*/
	@Column(name="expires_in")
	private Long expiresIn;

	/**
	 * 刷新令牌过期时间(单位:秒) 
	*/
	@Column(name="expires_in_refresh")
	private Long expiresInRefresh;

	/**
	 * 时间 
	*/
	@Column(name="time")
	private Long time;
   
	/**
	 * 时间 
	*/
	@Column(name="client_type")
	private String clientType;
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
     * clientid
     * @return 
     */ 
    public Long getClientId() {
    	return clientId;
    }
    
    /** 
     * clientid
     * @param 
     */ 
    public void setClientId(Long clientId) {
    	this.clientId = clientId;
    }
    	
    /** 
     * 操作令牌
     * @return 
     */ 
    public String getAccessToken() {
    	return accessToken;
    }
    
    /** 
     * 操作令牌
     * @param 
     */ 
    public void setAccessToken(String accessToken) {
    	this.accessToken = accessToken;
    }
    	
    /** 
     * 刷新令牌
     * @return 
     */ 
    public String getRefreshToken() {
    	return refreshToken;
    }
    
    /** 
     * 刷新令牌
     * @param 
     */ 
    public void setRefreshToken(String refreshToken) {
    	this.refreshToken = refreshToken;
    }
    	
    /** 
     * 过期时间（单位:秒）
     * @return 
     */ 
    public Long getExpiresIn() {
    	return expiresIn;
    }
    
    /** 
     * 过期时间（单位:秒）
     * @param 
     */ 
    public void setExpiresIn(Long expiresIn) {
    	this.expiresIn = expiresIn;
    }
    	
    /** 
     * 刷新令牌过期时间(单位:秒)
     * @return 
     */ 
    public Long getExpiresInRefresh() {
    	return expiresInRefresh;
    }
    
    /** 
     * 刷新令牌过期时间(单位:秒)
     * @param 
     */ 
    public void setExpiresInRefresh(Long expiresInRefresh) {
    	this.expiresInRefresh = expiresInRefresh;
    }
    	
    /** 
     * 时间
     * @return 
     */ 
    public Long getTime() {
    	return time;
    }
    
    /** 
     * 时间
     * @param 
     */ 
    public void setTime(Long time) {
    	this.time = time;
    }

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
    	

}