package com.install.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.BaseActionSupport;
import com.install.bean.ProductBean;
import com.install.dao.ProductDAO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductAction extends BaseActionSupport {
	
	private static final long serialVersionUID = 1L;
    private ProductDAO dao = new ProductDAO(); 
    private ProductBean productBean = new ProductBean();  
    private String productno;
    
    
    public String getProductno() {
		return productno;
	}


	public void setProductno(String productno) {
		this.productno = productno;
	}


	public ProductBean getProductBean() {
		return productBean;
	}


	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}


	/**    
     * 查询列表页面  
     * @return  
     */    
    public void pproselct() {
    	productBean = dao.getInformation(productno);
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(productBean.getPname()!=null && !"".equals(productBean.getPname())){
    		map.put("content", JSONObject.fromObject(productBean));
	        map.put("result", "200");
    	    outPrintJSONObject(map);
    	}else{
    		map.put("result", "1000");
    	    outPrintJSONObject(map);
    	}
    	
    } 
    /**
     * 提交安装信息
     */
    public void psubmit() {
		
    	
    	
	}
}
