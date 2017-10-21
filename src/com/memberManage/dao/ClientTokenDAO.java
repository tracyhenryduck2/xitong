package com.memberManage.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.memberManage.bean.ClientTokenBean;
                        
/**                     
 *                      
 * 令牌表 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class ClientTokenDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, ClientTokenBean clienttokenBean) {   
    	String sql ="select a.* from client_token a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(clienttokenBean!=null) { 
    		if(clienttokenBean.getId() != null) { 
    			objectList.add(clienttokenBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(clienttokenBean.getClientId() != null) { 
    			objectList.add(clienttokenBean.getClientId());
    			sqlWhere += " AND a.client_id = ? ";
    		} 
    		if(clienttokenBean.getAccessToken() != null && clienttokenBean.getAccessToken().trim().length()>0) { 
    			objectList.add(clienttokenBean.getAccessToken());
    			sqlWhere += " AND a.access_token = ? ";
    		} 
    		if(clienttokenBean.getRefreshToken() != null && clienttokenBean.getRefreshToken().trim().length()>0) { 
    			objectList.add(clienttokenBean.getRefreshToken());
    			sqlWhere += " AND a.refresh_token = ? ";
    		} 
    		if(clienttokenBean.getExpiresIn() != null) { 
    			objectList.add(clienttokenBean.getExpiresIn());
    			sqlWhere += " AND a.expires_in = ? ";
    		} 
    		if(clienttokenBean.getExpiresInRefresh() != null) { 
    			objectList.add(clienttokenBean.getExpiresInRefresh());
    			sqlWhere += " AND a.expires_in_refresh = ? ";
    		} 
    		if(clienttokenBean.getTime() != null) { 
    			objectList.add(clienttokenBean.getTime());
    			sqlWhere += " AND a.time = ? ";
    		} 
    		if(clienttokenBean.getClientType() != null) { 
    			objectList.add(clienttokenBean.getClientType());
    			sqlWhere += " AND a.client_type = ? ";
    		}  
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from client_token a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
