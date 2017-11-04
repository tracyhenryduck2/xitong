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
    		if(clientBean.getEmail() != null && clientBean.getEmail().trim().length()>0) { 
    			objectList.add(clientBean.getEmail());
    			sqlWhere += " AND a.email = ? ";
    		} 
    		if(clientBean.getPhoneNumber() != null && clientBean.getPhoneNumber().trim().length()>0) { 
    			objectList.add(clientBean.getPhoneNumber());
    			sqlWhere += " AND a.phone_number = ? ";
    		} 
    		if(clientBean.getBirthday() != null) { 
    			objectList.add(clientBean.getBirthday());
    			sqlWhere += " AND a.birthday = ? ";
    		} 
    		if(clientBean.getFirstName() != null && clientBean.getFirstName().trim().length()>0) { 
    			objectList.add(clientBean.getFirstName());
    			sqlWhere += " AND a.first_name = ? ";
    		} 
    		if(clientBean.getLastName() != null && clientBean.getLastName().trim().length()>0) { 
    			objectList.add(clientBean.getLastName());
    			sqlWhere += " AND a.last_name = ? ";
    		} 
    		if(clientBean.getRender() != null && clientBean.getRender().trim().length()>0) { 
    			objectList.add(clientBean.getRender());
    			sqlWhere += " AND a.render = ? ";
    		} 
    		if(clientBean.getAge() != null) { 
    			objectList.add(clientBean.getAge());
    			sqlWhere += " AND a.age = ? ";
    		} 
    		if(clientBean.getDescription() != null && clientBean.getDescription().trim().length()>0) { 
    			objectList.add(clientBean.getDescription());
    			sqlWhere += " AND a.description = ? ";
    		} 
    		if(clientBean.getSmall() != null && clientBean.getSmall().trim().length()>0) { 
    			objectList.add(clientBean.getSmall());
    			sqlWhere += " AND a.small = ? ";
    		} 
    		if(clientBean.getBig() != null && clientBean.getBig().trim().length()>0) { 
    			objectList.add(clientBean.getBig());
    			sqlWhere += " AND a.big = ? ";
    		} 
    		if(clientBean.getUpdateDate() != null) { 
    			objectList.add(clientBean.getUpdateDate());
    			sqlWhere += " AND a.update_date = ? ";
    		}
    		if(clientBean.getCtime() != null) { 
    			objectList.add(clientBean.getCtime());
    			sqlWhere += " AND a.ctime = ? ";
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
