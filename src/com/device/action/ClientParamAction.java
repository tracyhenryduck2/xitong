package com.device.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.device.bean.ClientParamBean;
import com.device.bean.DeviceBean;
import com.device.dao.ClientParamDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 参数表
 * @author junjun.xue
 *
 */
public class ClientParamAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ClientParamDAO dao = new ClientParamDAO(); 
    private ClientParamBean clientParamBean = new ClientParamBean();    
    private final String tableDesc = "参数表";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddClientParam() {
        if("1".equals(oper)){
        	clientParamBean = dao.select(ClientParamBean.class,clientParamBean.getId());  
        } 
    	return "toAddClientParam";    
    } 
 
    /**    
     * 新增
     */    
    public String addClientParam() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;
            
            if(dao.getParamName(clientParamBean.getName(),clientParamBean.getPid())!=0){
                showMessage = "名称重复";  
                return error; 
            }else{
                clientParamBean.setAbandoned(0l);
                clientParamBean.setCtime(new Date().getTime()/1000);
                result = dao.insert(clientParamBean); 
                if (result) {  
                    showMessage += "成功";  
                    return reload_success; 
                } else {
                    showMessage += "失败";  
                    return error;   
                }
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
            "id","name","type","min","max","abandoned"
            ,"ctime","atime","pid"
        };
        boolean result=dao.update(clientParamBean,param);
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
    public String delClientParam() {  
    	try {
    		boolean result = dao.delete(ClientParamBean.class,clientParamBean.getId());  
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
    public String delClientParams() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(ClientParamBean.class,ids);
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
    
    
    public String abandonClientParams(){
    	
    	try{
       		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.setParams(ids,new Date().getTime()/1000, 1l);
    		if(result){
    		    showMessage = "删除"+tableDesc+"成功"; //reload   
    		    return reload_success;  
    		}else{
    		    showMessage = "删除"+tableDesc+"失败";  
    		    return error; 
    		}
    		
    	}catch(Exception e){
    		
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
    	List<Map<String, Object>> list = dao.getPageList(clientParamBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public ClientParamBean getClientParamBean() { 
    	return clientParamBean;    
    } 
 
    public void setClientParamBean(ClientParamBean clientParamBean) {   
    	this.clientParamBean = clientParamBean;
    } 
}
