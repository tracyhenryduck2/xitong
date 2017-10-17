package com.install.action;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.common.UploadImg;
import com.install.bean.InstallBean;
import com.install.contant.Constant;
import com.install.dao.InstallDAO;

import net.sf.json.JSONArray;

import com.common.BaseActionSupport;

/**
 * 
 * @author xin.chou
 *
 */
public class InstallAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private InstallDAO dao = new InstallDAO(); 
    private InstallBean installBean = new InstallBean();    
    private final String tableDesc = "";

    private String type;
    private String workname;
    private File file;
    private String fileFileName;
    public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
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
    	List<Map<String, Object>> list = dao.getPageList(page, installBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
    
    /**
     * 查询自己的检测记录
     * @return
     */
    public void plist() {
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(type != null && type.length()>0 && "3".equalsIgnoreCase(type)){
    		
    		if(workname != null && workname.length()>0){
    			List<Map<String, Object>> list = dao.getPeoplePageList(workname);
    			map.put("content", JSONArray.fromObject(list));
    			map.put("result", "200");
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
    /**
     * 按照收索条件收索
     * @return
     */
    public String search() {
        return "search"; 
    }
    
    /**
     * 上传信息接口
     * @return
     */
    public void postInfo() {
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(installBean !=null && installBean.getProductno().length()>0){
    		InstallBean installBean2 = dao.getOneInstall(installBean.getUsername(),installBean.getProductno());
    		if(installBean2 !=null && installBean2.getProductno().length()>0){
    			
    			long installTime2 = Calendar.getInstance().getTimeInMillis();
    			installBean2.setTime(installTime2/1000);
    			
    			if(installBean.getTime()!=0){
    				installBean2.setTime(installBean.getTime());
    			}
    			if(installBean.getCustomer() !=null && installBean.getCustomer().length()>0){
    				installBean2.setCustomer(installBean.getCustomer());;
    			}
    			if(installBean.getCphone() !=null && installBean.getCphone().length()>0){
    				installBean2.setCtphone(installBean.getCphone());
    			}
    			if(installBean.getCustomertwo() !=null && installBean.getCustomertwo().length()>0){
    				installBean2.setCustomertwo(installBean.getCustomertwo());;
    			}
    			if(installBean.getCtphone() !=null && installBean.getCphone().length()>0){
    				installBean2.setCtphone(installBean.getCtphone());;
    			}
    			if(installBean.getAddr() !=null && installBean.getAddr().length()>0){
    				installBean2.setAddr(installBean.getAddr());
    			}
    			if(installBean.getXno() !=null && installBean.getXno().length()>0){
    				installBean2.setXno(installBean.getXno());;
    			}
    			if(installBean.getYno() !=null && installBean.getYno().length()>0){
    				installBean2.setYno(installBean.getYno());;
    			}
    			if(dao.updateInstallInfor(installBean2)){
    				System.out.println("更新安装信息成功");
    				map.put("result", 200);
    	    		outPrintJSONObject(map);
    			}else{
    				map.put("result", 4004);
    	    		outPrintJSONObject(map);
    			}
    		}else{
    			long installTime = Calendar.getInstance().getTimeInMillis();
    			installBean.setTime(installTime/1000);
//    			String realAddr = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
//    			+"/"+Constant.PIC_PAGE+"/";
//    			if(installBean.getPicposition()!=null && installBean.getPicposition().length()>0){
//    				installBean.setPicposition(realAddr+installBean.getPicposition());
//    			}
//    			if(installBean.getPictwo()!=null && installBean.getPictwo().length()>0){
//    				installBean.setPictwo(realAddr+installBean.getPictwo());
//    			}
//    			if(installBean.getPicthree()!=null && installBean.getPicthree().length()>0){
//    				installBean.setPicthree(realAddr+installBean.getPicthree());
//    			}
    			if(dao.insertInstallInfor(installBean)){
    				System.out.println("插入安装信息成功");
    				map.put("result", 200);
    	    		outPrintJSONObject(map);
    			}else{
    				map.put("result", 4005);
    	    		outPrintJSONObject(map);
    			}
    		}
    	}else{
    		map.put("result", 4003);
    		outPrintJSONObject(map);
    	}
    	
	}
 
    public InstallBean getInstallBean() { 
    	return installBean;    
    } 
 
    public void setInstallBean(InstallBean installBean) {   
    	this.installBean = installBean;
    } 
    
    public File getFile() {
    	  return file;
    	 }
    	 public void setFile(File file) {
    	  this.file = file;
    	 }
    	 public String getFileFileName() {
    	  return fileFileName;
    	 }
    	 public void setFileFileName(String fileFileName) {
    	  this.fileFileName = fileFileName;
    	 }
    	 
    	 
    	 /**
    	  * 上传图片
    	  * http://SERVER[:PORT]/PROJECTNAME/install/action/InstallAction!upload.action
    	  */
    	 public void upload(){
    	  String dir=Constant.PIC_PAGE;
    	  UploadImg upload=new UploadImg(file,fileFileName,dir,request,response);
    	  try {
    	   upload.importFile();
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }
    	 }
}
