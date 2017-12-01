package com.device.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.device.bean.ClientParamBean;
                        
/**                     
 *                      
 * 参数表 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class ClientParamDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(ClientParamBean clientparamBean) {   
    	String sql ="select a.* from client_param a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(clientparamBean!=null) { 
    		if(clientparamBean.getId() != null) { 
    			objectList.add(clientparamBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(clientparamBean.getName() != null && clientparamBean.getName().trim().length()>0) { 
    			objectList.add(clientparamBean.getName());
    			sqlWhere += " AND a.name = ? ";
    		} 
    		if(clientparamBean.getType() != null) { 
    			objectList.add(clientparamBean.getType());
    			sqlWhere += " AND a.type = ? ";
    		} 
    		if(clientparamBean.getMin() != null) { 
    			objectList.add(clientparamBean.getMin());
    			sqlWhere += " AND a.min = ? ";
    		} 
    		if(clientparamBean.getMax() != null) { 
    			objectList.add(clientparamBean.getMax());
    			sqlWhere += " AND a.max = ? ";
    		} 
    		if(clientparamBean.getAbandoned() != null) { 
    			objectList.add(clientparamBean.getAbandoned());
    			sqlWhere += " AND a.abandoned = ? ";
    		} 
    		if(clientparamBean.getCtime() != null) { 
    			objectList.add(clientparamBean.getCtime());
    			sqlWhere += " AND a.ctime = ? ";
    		} 
    		if(clientparamBean.getAtime() != null) { 
    			objectList.add(clientparamBean.getAtime());
    			sqlWhere += " AND a.atime = ? ";
    		} 
    		if(clientparamBean.getPid() != null) { 
    			objectList.add(clientparamBean.getPid());
    			sqlWhere += " AND a.pid = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray();    
    		sql += " order by atime desc";                  
    	List<Map<String,Object>> list=j.queryForList(sql,pram);  
    	return list;      
    } 
    
    
    public boolean setParams(String ids,Long time,Long bs){
    	String sql = "update client_param set abandoned = "+bs+",atime="+time+" where id in("+ids+")";
    	
    	return j.execute(sql);
    	
    }
    
    public Long getParamName(String ds,Long pid){
    	String sql = "select count(*) from client_param where name=? and pid=?";
    	Object[] params = {ds,pid};
    	
    	return j.queryForLong(sql, params);
    	
    	
    	
    }
    
}                       
