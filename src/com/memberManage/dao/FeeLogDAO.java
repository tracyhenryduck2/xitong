package com.memberManage.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.memberManage.bean.FeeLogBean;
import com.memberManage.bean.MemberBean;
                        
/**                     
 *                      
 * 消费记录 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class FeeLogDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, FeeLogBean feelogBean,MemberBean memberbean) {   
    	String sql ="select a.*,b.username from fee_log a left join member b on a.user_id = b.id"; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(feelogBean!=null) { 
    		if(feelogBean.getId() != null) { 
    			objectList.add(feelogBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(feelogBean.getFeeMoney() != null ) { 
    			objectList.add(feelogBean.getFeeMoney());
    			sqlWhere += " AND a.fee_money = ? ";
    		} 
    		if(feelogBean.getFeeTime() != null) { 
    			objectList.add(feelogBean.getFeeTime());
    			sqlWhere += " AND a.fee_time = ? ";
    		} 
    		if(feelogBean.getInitStorage() != null ) { 
    			objectList.add(feelogBean.getInitStorage());
    			sqlWhere += " AND a.init_storage = ? ";
    		} 
    		if(feelogBean.getUserId() != null) { 
    			objectList.add(feelogBean.getUserId());
    			sqlWhere += " AND a.user_id = ? ";
    		}
    		if(memberbean.getUsername() != null&&memberbean.getUsername().trim().length()>0) { 
    			objectList.add(memberbean.getUsername());
    			sqlWhere += " AND b.username = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from fee_log a left join member b on a.user_id = b.id "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
