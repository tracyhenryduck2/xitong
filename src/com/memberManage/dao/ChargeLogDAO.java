package com.memberManage.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.memberManage.bean.ChargeLogBean;
import com.memberManage.bean.MemberBean;
                        
/**                     
 *                      
 * 充值记录 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class ChargeLogDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, ChargeLogBean chargelogBean,MemberBean memberbean) {   
    	String sql ="select a.*,b.username from charge_log a left join member b on a.user_id = b.id"; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(chargelogBean!=null) { 
    		if(chargelogBean.getId() != null) { 
    			objectList.add(chargelogBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(chargelogBean.getChargeMoney() != null) { 
    			objectList.add(chargelogBean.getChargeMoney());
    			sqlWhere += " AND a.charge_money = ? ";
    		} 
    		if(chargelogBean.getChargeTime() != null) { 
    			objectList.add(chargelogBean.getChargeTime());
    			sqlWhere += " AND a.charge_time = ? ";
    		} 
    		if(chargelogBean.getInitStorage() != null ) { 
    			objectList.add(chargelogBean.getInitStorage());
    			sqlWhere += " AND a.init_storage = ? ";
    		} 
    		if(chargelogBean.getUserId() != null) { 
    			objectList.add(chargelogBean.getUserId());
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
    	page.setTotalRows(j.queryForInteger("select count(*) from charge_log a left join member b on a.user_id = b.id "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }
    
}                       
