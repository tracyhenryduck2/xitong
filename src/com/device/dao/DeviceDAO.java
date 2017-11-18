package com.device.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.device.bean.DeviceBean;
                        
/**                     
 *                      
 * 设备表 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class DeviceDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, DeviceBean deviceBean) {   
    	String sql ="select a.* from device a "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(deviceBean!=null) { 
    		if(deviceBean.getId() != null) { 
    			objectList.add(deviceBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(deviceBean.getDeviceid() != null && deviceBean.getDeviceid().trim().length()>0) { 
    			objectList.add(deviceBean.getDeviceid());
    			sqlWhere += " AND a.deviceid = ? ";
    		} 
    		if(deviceBean.getToken() != null && deviceBean.getToken().trim().length()>0) { 
    			objectList.add(deviceBean.getToken());
    			sqlWhere += " AND a.token = ? ";
    		} 
    		if(deviceBean.getCtrlkey() != null && deviceBean.getCtrlkey().trim().length()>0) { 
    			objectList.add(deviceBean.getCtrlkey());
    			sqlWhere += " AND a.ctrlkey = ? ";
    		} 
    		if(deviceBean.getBindkey() != null && deviceBean.getBindkey().trim().length()>0) { 
    			objectList.add(deviceBean.getBindkey());
    			sqlWhere += " AND a.bindkey = ? ";
    		} 
    		if(deviceBean.getMac() != null && deviceBean.getMac().trim().length()>0) { 
    			objectList.add(deviceBean.getMac());
    			sqlWhere += " AND a.mac = ? ";
    		} 
    		if(deviceBean.getBinver() != null && deviceBean.getBinver().trim().length()>0) { 
    			objectList.add(deviceBean.getBinver());
    			sqlWhere += " AND a.binver = ? ";
    		} 
    		if(deviceBean.getBintype() != null && deviceBean.getBintype().trim().length()>0) { 
    			objectList.add(deviceBean.getBintype());
    			sqlWhere += " AND a.bintype = ? ";
    		} 
    		if(deviceBean.getIp() != null && deviceBean.getIp().trim().length()>0) { 
    			objectList.add(deviceBean.getIp());
    			sqlWhere += " AND a.ip = ? ";
    		} 
    		if(deviceBean.getOnline() != null) { 
    			objectList.add(deviceBean.getOnline());
    			sqlWhere += " AND a.online = ? ";
    		} 
    		if(deviceBean.getPid() != null) { 
    			objectList.add(deviceBean.getPid());
    			sqlWhere += " AND a.pid = ? ";
    		} 
    		if(deviceBean.getName() != null && deviceBean.getName().trim().length()>0) { 
    			objectList.add(deviceBean.getName());
    			sqlWhere += " AND a.name = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from device a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }                   
}                       
