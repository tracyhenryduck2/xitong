package com.device.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.device.bean.ProductBean;
                        
/**                     
 *                      
 * 产品表 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class ProductDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, ProductBean productBean) {   
    	String sql ="select a.* from product a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(productBean!=null) { 
    		if(productBean.getId() != null) { 
    			objectList.add(productBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(productBean.getName() != null && productBean.getName().trim().length()>0) { 
    			objectList.add(productBean.getName());
    			sqlWhere += " AND a.name = ? ";
    		} 
    		if(productBean.getProdKey() != null && productBean.getProdKey().trim().length()>0) { 
    			objectList.add(productBean.getProdKey());
    			sqlWhere += " AND a.prod_key = ? ";
    		} 
    		if(productBean.getModel() != null && productBean.getModel().trim().length()>0) { 
    			objectList.add(productBean.getModel());
    			sqlWhere += " AND a.model = ? ";
    		} 
    		if(productBean.getDesp() != null && productBean.getDesp().trim().length()>0) { 
    			objectList.add(productBean.getDesp());
    			sqlWhere += " AND a.desp = ? ";
    		} 
    		if(productBean.getImg() != null && productBean.getImg().trim().length()>0) { 
    			objectList.add(productBean.getImg());
    			sqlWhere += " AND a.img = ? ";
    		} 
    		if(productBean.getForceBind() != null) { 
    			objectList.add(productBean.getForceBind());
    			sqlWhere += " AND a.force_bind = ? ";
    		} 
    		if(productBean.getCtime() != null) { 
    			objectList.add(productBean.getCtime());
    			sqlWhere += " AND a.ctime = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from product a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    } 
    
    
   public List<Map<String,Object>> getList(){
	   String sql = "select id,name from product";
	   return j.queryForList(sql);
	   
   }
   
   public boolean updateProdkey(Long id,String prodkey,Long ctime){
	    
	   String sql = "update product set ctime=?,prod_key=? where id=?";
	   Object[] params = {ctime,prodkey,id};
	   
	   return j.execute(sql, params);
	   
   }
}                       
