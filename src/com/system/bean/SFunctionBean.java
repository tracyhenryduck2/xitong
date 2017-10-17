package com.system.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 功能
 * @author xin.chou
 *
 */
@Table(name="s_function")
public class SFunctionBean {

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
	 * 地址 
	*/
	@Column(name="url")
	private String url;

	/**
	 * 模块 
	*/
	@Column(name="function_group_id")
	private Long functionGroupId;

	/**
	 * 排序 
	*/
	@Column(name="sort")
	private Long sort;
	/**
	 * 权限
	*/
	@Column(name="authority")
	private Long authority;
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
     * 地址
     * @return 
     */ 
    public String getUrl() {
    	return url;
    }
    
    /** 
     * 地址
     * @param 
     */ 
    public void setUrl(String url) {
    	this.url = url;
    }
    	
    /** 
     * 模块
     * @return 
     */ 
    public Long getFunctionGroupId() {
    	return functionGroupId;
    }
    
    /** 
     * 模块
     * @param 
     */ 
    public void setFunctionGroupId(Long functionGroupId) {
    	this.functionGroupId = functionGroupId;
    }
    	
    /** 
     * 排序
     * @return 
     */ 
    public Long getSort() {
    	return sort;
    }
    
    /** 
     * 排序
     * @param 
     */ 
    public void setSort(Long sort) {
    	this.sort = sort;
    }

	public Long getAuthority() {
		return authority;
	}

	public void setAuthority(Long authority) {
		this.authority = authority;
	}
    
    
}