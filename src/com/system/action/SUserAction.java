package com.system.action;

import java.util.List;
import java.util.Map;
import com.system.bean.SUserBean;
import com.system.dao.SUserDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 管理员表
 * @author xin.chou
 *
 */
public class SUserAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private SUserDAO dao = new SUserDAO(); 
    private SUserBean sUserBean = new SUserBean();    
    private final String tableDesc = "管理员表";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddSUser() {
        if ("1".equals(oper)) {   
    	    sUserBean = dao.select(SUserBean.class,sUserBean.getId());  
    	}    
    	return "toAddSUser";    
    } 
 
    /**    
     * 新增
     */    
    public String addSUser() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(sUserBean); 
            } else { 
                result = dao.insert(sUserBean); 
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
            "id","username","password","type","createtime"
        };
        boolean result=dao.update(sUserBean,param);
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
    public String delSUser() {  
    	try {
    		boolean result = dao.delete(SUserBean.class,sUserBean.getId());  
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
    public String delSUsers() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(SUserBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, sUserBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public SUserBean getSUserBean() { 
    	return sUserBean;    
    } 
 
    public void setSUserBean(SUserBean sUserBean) {   
    	this.sUserBean = sUserBean;
    } 
}
