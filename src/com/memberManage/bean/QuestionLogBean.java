package com.memberManage.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 问题记录
 * @author xin.chou
 *
 */
@Table(name="question_log")
public class QuestionLogBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 是安卓还是苹果问题,0代表安卓，1代表IOS，2代表其他 
	*/
	@Column(name="type")
	private Long type;

	/**
	 * 主题 
	*/
	@Column(name="title")
	private String title;

	/**
	 * 问题描述 
	*/
	@Column(name="dsc")
	private String dsc;

	/**
	 * APP版本号信息和网关固件版本信息 
	*/
	@Column(name="ver")
	private String ver;

	/**
	 * 创建时间 
	*/
	@Column(name="createtime")
	private Long createtime;

	/**
	 * 回答 
	*/
	@Column(name="answer")
	private String answer;

	/**
	 * 回答时间 
	*/
	@Column(name="answertime")
	private Long answertime;

	/**
	 * 创建者id 
	*/
	@Column(name="createid")
	private Long createid;
    	
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
     * 是安卓还是苹果问题,0代表安卓，1代表IOS，2代表其他
     * @return 
     */ 
    public Long getType() {
    	return type;
    }
    
    /** 
     * 是安卓还是苹果问题,0代表安卓，1代表IOS，2代表其他
     * @param 
     */ 
    public void setType(Long type) {
    	this.type = type;
    }
    	
    /** 
     * 主题
     * @return 
     */ 
    public String getTitle() {
    	return title;
    }
    
    /** 
     * 主题
     * @param 
     */ 
    public void setTitle(String title) {
    	this.title = title;
    }
    	
    /** 
     * 问题描述
     * @return 
     */ 
    public String getDsc() {
    	return dsc;
    }
    
    /** 
     * 问题描述
     * @param 
     */ 
    public void setDsc(String dsc) {
    	this.dsc = dsc;
    }
    	
    /** 
     * APP版本号信息和网关固件版本信息
     * @return 
     */ 
    public String getVer() {
    	return ver;
    }
    
    /** 
     * APP版本号信息和网关固件版本信息
     * @param 
     */ 
    public void setVer(String ver) {
    	this.ver = ver;
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
    	
    /** 
     * 回答
     * @return 
     */ 
    public String getAnswer() {
    	return answer;
    }
    
    /** 
     * 回答
     * @param 
     */ 
    public void setAnswer(String answer) {
    	this.answer = answer;
    }
    	
    /** 
     * 回答时间
     * @return 
     */ 
    public Long getAnswertime() {
    	return answertime;
    }
    
    /** 
     * 回答时间
     * @param 
     */ 
    public void setAnswertime(Long answertime) {
    	this.answertime = answertime;
    }
    	
    /** 
     * 创建者id
     * @return 
     */ 
    public Long getCreateid() {
    	return createid;
    }
    
    /** 
     * 创建者id
     * @param 
     */ 
    public void setCreateid(Long createid) {
    	this.createid = createid;
    }
    	

}