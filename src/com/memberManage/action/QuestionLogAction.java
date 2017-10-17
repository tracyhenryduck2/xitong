package com.memberManage.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.memberManage.bean.QuestionLogBean;
import com.memberManage.dao.QuestionLogDAO;
import com.system.bean.SUserBean;
import com.common.Common;
import com.common.BaseActionSupport;
import com.common.ExportExcel;

/**
 * 问题记录
 * @author xin.chou
 *
 */
public class QuestionLogAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private QuestionLogDAO dao = new QuestionLogDAO(); 
    private QuestionLogBean questionLogBean = new QuestionLogBean();    
    private final String tableDesc = "问题记录";
    
    
    @SuppressWarnings("unchecked")
	public void exportAll(){
    	
    	List<Map<String,Object>> commonlist = dao.getCommonets();
    	List<Map<String,Object>> list = dao.getAllquesList();
    	
		String[][] arrs = new String[commonlist.size()][4];
		for(int i=0;i<commonlist.size();i++) {
			arrs[i][0] = (String)commonlist.get(i).get("COLUMN_COMMENT");
			arrs[i][1] = (String)commonlist.get(i).get("COLUMN_NAME");
			arrs[i][2] = "1";
			arrs[i][3] = "20";
		}
		
		for(Map<String,Object>map:list){
			try{
				Long ds = Long.parseLong(map.get("createtime")+"");
				map.put("createtime", stampToDate(ds));
			}
			catch(NumberFormatException e){
				map.put("createtime", "");
			}

			try{
				Long ds2 = Long.parseLong(map.get("answertime")+"");
				map.put("answertime", stampToDate(ds2));
			}
			catch(NumberFormatException e){
				map.put("answertime", "");
			}
			

		}
		
		
//    	System.out.println(commonlist.toString());
		ExportExcel export = new ExportExcel(tableDesc, response);
		export.setParam(arrs);
		export.addData(list);
		export.writeExcel();
    	
    }
    
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddQuestionLog() {
        if ("1".equals(oper)) {   
    	    questionLogBean = dao.select(QuestionLogBean.class,questionLogBean.getId());  
    	}    
    	return "toAddQuestionLog";    
    } 
 
    /**    
     * 新增
     */    
    public String addQuestionLog() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            long time = new Date().getTime()/1000;
          	SUserBean suserbean = (SUserBean)session.get("user");
            if ("1".equals(oper)) {
      
                showMessage = "编辑"+tableDesc;
                if(questionLogBean.getAnswer()!=null&&!"".equals(questionLogBean.getAnswer())){
                	if(suserbean.getType()==1){
                    	questionLogBean.setAnswertime(time);
                	}else{
                        showMessage = "此问题已结案";  
                        return error; 
                	}

                }else{
                   Long creatid =	questionLogBean.getCreateid();
                   if(creatid!=suserbean.getId()){
                       showMessage = "您没权限修改此问题";  
                       return error; 
                   }
                }
                result = dao.update(questionLogBean); 
            } else { 
            	questionLogBean.setCreatetime(time);
            	questionLogBean.setCreateid(suserbean.getId());
                result = dao.insert(questionLogBean); 
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
            "id","type","title","dsc","ver","createtime"
            ,"answer","answertime","createid"
        };
        boolean result=dao.update(questionLogBean,param);
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
    public String delQuestionLog() {  
    	try {
    		boolean result = dao.delete(QuestionLogBean.class,questionLogBean.getId());  
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
    public String delQuestionLogs() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(QuestionLogBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, questionLogBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public QuestionLogBean getQuestionLogBean() { 
    	return questionLogBean;    
    } 
 
    public void setQuestionLogBean(QuestionLogBean questionLogBean) {   
    	this.questionLogBean = questionLogBean;
    } 
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s*1000l);
        res = simpleDateFormat.format(date);
        return res;
    }
}
