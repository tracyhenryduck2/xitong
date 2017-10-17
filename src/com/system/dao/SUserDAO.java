package com.system.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.system.bean.SUserBean;
                        
/**                     
 *                      
 * 管理员表 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class SUserDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, SUserBean suserBean) {   
    	String sql ="select a.* from s_user a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(suserBean!=null) { 
    		if(suserBean.getId() != null) { 
    			objectList.add(suserBean.getId());
    			sqlWhere += " AND a.Id = ? ";
    		} 
    		if(suserBean.getUsername() != null && suserBean.getUsername().trim().length()>0) { 
    			objectList.add(suserBean.getUsername());
    			sqlWhere += " AND a.username = ? ";
    		} 
    		if(suserBean.getPassword() != null && suserBean.getPassword().trim().length()>0) { 
    			objectList.add(suserBean.getPassword());
    			sqlWhere += " AND a.password = ? ";
    		} 
    		if(suserBean.getType() != null) { 
    			objectList.add(suserBean.getType());
    			sqlWhere += " AND a.type = ? ";
    		} 
    		if(suserBean.getCreatetime() != null) { 
    			objectList.add(suserBean.getCreatetime());
    			sqlWhere += " AND a.createtime = ? ";
    		} 
    		if(suserBean.getPhone() != null) { 
    			objectList.add(suserBean.getPhone());
    			sqlWhere += " AND a.phone = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from s_user a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }
    
	public SUserBean selectUser(String username, String password) {
		Object[] params={username,password};
		return j.queryForBean(
				SUserBean.class,
				"SELECT * from s_user WHERE username=? and password=?",
				params);
	}
	
	
	public SUserBean hasRegisterUser(String username) {
		Object[] params={username};
		return j.queryForBean(
				SUserBean.class,
				"SELECT * from s_user WHERE username=?",
				params);
	}
	
	public SUserBean hasRegisterPhone(String phone) {
		Object[] params={phone};
		return j.queryForBean(
				SUserBean.class,
				"SELECT * from s_user WHERE phone=?",
				params);
	}
}                       
