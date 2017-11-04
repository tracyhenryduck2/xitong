package com.device.action;

import java.util.List;
import java.util.Map;
import com.device.bean.DeviceTypeBean;
import com.device.dao.DeviceTypeDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 设备种类
 * @author junjun.xue
 *
 */
public class DeviceTypeAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private DeviceTypeDAO dao = new DeviceTypeDAO(); 
    private DeviceTypeBean deviceTypeBean = new DeviceTypeBean();    
    private final String tableDesc = "设备种类";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddDeviceType() {
        if ("1".equals(oper)) {   
    	    deviceTypeBean = dao.select(DeviceTypeBean.class,deviceTypeBean.getId());  
    	}    
    	return "toAddDeviceType";    
    } 
 
    /**    
     * 新增
     */    
    public String addDeviceType() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(deviceTypeBean); 
            } else { 
                result = dao.insert(deviceTypeBean); 
            }
            if (result) {  
                showMessage += "成功";  
                return reload_success; 
            } else {
                showMessage += "失败";  
                return error;   
            }  
        } catch (Exception e) {    
            showMessage = "数据异常，操作失败";   
            return error;  
        } 
    } 
 
    /**    
     * 编辑部分字段专用
     * @return
     */
    public String addTest2(){
        showMessage = "编辑2"+tableDesc;
        String[] param={
            "id","name","pid","model","desp","img"
        };
        boolean result=dao.update(deviceTypeBean,param);
        if (result) { 
            showMessage += "成功";
            return reload_success;
        } else {
            showMessage += "失败"; 
           return error; 
        }
    }
    /**    
     * 删除操作 
     */    
    public String delDeviceType() {  
    	try {
    		boolean result = dao.delete(DeviceTypeBean.class,deviceTypeBean.getId());  
    		if (result) {
    		    showMessage = "删除"+tableDesc+"成功"; //reload   
    		    return reload_success;  
    		} else {
    		    showMessage = "删除"+tableDesc+"失败";  
    		    return error; 
    		}  
    	} catch (Exception e) {  
    		return exception; 
    	}    
    } 
 
 
    /**    
     * 删除操作 
     */    
    public String delDeviceTypes() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(DeviceTypeBean.class,ids);
    		if (result) {
    		    showMessage = "删除"+tableDesc+"成功"; //reload   
    		    return reload_success;  
    		} else {
    		    showMessage = "删除"+tableDesc+"失败";  
    		    return error; 
    		}  
    	} catch (Exception e) {  
    		return exception; 
    	}    
    } 
    public String search() {
        return "search"; 
    }
 
    /**    
     * 查询列表页面  
     * @return  
     */    
    public String list() {
    	page.execute(request, "ID", "desc");
    	List<Map<String, Object>> list = dao.getPageList(page, deviceTypeBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public DeviceTypeBean getDeviceTypeBean() { 
    	return deviceTypeBean;    
    } 
 
    public void setDeviceTypeBean(DeviceTypeBean deviceTypeBean) {   
    	this.deviceTypeBean = deviceTypeBean;
    } 
}
