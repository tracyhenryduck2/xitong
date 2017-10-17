package com.system.action;

import java.util.List;
import java.util.Map;
import com.system.bean.SFunctionGroupBean;
import com.system.dao.SFunctionGroupDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 模块组
 * @author xin.chou
 *
 */
public class SFunctionGroupAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static SFunctionGroupDAO dao = new SFunctionGroupDAO(); 
    private SFunctionGroupBean sFunctionGroupBean = new SFunctionGroupBean();    
    private final String tableDesc = "模块组";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddSFunctionGroup() {
        if ("1".equals(oper)) {   
    	    sFunctionGroupBean = dao.select(SFunctionGroupBean.class,sFunctionGroupBean.getId());  
    	}    
    	return "toAddSFunctionGroup";    
    } 
 
    /**    
     * 新增
     */    
    public String addSFunctionGroup() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(sFunctionGroupBean); 
            } else { 
                result = dao.insert(sFunctionGroupBean); 
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
            "id","name","sort","parentId"
        };
        boolean result=dao.update(sFunctionGroupBean,param);
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
    public String delSFunctionGroup() {  
    	try {
    		boolean result = dao.delete(SFunctionGroupBean.class,sFunctionGroupBean.getId());  
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
    public String delSFunctionGroups() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(SFunctionGroupBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, sFunctionGroupBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public static List<Map<String,Object>> getMenulist(Long authority){
    	
    	return dao.getFunctionGroupList(authority);
    }
    
    public SFunctionGroupBean getSFunctionGroupBean() { 
    	return sFunctionGroupBean;    
    } 
 
    public void setSFunctionGroupBean(SFunctionGroupBean sFunctionGroupBean) {   
    	this.sFunctionGroupBean = sFunctionGroupBean;
    } 
}
