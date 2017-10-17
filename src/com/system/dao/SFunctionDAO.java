package com.system.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.system.bean.SFunctionBean;
                        
/**                     
 *                      
 * 功能 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class SFunctionDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, SFunctionBean sfunctionBean) {   
    	String sql ="select a.* from s_function a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(sfunctionBean!=null) { 
    		if(sfunctionBean.getId() != null) { 
    			objectList.add(sfunctionBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(sfunctionBean.getName() != null && sfunctionBean.getName().trim().length()>0) { 
    			objectList.add(sfunctionBean.getName());
    			sqlWhere += " AND a.name = ? ";
    		} 
    		if(sfunctionBean.getUrl() != null && sfunctionBean.getUrl().trim().length()>0) { 
    			objectList.add(sfunctionBean.getUrl());
    			sqlWhere += " AND a.url = ? ";
    		} 
    		if(sfunctionBean.getFunctionGroupId() != null) { 
    			objectList.add(sfunctionBean.getFunctionGroupId());
    			sqlWhere += " AND a.function_group_id = ? ";
    		} 
    		if(sfunctionBean.getSort() != null) { 
    			objectList.add(sfunctionBean.getSort());
    			sqlWhere += " AND a.sort = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from s_function a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
