package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 客户管理
 * @author xin.chou
 *
 */
@Table(name="member")
public class MemberBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * hekrid 
	*/
	@Column(name="hekrid")
	private String hekrid;

	/**
	 * 性别 
	*/
	@Column(name="sex")
	private Long sex;

	/**
	 * 手机 
	*/
	@Column(name="phone")
	private String phone;

	/**
	 * 姓名 
	*/
	@Column(name="username")
	private String username;

	/**
	 * 邮箱 
	*/
	@Column(name="email")
	private String email;

	/**
	 * 绑定此平台时间 
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
     * hekrid
     * @return 
     */ 
    public String getHekrid() {
    	return hekrid;
    }
    
    /** 
     * hekrid
     * @param 
     */ 
    public void setHekrid(String hekrid) {
    	this.hekrid = hekrid;
    }
    	
    /** 
     * 性别
     * @return 
     */ 
    public Long getSex() {
    	return sex;
    }
    
    /** 
     * 性别
     * @param 
     */ 
    public void setSex(Long sex) {
    	this.sex = sex;
    }
    	
    /** 
     * 手机
     * @return 
     */ 
    public String getPhone() {
    	return phone;
    }
    
    /** 
     * 手机
     * @param 
     */ 
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    	
    /** 
     * 姓名
     * @return 
     */ 
    public String getUsername() {
    	return username;
    }
    
    /** 
     * 姓名
     * @param 
     */ 
    public void setUsername(String username) {
    	this.username = username;
    }
    	
    /** 
     * 邮箱
     * @return 
     */ 
    public String getEmail() {
    	return email;
    }
    
    /** 
     * 邮箱
     * @param 
     */ 
    public void setEmail(String email) {
    	this.email = email;
    }
    	
    /** 
     * 绑定此平台时间
     * @return 
     */ 
    public Long getCtime() {
    	return ctime;
    }
    
    /** 
     * 绑定此平台时间
     * @param 
     */ 
    public void setCtime(Long ctime) {
    	this.ctime = ctime;
    }
    	
}