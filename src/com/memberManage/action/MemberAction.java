package com.memberManage.action;

import java.util.List;
import java.util.Map;
import com.memberManage.bean.MemberBean;
import com.memberManage.dao.MemberDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 客户管理
 * @author xin.chou
 *
 */
public class MemberAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private MemberDAO dao = new MemberDAO(); 
    private MemberBean memberBean = new MemberBean();    
    private final String tableDesc = "客户管理";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddMember() {
        if ("1".equals(oper)) {   
    	    memberBean = dao.select(MemberBean.class,memberBean.getId());  
    	}    
    	return "toAddMember";    
    } 
 
    /**    
     * 新增
     */    
    public String addMember() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(memberBean); 
            } else { 
                result = dao.insert(memberBean); 
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
            "id","hekrid","sex","phone","username","email"
            ,"ctime"
        };
        boolean result=dao.update(memberBean,param);
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
    public String delMember() {  
    	try {
    		boolean result = dao.delete(MemberBean.class,memberBean.getId());  
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
    public String delMembers() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(MemberBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, memberBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public MemberBean getMemberBean() { 
    	return memberBean;    
    } 
 
    public void setMemberBean(MemberBean memberBean) {   
    	this.memberBean = memberBean;
    } 
}
