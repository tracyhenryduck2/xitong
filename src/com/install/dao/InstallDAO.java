package com.install.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avatar.db.DBFactory;
import com.avatar.db.jdbc.JdbcHandler;
import com.common.BaseDAO;
import com.common.Page;
import com.install.bean.InstallBean;



                        
/**                     
 *                      
 *  <br>          
 *                      
 * @author   <br>   
 * @taskId   <br>   
 */                     
public class InstallDAO extends BaseDAO { 
    /**                 
     * 查询所有信息
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPageList(Page page, InstallBean installBean) {   
    	String sql ="select  i.*, s.username  from install i,s_user s "; 
    	List<Object> objectList = new ArrayList<Object>();
    	String sqlWhere = " where 1=1 and i.workid = s.id";  
    	if(installBean!=null) { 
    		if(installBean.getUsername()!=null && installBean.getUsername().trim().length()>0) { 
    			objectList.add(installBean.getUsername());
    			sqlWhere += " AND s.username = ? ";
    		}
    		if(installBean.getAddr()!=null && installBean.getAddr().trim().length()>0) { 
    			objectList.add(installBean.getAddr());
    			sqlWhere += " AND i.addr like ? ";
    		}
    		if(installBean.getTime() != 0) { 
    			objectList.add(installBean.getTime());
    			sqlWhere += " AND i.time = ? ";
    		}
    	}                 
    	sql = sql + sqlWhere; 
    	Object[] pram = objectList.toArray(); 
    	if(page.getSortname()!=null && page.getSortorder()!=null) {   
    		sql += " order by "+page.getSortname()+" " +page.getSortorder(); 
    	}                 
    	page.setTotalRows(j.queryForInteger("select count(*) from install i,s_user s "+sqlWhere, pram));
    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return list;      
    }   
    
    
	/**                 
     * 巡检记录分页查询         
     * @param page      
     * @param           
     * @return          
     */                 
    public List<Map<String,Object>> getPeoplePageList(String workname) {   
    	String sql ="select  i.*, s.username  from install i,s_user s "
    			+ "where 1=1 and i.workid = s.id "
    			+ "and s.username =  '"+workname +"'"; 
//    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return j.queryForList(sql);
//    	return list;      
    } 
    
	/**                 
     * 查看单个安装信息         
     * @param page      
     * @param           
     * @return          
     */                 
    public InstallBean getOneInstall(String workname, String productno) {   
    	String sql ="select  i.*, s.username  from install i,s_user s "
    			+ "where 1=1 and i.workid = s.id "
    			+ "and s.username =  ? "
    			+ "and i.productno = ?"; 
    	Object[] params = { workname, productno };
//    	List<Map<String,Object>> list=j.queryForPageList(sql, page.getPageNo(),page.getPageSize(),pram);  
    	return j.queryForBean(InstallBean.class, sql, params);
//    	return list;      
    }
    
    
    /**
     * 查看是否存在数据
     * @param workname
     * @param productno
     * @return
     */
    public boolean isExist( String workname,String productno) {
		String sql = "select count(*) from install i, s_user s"
				+ "where i.workid = s.id "
				+ "and s.username = ? "
				+ "and i.productno = ? ";
		Object[] params = { workname, productno };
		int count = j.queryForInteger(sql, params);
		return count > 0;
	}
    
    
    /**
     * 增加安装图片信息
     * @param installBean
     * @return
     */
    public boolean insertInstall(InstallBean installBean){
    	String sql = "insert into install(workname,productno,picposition,pictwo,picthree) "
    			+ "values(?, ?, ?, ?, ?)";
		Object[] params = { installBean.getUsername(),installBean.getProductno(),
				installBean.getPicposition(),
				installBean.getPictwo(),
				installBean.getPicthree()};
		int count = j.queryForInteger(sql, params);
		return count > 0;
    }
    
    /**
     * 增加安装信息
     * @param installBean
     * @return
     */
    public boolean insertInstallInfor(InstallBean installBean){
    	String sql = "insert into install(workid,"
    			+ "productno,time,customer,cphone,"
    			+ "customertwo,ctphone,addr,xno,yno,workname,picposition,pictwo,picthree) "
    			+ "values(?, ?, ?, ?, ?, "
    			+ "?, ?, ?, ?, ?, "
    			+ "?, ?, ?, ?)";
    	System.out.println("提交信息sql："+sql);
    	Object[] params = { installBean.getWorkid(),installBean.getProductno(),installBean.getTime(),
    			installBean.getCustomer(),installBean.getCphone(),installBean.getCustomertwo(),
    			installBean.getCtphone(),installBean.getAddr(),installBean.getXno(),installBean.getYno(),
    			installBean.getUsername(),
    			installBean.getPicposition(),
    			installBean.getPictwo(),
    			installBean.getPicthree()};
    	return j.execute(sql, params);
    }
    
    /**
     * 修改图片信息
     * @param installBean
     * @return
     */
    public boolean updateInstall(InstallBean installBean){
    	String sql = "update install set picposition=?, "
    			+ "pictwo=?, "
    			+ "picthree=? "
    			+ "where workname=? "
    			+ "and productno=? ";
		Object[] params = { installBean.getPicposition(),installBean.getPictwo(),
				installBean.getPicthree(),installBean.getUsername(),
				installBean.getProductno()};
		int count = j.queryForInteger(sql, params);
		return count > 0;
    }
    
    /**
     * 修改安装信息
     * @param installBean
     * @return
     */
    public boolean updateInstallInfor(InstallBean installBean){
    	String sql = "update install set time=?,"
    			+ "customer=?,"
    			+ "cphone=?,"
    			+ "customertwo=?,"
    			+ "ctphone=?,"
    			+ "addr=?,"
    			+ "xno=?,"
    			+ "yno=? "
    			+ "where workname=? "
    			+ "and productno=? ";
    	Object[] params = { 
    			installBean.getTime(),
    			installBean.getCustomer(),
    			installBean.getCphone(),
    			installBean.getCustomertwo(),
    			installBean.getCtphone(),
    			installBean.getAddr(),
    			installBean.getXno(),
    			installBean.getYno(),
    			installBean.getUsername(),
    			installBean.getProductno()};
    	int count = j.queryForInteger(sql, params);
    	return count > 0;
    }
                   
}                       
