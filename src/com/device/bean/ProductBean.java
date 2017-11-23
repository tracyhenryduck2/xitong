package com.device.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 产品表
 * @author junjun.xue
 *
 */
@Table(name="product")
public class ProductBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 设备型号名称 
	*/
	@Column(name="name")
	private String name;

	/**
	 * 设备种类标识 
	*/
	@Column(name="prod_key")
	private String prodKey;

	/**
	 * 设备型号 
	*/
	@Column(name="model")
	private String model;

	/**
	 * 介绍 
	*/
	@Column(name="desp")
	private String desp;

	/**
	 * 产品图片 
	*/
	@Column(name="img")
	private String img;

	/**
	 * 是否强制绑定 
	*/
	@Column(name="force_bind")
	private Long forceBind;

	/**
	 * 生成时间 
	*/
	@Column(name="ctime")
	private Long ctime;
    	
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
     * 设备型号名称
     * @return 
     */ 
    public String getName() {
    	return name;
    }
    
    /** 
     * 设备型号名称
     * @param 
     */ 
    public void setName(String name) {
    	this.name = name;
    }
    	
    /** 
     * 设备种类标识
     * @return 
     */ 
    public String getProdKey() {
    	return prodKey;
    }
    
    /** 
     * 设备种类标识
     * @param 
     */ 
    public void setProdKey(String prodKey) {
    	this.prodKey = prodKey;
    }
    	
    /** 
     * 设备型号
     * @return 
     */ 
    public String getModel() {
    	return model;
    }
    
    /** 
     * 设备型号
     * @param 
     */ 
    public void setModel(String model) {
    	this.model = model;
    }
    	
    /** 
     * 介绍
     * @return 
     */ 
    public String getDesp() {
    	return desp;
    }
    
    /** 
     * 介绍
     * @param 
     */ 
    public void setDesp(String desp) {
    	this.desp = desp;
    }
    	
    /** 
     * 产品图片
     * @return 
     */ 
    public String getImg() {
    	return img;
    }
    
    /** 
     * 产品图片
     * @param 
     */ 
    public void setImg(String img) {
    	this.img = img;
    }
    	
    /** 
     * 是否强制绑定
     * @return 
     */ 
    public Long getForceBind() {
    	return forceBind;
    }
    
    /** 
     * 是否强制绑定
     * @param 
     */ 
    public void setForceBind(Long forceBind) {
    	this.forceBind = forceBind;
    }
    	
    /** 
     * 生成时间
     * @return 
     */ 
    public Long getCtime() {
    	return ctime;
    }
    
    /** 
     * 生成时间
     * @param 
     */ 
    public void setCtime(Long ctime) {
    	this.ctime = ctime;
    }
    	
}