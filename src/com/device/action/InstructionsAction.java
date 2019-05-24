package com.device.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import com.device.bean.InstructionsBean;
import com.device.dao.InstructionsDAO;
import com.common.Common;
import com.common.BaseActionSupport;

/**
 * 
 * @author junjun.xue
 *
 */
public class InstructionsAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private InstructionsDAO dao = new InstructionsDAO(); 
    private InstructionsBean instructionsBean = new InstructionsBean();    
    private final String tableDesc = "";
    private File file;
    private String fileFileName;
    /**    
     * 转向添加页面  
     * @return  
     */    
    public String toAddInstructions() {
        if ("1".equals(oper)) {   
    	    instructionsBean = dao.select(InstructionsBean.class,instructionsBean.getId());  
    	}    
    	return "toAddInstructions";    
    } 
 
    /**    
     * 新增
     */    
    public String addInstructions() {  
        try {   
            showMessage = "新增"+tableDesc; 
            boolean result = true;  
            if ("1".equals(oper)) {    
                showMessage = "编辑"+tableDesc;  
                result = dao.update(instructionsBean); 
            } else { 
                result = dao.insert(instructionsBean); 
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
            "id","name","file"
        };
        boolean result=dao.update(instructionsBean,param);
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
    public String delInstructions() {  
    	try {
    		boolean result = dao.delete(InstructionsBean.class,instructionsBean.getId());  
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
    public String delInstructionss() { 
    	try {
    		String[] idArr = request.getParameterValues("idArr");   
    		String ids = Common.array2String(idArr);   
    		boolean result = dao.deletes(InstructionsBean.class,ids);
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
    	List<Map<String, Object>> list = dao.getPageList(page, instructionsBean);
    	request.setAttribute("list", list);   
    	return "list";    
    } 
 
    public InstructionsBean getInstructionsBean() { 
    	return instructionsBean;    
    } 
 
    public void setInstructionsBean(InstructionsBean instructionsBean) {   
    	this.instructionsBean = instructionsBean;
    } 
    
    public void importFile() throws Exception{
    	response.setContentType("text/html;charset=utf-8");
    	out=response.getWriter();
    	InputStream in=new FileInputStream(file);

    	String fileName=fileFileName;
    	FileOutputStream fs=new FileOutputStream(getUploadPath()+fileName);
    	int byteread=0;
    	int bytesum=0;
    	byte[] buffer=new byte[1024];
    	while((byteread=in.read(buffer))!=-1){
    		bytesum+=byteread;
    		fs.write(buffer, 0, byteread);
    	}
    	fs.close();
    	in.close();
    	out.print(fileName);
    	out.close();
    }
    
    private String getUploadPath() {
    	File file=new File(request.getSession().getServletContext().getRealPath(""));
    	File tmpFile = new File(file.getParentFile().getAbsolutePath()+"/instruction/");
    	if(!tmpFile.exists()) {
    		tmpFile.mkdirs();
    	}
		return file.getParentFile().getAbsolutePath()+"/instruction/";
	}

	public void removeFile() throws IOException{
		out=response.getWriter();
    	String fileName=request.getParameter("fileName");
    	File file=new File(getUploadPath()+fileName);
    	boolean flag=true;
    	if(file.isFile()&&file.exists()){
    		flag=file.delete();
    	}
    	out.print(flag);
    	out.close();
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
}
