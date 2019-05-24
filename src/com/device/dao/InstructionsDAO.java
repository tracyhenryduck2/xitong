package com.device.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.device.bean.InstructionsBean;
                        
/**                     
 *                      
 *  <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class InstructionsDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, InstructionsBean instructionsBean) {   
    	String sql ="select a.* from instructions a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(instructionsBean!=null) { 
    		if(instructionsBean.getId() != null) { 
    			objectList.add(instructionsBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(instructionsBean.getName() != null && instructionsBean.getName().trim().length()>0) { 
    			objectList.add(instructionsBean.getName());
    			sqlWhere += " AND a.name = ? ";
    		} 
    		if(instructionsBean.getFileUrl() != null && instructionsBean.getFileUrl().trim().length()>0) { 
    			objectList.add(instructionsBean.getFileUrl());
    			sqlWhere += " AND a.file_url = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from instructions a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }
    
    public Map<String,Object> getInstructionFile(String instruction_name) {
    	String sql ="select a.* from instructions a where a.name = '"+instruction_name+"'"; 
    	
    	return j.queryForMap(sql);
    }
}                       
