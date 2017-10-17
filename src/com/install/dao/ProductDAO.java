package com.install.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.common.BaseDAO;
import com.common.Page;
import com.install.bean.InstallBean;
import com.install.bean.ProductBean;

public class ProductDAO extends BaseDAO{
    /**                 
     * 查询产品信息
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, InstallBean installBean) {   
    	String sql ="select  i.*, s.username  from install i,s_user s "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 and i.workid = s.id";  
    	if(installBean!=null) { 
    		if(installBean.getUsername()!=null && installBean.getUsername().trim().length()>0) { 
    			objectList.add(installBean.getUsername());
    			sqlWhere += " AND s.username = ? ";
    		}
    		if(installBean.getAddr()!=null && installBean.getAddr().trim().length()>0) { 
    			objectList.add(installBean.getAddr());
    			sqlWhere += " AND i.addr like ? ";
    		}
    		if(installBean.getTime() != 0) { 
    			objectList.add(installBean.getTime());
    			sqlWhere += " AND i.time = ? ";
    		}
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from install i,s_user s "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }   
    
    
    /**                 
     * 查询单个产品信息
     * @param page      
     * @param           
     * @return          
     */                 
    public ProductBean getInformation(String productno) {   
    	String sql ="select  *  from product "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 and productno= ?";  
        objectList.add(productno);
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray();        
    	return j.queryForBean(ProductBean.class, sql, pram);
    } 
}
