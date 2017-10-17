package com.memberManage.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.common.BaseDAO;
import com.common.Page;
import com.memberManage.bean.QuestionLogBean;
                        
/**                     
 *                      
 * 问题记录 <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class QuestionLogDAO extends BaseDAO { 
    /**                 
     * 分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, QuestionLogBean questionlogBean) {   
    	String sql ="select a.*,b.username from question_log a left join s_user b on a.createid = b.id"; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 ";  
    	if(questionlogBean!=null) { 
    		if(questionlogBean.getId() != null) { 
    			objectList.add(questionlogBean.getId());
    			sqlWhere += " AND a.id = ? ";
    		} 
    		if(questionlogBean.getType() != null) { 
    			objectList.add(questionlogBean.getType());
    			sqlWhere += " AND a.type = ? ";
    		} 
    		if(questionlogBean.getTitle() != null && questionlogBean.getTitle().trim().length()>0) { 
    			objectList.add(questionlogBean.getTitle());
    			sqlWhere += " AND a.title = ? ";
    		} 
    		if(questionlogBean.getDsc() != null && questionlogBean.getDsc().trim().length()>0) { 
    			objectList.add(questionlogBean.getDsc());
    			sqlWhere += " AND a.dsc = ? ";
    		} 
    		if(questionlogBean.getVer() != null && questionlogBean.getVer().trim().length()>0) { 
    			objectList.add(questionlogBean.getVer());
    			sqlWhere += " AND a.ver = ? ";
    		} 
    		if(questionlogBean.getCreatetime() != null) { 
    			objectList.add(questionlogBean.getCreatetime());
    			sqlWhere += " AND a.createtime = ? ";
    		} 
    		if(questionlogBean.getAnswer() != null && questionlogBean.getAnswer().trim().length()>0) { 
    			objectList.add(questionlogBean.getAnswer());
    			sqlWhere += " AND a.answer = ? ";
    		} 
    		if(questionlogBean.getAnswertime() != null) { 
    			objectList.add(questionlogBean.getAnswertime());
    			sqlWhere += " AND a.answertime = ? ";
    		} 
    		if(questionlogBean.getCreateid() != null) { 
    			objectList.add(questionlogBean.getCreateid());
    			sqlWhere += " AND a.createid = ? ";
    		} 
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from question_log a "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }
    
    public List<Map<String,Object>> getAllquesList(){
    	
    	String sql = "select * from question_log";
    	return j.queryForList(sql);
    	
    }
    
    public List<Map<String,Object>> getCommonets(){
         String sql = "SELECT COLUMN_NAME,COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'question_log' AND table_schema = 'point'";
         return j.queryForList(sql);
    }
}                       
