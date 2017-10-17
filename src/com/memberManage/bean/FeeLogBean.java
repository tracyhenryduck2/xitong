package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 消费记录
 * @author xin.chou
 *
 */
@Table(name="fee_log")
public class FeeLogBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 消费金额 
	*/
	@Column(name="fee_money")
	private Float feeMoney;

	/**
	 * 消费时间 
	*/
	@Column(name="fee_time")
	private Long feeTime;

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
     * 消费金额
     * @return 
     */ 
    public Float getFeeMoney() {
    	return feeMoney;
    }
    
    /** 
     * 消费金额
     * @param 
     */ 
    public void setFeeMoney(Float feeMoney) {
    	this.feeMoney = feeMoney;
    }
    	
    /** 
     * 消费时间
     * @return 
     */ 
    public Long getFeeTime() {
    	return feeTime;
    }
    
    /** 
     * 消费时间
     * @param 
     */ 
    public void setFeeTime(Long feeTime) {
    	this.feeTime = feeTime;
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