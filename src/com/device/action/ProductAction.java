package com.device.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.device.bean.ProductBean;
import com.device.dao.ProductDAO;
import com.common.Common;
import com.common.MD5;
import com.common.BaseActionSupport;

/**
 * 产品表
 * @author junjun.xue
 *
 */
public class ProductAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ProductDAO dao = new ProductDAO(); 
    private ProductBean productBean = new ProductBean();    
    private final String tableDesc = "产品表";
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddProduct() {
        if ("1".equals(oper)) {   
    	    productBean = dao.select(ProductBean.class,productBean.getId());  
    	}    
    	return "toAddProduct";    
    } 
 
    /**    
     * 新增
     */    
    public String addProduct() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(productBean); 
            } else {
            	Long saveid = dao.save(productBean); 
            	Long time = new Date().getTime();
            	result =dao.updateProdkey(saveid,MD5.get32MD5(String.valueOf(time)),time/1000);
     
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
            "id","name","prodKey","model","desp","img"
            ,"forceBind","ctime"
        };
        boolean result=dao.update(productBean,param);
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
    public String delProduct() {  
    	try {
    		boolean result = dao.delete(ProductBean.class,productBean.getId());  
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
    public String delProducts() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(ProductBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, productBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public ProductBean getProductBean() { 
    	return productBean;    
    } 
 
    public void setProductBean(ProductBean productBean) {   
    	this.productBean = productBean;
    } 
}
