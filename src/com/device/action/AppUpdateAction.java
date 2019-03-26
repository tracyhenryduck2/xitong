package com.device.action;

import java.util.List;
import java.util.Map;
import com.device.bean.AppUpdateBean;
import com.device.dao.AppUpdateDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 
 * @author junjun.xue
 *
 */
public class AppUpdateAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private AppUpdateDAO dao = new AppUpdateDAO(); 
    private AppUpdateBean appUpdateBean = new AppUpdateBean();    
    private final String tableDesc = "";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddAppUpdate() {
        if ("1".equals(oper)) {   
    	    appUpdateBean = dao.select(AppUpdateBean.class,appUpdateBean.getId());  
    	}    
    	return "toAddAppUpdate";    
    } 
 
    /**    
     * 新增
     */    
    public String addAppUpdate() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(appUpdateBean); 
            } else { 
                result = dao.insert(appUpdateBean); 
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
            "id","version","code","url","en","zh"
            ,"fr","de","es","nl","fi"
            ,"sl","it","cs","urlEx"
        };
        boolean result=dao.update(appUpdateBean,param);
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
    public String delAppUpdate() {  
    	try {
    		boolean result = dao.delete(AppUpdateBean.class,appUpdateBean.getId());  
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
    public String delAppUpdates() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(AppUpdateBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, appUpdateBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public AppUpdateBean getAppUpdateBean() { 
    	return appUpdateBean;    
    } 
 
    public void setAppUpdateBean(AppUpdateBean appUpdateBean) {   
    	this.appUpdateBean = appUpdateBean;
    } 
}
