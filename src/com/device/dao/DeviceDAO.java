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
    		if(deviceBean.getDevTid() != null && deviceBean.getDevTid().trim().length()>0) { 
    			objectList.add(deviceBean.getDevTid());
    			sqlWhere += " AND a.dev_tid = ? ";
    		} 
    		if(deviceBean.getToken() != null && deviceBean.getToken().trim().length()>0) { 
    			objectList.add(deviceBean.getToken());
    			sqlWhere += " AND a.token = ? ";
    		} 
    		if(deviceBean.getCtrlKey() != null && deviceBean.getCtrlKey().trim().length()>0) { 
    			objectList.add(deviceBean.getCtrlKey());
    			sqlWhere += " AND a.ctrl_key = ? ";
    		} 
    		if(deviceBean.getBindKey() != null && deviceBean.getBindKey().trim().length()>0) { 
    			objectList.add(deviceBean.getBindKey());
    			sqlWhere += " AND a.bind_key = ? ";
    		} 
    		if(deviceBean.getMac() != null && deviceBean.getMac().trim().length()>0) { 
    			objectList.add(deviceBean.getMac());
    			sqlWhere += " AND a.mac = ? ";
    		} 
    		if(deviceBean.getBinVer() != null && deviceBean.getBinVer().trim().length()>0) { 
    			objectList.add(deviceBean.getBinVer());
    			sqlWhere += " AND a.bin_ver = ? ";
    		} 
    		if(deviceBean.getBinType() != null && deviceBean.getBinType().trim().length()>0) { 
    			objectList.add(deviceBean.getBinType());
    			sqlWhere += " AND a.bin_type = ? ";
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
    		if(deviceBean.getSsid() != null && deviceBean.getSsid().trim().length()>0) { 
    			objectList.add(deviceBean.getSsid());
    			sqlWhere += " AND a.ssid = ? ";
    		} 
    		if(deviceBean.getCtime() != null) { 
    			objectList.add(deviceBean.getCtime());
    			sqlWhere += " AND a.ctime = ? ";
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
