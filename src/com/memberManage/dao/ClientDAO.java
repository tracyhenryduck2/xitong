package com.memberManage.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.memberManage.bean.ClientBean;
                        
/**                     
 *                      
 * 客户 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class ClientDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, ClientBean clientBean) {   
    	String sql ="select a.* from client a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(clientBean!=null) { 
    		if(clientBean.getId() != null) { 
    			objectList.add(clientBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(clientBean.getUsername() != null && clientBean.getUsername().trim().length()>0) { 
    			objectList.add(clientBean.getUsername());
    			sqlWhere += " AND a.username = ? ";
    		} 
    		if(clientBean.getPassword() != null && clientBean.getPassword().trim().length()>0) { 
    			objectList.add(clientBean.getPassword());
    			sqlWhere += " AND a.password = ? ";
    		} 
    		if(clientBean.getImg() != null && clientBean.getImg().trim().length()>0) { 
    			objectList.add(clientBean.getImg());
    			sqlWhere += " AND a.img = ? ";
    		} 
    		if(clientBean.getEmail() != null && clientBean.getEmail().trim().length()>0) { 
    			objectList.add(clientBean.getEmail());
    			sqlWhere += " AND a.email = ? ";
    		} 
    		if(clientBean.getPhone() != null && clientBean.getPhone().trim().length()>0) { 
    			objectList.add(clientBean.getPhone());
    			sqlWhere += " AND a.phone = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from client a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
