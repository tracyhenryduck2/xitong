package com.memberManage.action;

import java.util.List;
import java.util.Map;
import com.memberManage.bean.ChargeLogBean;
import com.memberManage.dao.ChargeLogDAO;
import com.common.Common;
import com.common.BaseActionSupport;
import com.memberManage.bean.MemberBean;

/**
 * 充值记录
 * @author xin.chou
 *
 */
public class ChargeLogAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ChargeLogDAO dao = new ChargeLogDAO(); 
    private ChargeLogBean chargeLogBean = new ChargeLogBean();    
    private MemberBean memberBean = new MemberBean(); 
    private final String tableDesc = "充值记录";

 
 
    /**    
     * 编辑部分字段专用
     * @return
     */
    public String addTest2(){
        showMessage = "编辑2"+tableDesc;
        String[] param={
            "id","chargeMoney","chargeTime","initStorage","userId"
        };
        boolean result=dao.update(chargeLogBean,param);
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
    public String delChargeLog() {  
    	try {
    		boolean result = dao.delete(ChargeLogBean.class,chargeLogBean.getId());  
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
    public String delChargeLogs() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(ChargeLogBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, chargeLogBean,memberBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public ChargeLogBean getChargeLogBean() { 
    	return chargeLogBean;    
    } 
 
    public void setChargeLogBean(ChargeLogBean chargeLogBean) {   
    	this.chargeLogBean = chargeLogBean;
    } 
    
    public MemberBean getMemberBean(){
    	return memberBean;
    }
    
    public void setMemberBean(MemberBean memberBean) {   
    	this.memberBean = memberBean;
    } 
}
