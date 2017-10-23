package com.memberManage.action;

import java.util.List;
import java.util.Map;
import com.memberManage.bean.ClientBean;
import com.memberManage.dao.ClientDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 客户
 * @author junjun.xue
 *
 */
public class ClientAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ClientDAO dao = new ClientDAO(); 
    private ClientBean clientBean = new ClientBean();    
    private final String tableDesc = "客户";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddClient() {
        if ("1".equals(oper)) {   
    	    clientBean = dao.select(ClientBean.class,clientBean.getId());  
    	}    
    	return "toAddClient";    
    } 
 
    /**    
     * 新增
     */    
    public String addClient() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(clientBean); 
            } else { 
                result = dao.insert(clientBean); 
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
            "id","username","password","email","phoneNumber","birthday"
            ,"firstName","lastName","render","age","description"
            ,"small","big","updateDate"
        };
        boolean result=dao.update(clientBean,param);
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
    public String delClient() {  
    	try {
    		boolean result = dao.delete(ClientBean.class,clientBean.getId());  
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
    public String delClients() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(ClientBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, clientBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public ClientBean getClientBean() { 
    	return clientBean;    
    } 
 
    public void setClientBean(ClientBean clientBean) {   
    	this.clientBean = clientBean;
    } 
}
