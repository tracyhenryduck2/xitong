package com.device.bean;

import com.avatar.db.annotation.*;
import com.common.*;

/**
 * 
 * @author junjun.xue
 *
 */
@Table(name="app_update")
public class AppUpdateBean {

	/**
	 *  
	*/
	@Column(name="id",primaryKey=true,generatorType=GeneratorType.AUTO_INCREMENT)
	private Long id;

	/**
	 * app版本号 
	*/
	@Column(name="version")
	private String version;

	/**
	 * app版本号代码 
	*/
	@Column(name="code")
	private Long code;

	/**
	 * app下载的路径 
	*/
	@Column(name="url")
	private String url;

	/**
	 * 英文说明 
	*/
	@Column(name="en")
	private String en;

	/**
	 * 中文说明 
	*/
	@Column(name="zh")
	private String zh;

	/**
	 * 法文说明 
	*/
	@Column(name="fr")
	private String fr;

	/**
	 * 德文说明 
	*/
	@Column(name="de")
	private String de;

	/**
	 * 西班牙文说明 
	*/
	@Column(name="es")
	private String es;

	/**
	 * 荷兰文说明 
	*/
	@Column(name="nl")
	private String nl;

	/**
	 * 芬兰文说明 
	*/
	@Column(name="fi")
	private String fi;

	/**
	 * 斯洛文尼亚语说明 
	*/
	@Column(name="sl")
	private String sl;

	/**
	 * 意大利语说明 
	*/
	@Column(name="it")
	private String it;

	/**
	 * 捷克语说明 
	*/
	@Column(name="cs")
	private String cs;

	/**
	 * app下载的路径国外 
	*/
	@Column(name="url_ex")
	private String urlEx;

	/**
	 * 是否是ios 
	*/
	@Column(name="ios")
	private Long ios;
    	
	
	/**
	 * app下项目标示
	*/
	@Column(name="project")
	private String project;
	
	
	/**
	 * 挪威语说明 
	*/
	@Column(name="nb")
	private String nb;
	
	
	/**
	 * 丹麦语说明 
	*/
	@Column(name="da")
	private String da;
	
	
	/**
	 * 瑞典语说明 
	*/
	@Column(name="sv")
	private String sv;
	
	
	/**
	 * 日语说明 
	*/
	@Column(name="ja")
	private String ja;
	
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
     * app版本号
     * @return 
     */ 
    public String getVersion() {
    	return version;
    }
    
    /** 
     * app版本号
     * @param 
     */ 
    public void setVersion(String version) {
    	this.version = version;
    }
    	
    /** 
     * app版本号代码
     * @return 
     */ 
    public Long getCode() {
    	return code;
    }
    
    /** 
     * app版本号代码
     * @param 
     */ 
    public void setCode(Long code) {
    	this.code = code;
    }
    	
    /** 
     * app下载的路径
     * @return 
     */ 
    public String getUrl() {
    	return url;
    }
    
    /** 
     * app下载的路径
     * @param 
     */ 
    public void setUrl(String url) {
    	this.url = url;
    }
    	
    /** 
     * 英文说明
     * @return 
     */ 
    public String getEn() {
    	return en;
    }
    
    /** 
     * 英文说明
     * @param 
     */ 
    public void setEn(String en) {
    	this.en = en;
    }
    	
    /** 
     * 中文说明
     * @return 
     */ 
    public String getZh() {
    	return zh;
    }
    
    /** 
     * 中文说明
     * @param 
     */ 
    public void setZh(String zh) {
    	this.zh = zh;
    }
    	
    /** 
     * 法文说明
     * @return 
     */ 
    public String getFr() {
    	return fr;
    }
    
    /** 
     * 法文说明
     * @param 
     */ 
    public void setFr(String fr) {
    	this.fr = fr;
    }
    	
    /** 
     * 德文说明
     * @return 
     */ 
    public String getDe() {
    	return de;
    }
    
    /** 
     * 德文说明
     * @param 
     */ 
    public void setDe(String de) {
    	this.de = de;
    }
    	
    /** 
     * 西班牙文说明
     * @return 
     */ 
    public String getEs() {
    	return es;
    }
    
    /** 
     * 西班牙文说明
     * @param 
     */ 
    public void setEs(String es) {
    	this.es = es;
    }
    	
    /** 
     * 荷兰文说明
     * @return 
     */ 
    public String getNl() {
    	return nl;
    }
    
    /** 
     * 荷兰文说明
     * @param 
     */ 
    public void setNl(String nl) {
    	this.nl = nl;
    }
    	
    /** 
     * 芬兰文说明
     * @return 
     */ 
    public String getFi() {
    	return fi;
    }
    
    /** 
     * 芬兰文说明
     * @param 
     */ 
    public void setFi(String fi) {
    	this.fi = fi;
    }
    	
    /** 
     * 斯洛文尼亚语说明
     * @return 
     */ 
    public String getSl() {
    	return sl;
    }
    
    /** 
     * 斯洛文尼亚语说明
     * @param 
     */ 
    public void setSl(String sl) {
    	this.sl = sl;
    }
    	
    /** 
     * 意大利语说明
     * @return 
     */ 
    public String getIt() {
    	return it;
    }
    
    /** 
     * 意大利语说明
     * @param 
     */ 
    public void setIt(String it) {
    	this.it = it;
    }
    	
    /** 
     * 捷克语说明
     * @return 
     */ 
    public String getCs() {
    	return cs;
    }
    
    /** 
     * 捷克语说明
     * @param 
     */ 
    public void setCs(String cs) {
    	this.cs = cs;
    }
    	
    /** 
     * app下载的路径国外
     * @return 
     */ 
    public String getUrlEx() {
    	return urlEx;
    }
    
    /** 
     * app下载的路径国外
     * @param 
     */ 
    public void setUrlEx(String urlEx) {
    	this.urlEx = urlEx;
    }
    	
    /** 
     * 是否是ios
     * @return 
     */ 
    public Long getIos() {
    	return ios;
    }
    
    /** 
     * 是否是ios
     * @param 
     */ 
    public void setIos(Long ios) {
    	this.ios = ios;
    }

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getNb() {
		return nb;
	}

	public void setNb(String nb) {
		this.nb = nb;
	}

	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	public String getSv() {
		return sv;
	}

	public void setSv(String sv) {
		this.sv = sv;
	}

	public String getJa() {
		return ja;
	}

	public void setJa(String ja) {
		this.ja = ja;
	}
    
	
	
    
}