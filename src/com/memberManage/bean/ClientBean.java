package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 客户
 * @author xin.chou
 *
 */
@Table(name="client")
public class ClientBean {

	/**
	 * id 
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 登陆名 
	*/
	@Column(name="username")
	private String username;

	/**
	 * 密码 
	*/
	@Column(name="password")
	private String password;

	/**
	 * 头像 
	*/
	@Column(name="img")
	private String img;

	/**
	 * 邮箱 
	*/
	@Column(name="email")
	private String email;

	/**
	 * 手机号 
	*/
	@Column(name="phone")
	private String phone;
    	
    /** 
     * id
     * @return 
     */ 
    public Long getId() {
    	return id;
    }
    
    /** 
     * id
     * @param 
     */ 
    public void setId(Long id) {
    	this.id = id;
    }
    	
    /** 
     * 登陆名
     * @return 
     */ 
    public String getUsername() {
    	return username;
    }
    
    /** 
     * 登陆名
     * @param 
     */ 
    public void setUsername(String username) {
    	this.username = username;
    }
    	
    /** 
     * 密码
     * @return 
     */ 
    public String getPassword() {
    	return password;
    }
    
    /** 
     * 密码
     * @param 
     */ 
    public void setPassword(String password) {
    	this.password = password;
    }
    	
    /** 
     * 头像
     * @return 
     */ 
    public String getImg() {
    	return img;
    }
    
    /** 
     * 头像
     * @param 
     */ 
    public void setImg(String img) {
    	this.img = img;
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
     * 手机号
     * @return 
     */ 
    public String getPhone() {
    	return phone;
    }
    
    /** 
     * 手机号
     * @param 
     */ 
    public void setPhone(String phone) {
    	this.phone = phone;
    }
}