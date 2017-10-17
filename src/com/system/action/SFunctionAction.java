package com.system.action;

import java.util.List;
import java.util.Map;
import com.system.bean.SFunctionBean;
import com.system.dao.SFunctionDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 功能
 * @author xin.chou
 *
 */
public class SFunctionAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private SFunctionDAO dao = new SFunctionDAO(); 
    private SFunctionBean sFunctionBean = new SFunctionBean();    
    private final String tableDesc = "功能";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddSFunction() {
        if ("1".equals(oper)) {   
    	    sFunctionBean = dao.select(SFunctionBean.class,sFunctionBean.getId());  
    	}    
    	return "toAddSFunction";    
    } 
 
    /**    
     * 新增
     */    
    public String addSFunction() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(sFunctionBean); 
            } else { 
                result = dao.insert(sFunctionBean); 
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
            "id","name","url","functionGroupId","sort"
        };
        boolean result=dao.update(sFunctionBean,param);
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
    public String delSFunction() {  
    	try {
    		boolean result = dao.delete(SFunctionBean.class,sFunctionBean.getId());  
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
    public String delSFunctions() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(SFunctionBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, sFunctionBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
    
 
    public SFunctionBean getSFunctionBean() { 
    	return sFunctionBean;    
    } 
 
    public void setSFunctionBean(SFunctionBean sFunctionBean) {   
    	this.sFunctionBean = sFunctionBean;
    } 
}
