package com.device.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 参数表
 * @author junjun.xue
 *
 */
@Table(name="client_param")
public class ClientParamBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 名称 
	*/
	@Column(name="name")
	private String name;

	/**
	 * 类型:1:int;2:String 
	*/
	@Column(name="type")
	private Long type;

	/**
	 * 最小值 
	*/
	@Column(name="min")
	private Long min;

	/**
	 * 最大值 
	*/
	@Column(name="max")
	private Long max;

	/**
	 * 是否弃用 
	*/
	@Column(name="abandoned")
	private Long abandoned;

	/**
	 * 创建时间 
	*/
	@Column(name="ctime")
	private Long ctime;

	/**
	 * 弃用时间 
	*/
	@Column(name="atime")
	private Long atime;

	/**
	 * 产品id 
	*/
	@Column(name="pid")
	private Long pid;
    	
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
     * 名称
     * @return 
     */ 
    public String getName() {
    	return name;
    }
    
    /** 
     * 名称
     * @param 
     */ 
    public void setName(String name) {
    	this.name = name;
    }
    	
    /** 
     * 类型:1:int;2:String
     * @return 
     */ 
    public Long getType() {
    	return type;
    }
    
    /** 
     * 类型:1:int;2:String
     * @param 
     */ 
    public void setType(Long type) {
    	this.type = type;
    }
    	
    /** 
     * 最小值
     * @return 
     */ 
    public Long getMin() {
    	return min;
    }
    
    /** 
     * 最小值
     * @param 
     */ 
    public void setMin(Long min) {
    	this.min = min;
    }
    	
    /** 
     * 最大值
     * @return 
     */ 
    public Long getMax() {
    	return max;
    }
    
    /** 
     * 最大值
     * @param 
     */ 
    public void setMax(Long max) {
    	this.max = max;
    }
    	
    /** 
     * 是否弃用
     * @return 
     */ 
    public Long getAbandoned() {
    	return abandoned;
    }
    
    /** 
     * 是否弃用
     * @param 
     */ 
    public void setAbandoned(Long abandoned) {
    	this.abandoned = abandoned;
    }
    	
    /** 
     * 创建时间
     * @return 
     */ 
    public Long getCtime() {
    	return ctime;
    }
    
    /** 
     * 创建时间
     * @param 
     */ 
    public void setCtime(Long ctime) {
    	this.ctime = ctime;
    }
    	
    /** 
     * 弃用时间
     * @return 
     */ 
    public Long getAtime() {
    	return atime;
    }
    
    /** 
     * 弃用时间
     * @param 
     */ 
    public void setAtime(Long atime) {
    	this.atime = atime;
    }
    	
    /** 
     * 产品id
     * @return 
     */ 
    public Long getPid() {
    	return pid;
    }
    
    /** 
     * 产品id
     * @param 
     */ 
    public void setPid(Long pid) {
    	this.pid = pid;
    }
    	
}