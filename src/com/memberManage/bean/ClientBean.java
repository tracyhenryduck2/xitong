package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 客户
 * @author junjun.xue
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
	 * 邮箱 
	*/
	@Column(name="email")
	private String email;

	/**
	 * 手机号 
	*/
	@Column(name="phone_number")
	private String phoneNumber;

	/**
	 * 生日 
	*/
	@Column(name="birthday")
	private Long birthday;

	/**
	 * 名 
	*/
	@Column(name="first_name")
	private String firstName;

	/**
	 * 姓 
	*/
	@Column(name="last_name")
	private String lastName;

	/**
	 * 性别 
	*/
	@Column(name="render")
	private String render;

	/**
	 * 年龄 
	*/
	@Column(name="age")
	private Long age;

	/**
	 * 描述 
	*/
	@Column(name="description")
	private String description;

	/**
	 * 小头像 
	*/
	@Column(name="small")
	private String small;

	/**
	 * 大头像 
	*/
	@Column(name="big")
	private String big;

	/**
	 * 更新时间 
	*/
	@Column(name="update_date")
	private Long updateDate;
    	
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
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    
    /** 
     * 手机号
     * @param 
     */ 
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    	
    /** 
     * 生日
     * @return 
     */ 
    public Long getBirthday() {
    	return birthday;
    }
    
    /** 
     * 生日
     * @param 
     */ 
    public void setBirthday(Long birthday) {
    	this.birthday = birthday;
    }
    	
    /** 
     * 名
     * @return 
     */ 
    public String getFirstName() {
    	return firstName;
    }
    
    /** 
     * 名
     * @param 
     */ 
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    	
    /** 
     * 姓
     * @return 
     */ 
    public String getLastName() {
    	return lastName;
    }
    
    /** 
     * 姓
     * @param 
     */ 
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    	
    /** 
     * 性别
     * @return 
     */ 
    public String getRender() {
    	return render;
    }
    
    /** 
     * 性别
     * @param 
     */ 
    public void setRender(String render) {
    	this.render = render;
    }
    	
    /** 
     * 年龄
     * @return 
     */ 
    public Long getAge() {
    	return age;
    }
    
    /** 
     * 年龄
     * @param 
     */ 
    public void setAge(Long age) {
    	this.age = age;
    }
    	
    /** 
     * 描述
     * @return 
     */ 
    public String getDescription() {
    	return description;
    }
    
    /** 
     * 描述
     * @param 
     */ 
    public void setDescription(String description) {
    	this.description = description;
    }
    	
    /** 
     * 小头像
     * @return 
     */ 
    public String getSmall() {
    	return small;
    }
    
    /** 
     * 小头像
     * @param 
     */ 
    public void setSmall(String small) {
    	this.small = small;
    }
    	
    /** 
     * 大头像
     * @return 
     */ 
    public String getBig() {
    	return big;
    }
    
    /** 
     * 大头像
     * @param 
     */ 
    public void setBig(String big) {
    	this.big = big;
    }
    	
    /** 
     * 更新时间
     * @return 
     */ 
    public Long getUpdateDate() {
    	return updateDate;
    }
    
    /** 
     * 更新时间
     * @param 
     */ 
    public void setUpdateDate(Long updateDate) {
    	this.updateDate = updateDate;
    }
    	
}