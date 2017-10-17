package com.system.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 管理员表
 * @author xin.chou
 *
 */
@Table(name="s_user")
public class SUserBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 用户名 
	*/
	@Column(name="username")
	private String username;

	/**
	 * 登录密码 
	*/
	@Column(name="password")
	private String password;

	/**
	 * 类型1总管理员，2其他 
	*/
	@Column(name="type")
	private Long type;

	/**
	 * 创建时间 
	*/
	@Column(name="createtime")
	private Long createtime;
    
	
	/**
	 * 手机号 
	*/
	@Column(name="phone")
	private String phone;
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
     * 用户名
     * @return 
     */ 
    public String getUsername() {
    	return username;
    }
    
    /** 
     * 用户名
     * @param 
     */ 
    public void setUsername(String username) {
    	this.username = username;
    }
    	
    /** 
     * 登录密码
     * @return 
     */ 
    public String getPassword() {
    	return password;
    }
    
    /** 
     * 登录密码
     * @param 
     */ 
    public void setPassword(String password) {
    	this.password = password;
    }
    	
    /** 
     * 类型1总管理员，2其他
     * @return 
     */ 
    public Long getType() {
    	return type;
    }
    
    /** 
     * 类型1总管理员，2其他
     * @param 
     */ 
    public void setType(Long type) {
    	this.type = type;
    }
    	
    /** 
     * 创建时间
     * @return 
     */ 
    public Long getCreatetime() {
    	return createtime;
    }
    
    /** 
     * 创建时间
     * @param 
     */ 
    public void setCreatetime(Long createtime) {
    	this.createtime = createtime;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
 
    
}