package com.install.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.common.BaseDAO;
import com.common.Page;
import com.install.bean.InspectorBean;

public class InspectorDAO extends BaseDAO {
	/**                 
     * 所有信息分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, InspectorBean inspectorBean) {   
    	String sql ="SELECT b.id, a.productno, b.time,s.username from product a,inspector b, s_user s "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " WHERE s.id = b.inspectorid and b.productno = a.productno ";  
    	if(inspectorBean!=null ) { 
    		if(inspectorBean.getUsername() !=null && inspectorBean.getUsername().trim().length()>0) { 
    			objectList.add(inspectorBean.getUsername());
    			sqlWhere += " AND s.username = ? ";
    		}
    		if(inspectorBean.getProductno() !=null && inspectorBean.getProductno().trim().length()>0) { 
    			objectList.add(inspectorBean.getProductno());
    			sqlWhere += " AND a.productno = ? ";
    		}
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from product a,inspector b, s_user s"+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    } 

	/**                 
     * 巡检记录分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPeoplePageList(String inspectorname) {   
    	String sql ="SELECT b.id, a.productno, b.time,s.username from product a,inspector b, s_user s "
    			+ "WHERE s.id = b.inspectorid and b.productno = a.productno "
    			+ "and s.username =  '"+ inspectorname+"'"; 
//    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return j.queryForList(sql);
//    	return list;      
    } 

    
    
}
