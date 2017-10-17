package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 充值记录
 * @author xin.chou
 *
 */
@Table(name="charge_log")
public class ChargeLogBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 充值金额 
	*/
	@Column(name="charge_money")
	private Float chargeMoney;

	/**
	 * 充值时间 
	*/
	@Column(name="charge_time")
	private Long chargeTime;

	/**
	 * 初始余额 
	*/
	@Column(name="init_storage")
	private Float initStorage;

	/**
	 * 用户id 
	*/
	@Column(name="user_id")
	private Long userId;
    	
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
     * 充值金额
     * @return 
     */ 
    public Float getChargeMoney() {
    	return chargeMoney;
    }
    
    /** 
     * 充值金额
     * @param 
     */ 
    public void setChargeMoney(Float chargeMoney) {
    	this.chargeMoney = chargeMoney;
    }
    	
    /** 
     * 充值时间
     * @return 
     */ 
    public Long getChargeTime() {
    	return chargeTime;
    }
    
    /** 
     * 充值时间
     * @param 
     */ 
    public void setChargeTime(Long chargeTime) {
    	this.chargeTime = chargeTime;
    }
    	
    /** 
     * 初始余额
     * @return 
     */ 
    public Float getInitStorage() {
    	return initStorage;
    }
    
    /** 
     * 初始余额
     * @param 
     */ 
    public void setInitStorage(Float initStorage) {
    	this.initStorage = initStorage;
    }
    	
    /** 
     * 用户id
     * @return 
     */ 
    public Long getUserId() {
    	return userId;
    }
    
    /** 
     * 用户id
     * @param 
     */ 
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
    	
}