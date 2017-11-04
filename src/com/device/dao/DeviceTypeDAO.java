package com.device.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.device.bean.DeviceTypeBean;
                        
/**                     
 *                      
 * 设备种类 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class DeviceTypeDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, DeviceTypeBean devicetypeBean) {   
    	String sql ="select a.* from device_type a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(devicetypeBean!=null) { 
    		if(devicetypeBean.getId() != null) { 
    			objectList.add(devicetypeBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(devicetypeBean.getName() != null && devicetypeBean.getName().trim().length()>0) { 
    			objectList.add(devicetypeBean.getName());
    			sqlWhere += " AND a.name = ? ";
    		} 
    		if(devicetypeBean.getPid() != null && devicetypeBean.getPid().trim().length()>0) { 
    			objectList.add(devicetypeBean.getPid());
    			sqlWhere += " AND a.pid = ? ";
    		} 
    		if(devicetypeBean.getModel() != null && devicetypeBean.getModel().trim().length()>0) { 
    			objectList.add(devicetypeBean.getModel());
    			sqlWhere += " AND a.model = ? ";
    		} 
    		if(devicetypeBean.getDesp() != null && devicetypeBean.getDesp().trim().length()>0) { 
    			objectList.add(devicetypeBean.getDesp());
    			sqlWhere += " AND a.desp = ? ";
    		} 
    		if(devicetypeBean.getImg() != null && devicetypeBean.getImg().trim().length()>0) { 
    			objectList.add(devicetypeBean.getImg());
    			sqlWhere += " AND a.img = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from device_type a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
