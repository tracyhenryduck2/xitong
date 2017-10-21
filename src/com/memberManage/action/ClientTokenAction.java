package com.memberManage.action;

import java.util.List;
import java.util.Map;
import com.memberManage.bean.ClientTokenBean;
import com.memberManage.dao.ClientTokenDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 令牌表
 * @author xin.chou
 *
 */
public class ClientTokenAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ClientTokenDAO dao = new ClientTokenDAO(); 
    private ClientTokenBean clientTokenBean = new ClientTokenBean();    
    private final String tableDesc = "令牌表";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddClientToken() {
        if ("1".equals(oper)) {   
    	    clientTokenBean = dao.select(ClientTokenBean.class,clientTokenBean.getId());  
    	}    
    	return "toAddClientToken";    
    } 
 
    /**    
     * 新增
     */    
    public String addClientToken() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(clientTokenBean); 
            } else { 
                result = dao.insert(clientTokenBean); 
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
            "id","clientId","accessToken","refreshToken","expiresIn","expiresInRefresh"
            ,"time"
        };
        boolean result=dao.update(clientTokenBean,param);
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
    public String delClientToken() {  
    	try {
    		boolean result = dao.delete(ClientTokenBean.class,clientTokenBean.getId());  
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
    public String delClientTokens() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(ClientTokenBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, clientTokenBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public ClientTokenBean getClientTokenBean() { 
    	return clientTokenBean;    
    } 
 
    public void setClientTokenBean(ClientTokenBean clientTokenBean) {   
    	this.clientTokenBean = clientTokenBean;
    } 
}
