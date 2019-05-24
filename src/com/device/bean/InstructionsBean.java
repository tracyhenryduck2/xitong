package com.device.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 
 * @author junjun.xue
 *
 */
@Table(name="instructions")
public class InstructionsBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * 产品名称 
	*/
	@Column(name="name")
	private String name;

	/**
	 * 文件名 
	*/
	@Column(name="file_url")
	private String fileUrl;
    	
	
	/**
	 * 文件名(英文) 
	*/
	@Column(name="file_url_en")
	private String fileUrlEn;
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
     * 产品名称
     * @return 
     */ 
    public String getName() {
    	return name;
    }
    
    /** 
     * 产品名称
     * @param 
     */ 
    public void setName(String name) {
    	this.name = name;
    }
    	
    /** 
     * 文件名
     * @return 
     */ 
    public String getFileUrl() {
    	return fileUrl;
    }
    
    /** 
     * 文件名
     * @param 
     */ 
    public void setFileUrl(String fileUrl) {
    	this.fileUrl = fileUrl;
    }

	public String getFileUrlEn() {
		return fileUrlEn;
	}

	public void setFileUrlEn(String fileUrlEn) {
		this.fileUrlEn = fileUrlEn;
	}
    
    
}