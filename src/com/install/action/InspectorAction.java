package com.install.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.BaseActionSupport;
import com.google.gson.JsonObject;
import com.install.bean.InspectorBean;
import com.install.dao.InspectorDAO;

import net.sf.json.JSONArray;
import oracle.net.aso.e;

public class InspectorAction extends BaseActionSupport {
	/**
	 * 巡检记录表
	 */
	private static final long serialVersionUID = 1L;
    private InspectorDAO dao = new InspectorDAO(); 
    private InspectorBean inspectorBean = new InspectorBean();    
    private final String tableDesc = "巡检记录";
    private String type;
    private String inspectorname;
    
    public String getInspectorname() {
		return inspectorname;
	}

	public void setInspectorname(String inspectorname) {
		this.inspectorname = inspectorname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**    
     * 查询列表页面  
     * @return  
     */    
    public String list() {
    	page.execute(request, "ID", "desc");
    	List<Map<String, Object>> list = dao.getPageList(page, inspectorBean);
    	request.setAttribute("list", list);   
    	return "list";    
    }
   
    /**
     * 查询自己的检测记录
     * @return
     */
    public void plist() {
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(type != null && type.length()>0 && "2".equalsIgnoreCase(type)){
    		
    		if(inspectorname != null && inspectorname.length()>0){
    			List<Map<String, Object>> list = dao.getPeoplePageList(inspectorname);
    			map.put("content", JSONArray.fromObject(list));
    			map.put("result", 200);
    			outPrintJSONObject(map);
    		}else {
    			map.put("result", 3002);
        		outPrintJSONObject(map);
			}
    		
    	}else{
    		map.put("result", 3001);
    		outPrintJSONObject(map);
    	}
    	
	}

	public InspectorBean getInspectorBean() {
		return inspectorBean;
	}

	public void setInspectorBean(InspectorBean inspectorBean) {
		this.inspectorBean = inspectorBean;
	} 
    

}
