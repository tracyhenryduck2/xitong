package com.device.action;

import java.util.List;
import java.util.Map;
import com.device.bean.DeviceBean;
import com.device.dao.DeviceDAO;
import com.device.dao.ProductDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 设备表
 * @author junjun.xue
 *
 */
public class DeviceAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private DeviceDAO dao = new DeviceDAO(); 
    private ProductDAO productdao = new ProductDAO();
    private DeviceBean deviceBean = new DeviceBean();    
    private final String tableDesc = "设备表";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddDevice() {
    	
    	List<Map<String,Object>> lsit = productdao.getProductList();
    	request.setAttribute("List", lsit);
        if ("1".equals(oper)) {   
    	    deviceBean = dao.select(DeviceBean.class,deviceBean.getId());  
    	}    
    	return "toAddDevice";    
    } 
 
    /**    
     * 新增
     */    
    public String addDevice() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(deviceBean); 
            } else { 
                result = dao.insert(deviceBean); 
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
            "id","deviceid","token","ctrlkey","bindkey","mac"
            ,"binver","bintype","ip","online","pid"
            ,"name"
        };
        boolean result=dao.update(deviceBean,param);
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
    public String delDevice() {  
    	try {
    		boolean result = dao.delete(DeviceBean.class,deviceBean.getId());  
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
    public String delDevices() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(DeviceBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, deviceBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public DeviceBean getDeviceBean() { 
    	return deviceBean;    
    } 
 
    public void setDeviceBean(DeviceBean deviceBean) {   
    	this.deviceBean = deviceBean;
    } 
}
