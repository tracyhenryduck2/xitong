package com.system.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 模块组
 * @author xin.chou
 *
 */
@Table(name="s_function_group")
public class SFunctionGroupBean {

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
	 * 排序 
	*/
	@Column(name="sort")
	private Long sort;

	/**
	 * 上一级 
	*/
	@Column(name="parent_id")
	private Long parentId;
    	
	
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
    	
    /** 
     * 上一级
     * @return 
     */ 
    public Long getParentId() {
    	return parentId;
    }
    
    /** 
     * 上一级
     * @param 
     */ 
    public void setParentId(Long parentId) {
    	this.parentId = parentId;
    }

	public Long getAuthority() {
		return authority;
	}

	public void setAuthority(Long authority) {
		this.authority = authority;
	}
    
    
}