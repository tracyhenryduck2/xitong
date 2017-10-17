package com.memberManage.action;

import java.util.List;
import java.util.Map;
import com.memberManage.bean.FeeLogBean;
import com.memberManage.bean.MemberBean;
import com.memberManage.dao.FeeLogDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 消费记录
 * @author xin.chou
 *
 */
public class FeeLogAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private FeeLogDAO dao = new FeeLogDAO(); 
    private MemberBean memberBean = new MemberBean(); 
    private FeeLogBean feeLogBean = new FeeLogBean();    
    private final String tableDesc = "消费记录";

 
    /**    
     * 编辑部分字段专用
     * @return
     */
    public String addTest2(){
        showMessage = "编辑2"+tableDesc;
        String[] param={
            "id","feeMoney","chargeTime","initStorage","userId"
        };
        boolean result=dao.update(feeLogBean,param);
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
    public String delFeeLog() {  
    	try {
    		boolean result = dao.delete(FeeLogBean.class,feeLogBean.getId());  
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
    public String delFeeLogs() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(FeeLogBean.class,ids);
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
    	System.out.println("memberBean.getUsername():"+memberBean.getUsername());
    	List<Map<String, Object>> list = dao.getPageList(page, feeLogBean,memberBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public FeeLogBean getFeeLogBean() { 
    	return feeLogBean;    
    } 
 
    public void setFeeLogBean(FeeLogBean feeLogBean) {   
    	this.feeLogBean = feeLogBean;
    }
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	} 
    
    
}
