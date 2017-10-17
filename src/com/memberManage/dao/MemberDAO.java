package com.memberManage.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.memberManage.bean.MemberBean;
                        
/**                     
 *                      
 * 客户管理 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class MemberDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, MemberBean memberBean) {   
    	String sql ="select a.* from member a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(memberBean!=null) { 
    		if(memberBean.getId() != null) { 
    			objectList.add(memberBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(memberBean.getHekrid() != null && memberBean.getHekrid().trim().length()>0) { 
    			objectList.add(memberBean.getHekrid());
    			sqlWhere += " AND a.hekrid = ? ";
    		} 
    		if(memberBean.getSex() != null) { 
    			objectList.add(memberBean.getSex());
    			sqlWhere += " AND a.sex = ? ";
    		} 
    		if(memberBean.getPhone() != null && memberBean.getPhone().trim().length()>0) { 
    			objectList.add(memberBean.getPhone());
    			sqlWhere += " AND a.phone = ? ";
    		} 
    		if(memberBean.getUsername() != null && memberBean.getUsername().trim().length()>0) { 
    			objectList.add(memberBean.getUsername());
    			sqlWhere += " AND a.username = ? ";
    		} 
    		if(memberBean.getEmail() != null && memberBean.getEmail().trim().length()>0) { 
    			objectList.add(memberBean.getEmail());
    			sqlWhere += " AND a.email = ? ";
    		} 
    		if(memberBean.getCtime() != null) { 
    			objectList.add(memberBean.getCtime());
    			sqlWhere += " AND a.ctime = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from member a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
