package com.install.action;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;

import com.common.BaseActionSupport;
import com.install.bean.InstallBean;
import com.install.dao.InstallDAO;
import com.opensymphony.xwork2.ActionContext;

import oracle.net.aso.n;

public class PicUploadAction  extends BaseActionSupport{
	private String workname;
	private String productno;
	private InstallBean installBean = new InstallBean();
	private InstallDAO dao = new InstallDAO(); 
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File[] image; //上传的文件
    private String[] imageFileName; //文件名称
    private String[] imageContentType; //文件类型

    public String execute() throws Exception {
        ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
        System.out.println(realpath);
        if (image != null) {
            File savedir=new File(realpath);
            if(!savedir.getParentFile().exists())
                savedir.getParentFile().mkdirs();
            for(int i=0;i<image.length;i++){
                File savefile = new File(savedir, imageFileName[i]);
                FileUtils.copyFile(image[i], savefile);
            }
            ActionContext.getContext().put("message", "文件上传成功");
        }
        System.out.println("tupian weizhi +++++ "+ realpath);
        return "success";
    }
    
    public void uploadPic(){
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(workname !=null && workname.length()>0 &&
    			productno!=null && productno.length()>0){
	    	try{
	    		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
		        System.out.println(realpath);
		        if (image != null) {
		            File savedir=new File(realpath);
		            if(!savedir.getParentFile().exists())
		                savedir.getParentFile().mkdirs();
		            for(int i=0;i<image.length;i++){
		                File savefile = new File(savedir, imageFileName[i]);
		                FileUtils.copyFile(image[i], savefile);
		                if(i == 0){
		                	installBean.setPicposition(realpath+"/"+imageFileName[0]);
		                }else if(i ==1) {
							installBean.setPictwo(realpath +"/" + imageFileName[1]);
						}else {
							installBean.setPicthree(realpath + "/" + imageFileName[2]);
						}
		            }
//		            ActionContext.getContext().put("message", "文件上传成功");
		        }
		        installBean.setUsername(workname);
		        installBean.setProductno(productno);
		        saveTodata();
		        System.out.println("tupian weizhi +++++ "+ realpath);
		        map.put("result", realpath);
	    	}catch(Exception e){
	    		map.put("result", 4001);
	    	}finally{
	    		System.out.println( workname + "对应的产品" + productno);
	    		outPrintJSONObject(map);
	    	}
    	}else {
			map.put("result", 4002);
			outPrintJSONObject(map);
		}
    	
    }

    
    private void saveTodata() {
		if(dao.isExist(workname, productno)){
			if(dao.updateInstall(installBean))
				System.err.println("更新信息成功");
		}else{
			if(dao.insertInstall(installBean))
				System.out.println("插入信息成功");
		}
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public File[] getImage() {
        return image;
    }

    public void setImage(File[] image) {
        this.image = image;
    }

    public String[] getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String[] imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String[] getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String[] imageFileName) {
        this.imageFileName = imageFileName;
    }
}
