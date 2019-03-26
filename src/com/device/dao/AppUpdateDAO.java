package com.device.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.device.bean.AppUpdateBean;
                        
/**                     
 *                      
 *  <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class AppUpdateDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, AppUpdateBean appupdateBean) {   
    	String sql ="select a.* from app_update a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(appupdateBean!=null) { 
    		if(appupdateBean.getId() != null) { 
    			objectList.add(appupdateBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(appupdateBean.getVersion() != null && appupdateBean.getVersion().trim().length()>0) { 
    			objectList.add(appupdateBean.getVersion());
    			sqlWhere += " AND a.version = ? ";
    		} 
    		if(appupdateBean.getCode() != null) { 
    			objectList.add(appupdateBean.getCode());
    			sqlWhere += " AND a.code = ? ";
    		} 
    		if(appupdateBean.getUrl() != null && appupdateBean.getUrl().trim().length()>0) { 
    			objectList.add(appupdateBean.getUrl());
    			sqlWhere += " AND a.url = ? ";
    		} 
    		if(appupdateBean.getEn() != null && appupdateBean.getEn().trim().length()>0) { 
    			objectList.add(appupdateBean.getEn());
    			sqlWhere += " AND a.en = ? ";
    		} 
    		if(appupdateBean.getZh() != null && appupdateBean.getZh().trim().length()>0) { 
    			objectList.add(appupdateBean.getZh());
    			sqlWhere += " AND a.zh = ? ";
    		} 
    		if(appupdateBean.getFr() != null && appupdateBean.getFr().trim().length()>0) { 
    			objectList.add(appupdateBean.getFr());
    			sqlWhere += " AND a.fr = ? ";
    		} 
    		if(appupdateBean.getDe() != null && appupdateBean.getDe().trim().length()>0) { 
    			objectList.add(appupdateBean.getDe());
    			sqlWhere += " AND a.de = ? ";
    		} 
    		if(appupdateBean.getEs() != null && appupdateBean.getEs().trim().length()>0) { 
    			objectList.add(appupdateBean.getEs());
    			sqlWhere += " AND a.es = ? ";
    		} 
    		if(appupdateBean.getNl() != null && appupdateBean.getNl().trim().length()>0) { 
    			objectList.add(appupdateBean.getNl());
    			sqlWhere += " AND a.nl = ? ";
    		} 
    		if(appupdateBean.getFi() != null && appupdateBean.getFi().trim().length()>0) { 
    			objectList.add(appupdateBean.getFi());
    			sqlWhere += " AND a.fi = ? ";
    		} 
    		if(appupdateBean.getSl() != null && appupdateBean.getSl().trim().length()>0) { 
    			objectList.add(appupdateBean.getSl());
    			sqlWhere += " AND a.sl = ? ";
    		} 
    		if(appupdateBean.getIt() != null && appupdateBean.getIt().trim().length()>0) { 
    			objectList.add(appupdateBean.getIt());
    			sqlWhere += " AND a.it = ? ";
    		} 
    		if(appupdateBean.getCs() != null && appupdateBean.getCs().trim().length()>0) { 
    			objectList.add(appupdateBean.getCs());
    			sqlWhere += " AND a.cs = ? ";
    		} 
    		if(appupdateBean.getUrlEx() != null && appupdateBean.getUrlEx().trim().length()>0) { 
    			objectList.add(appupdateBean.getUrlEx());
    			sqlWhere += " AND a.url_ex = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from app_update a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
